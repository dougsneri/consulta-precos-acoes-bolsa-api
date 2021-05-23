package br.com.pagseguro.uol.consultaprecosacoesbolsaapi.service;

import br.com.pagseguro.uol.consultaprecosacoesbolsaapi.client.YahooFinanceServiceClient;
import br.com.pagseguro.uol.consultaprecosacoesbolsaapi.client.response.AutoCompleteResponse;
import br.com.pagseguro.uol.consultaprecosacoesbolsaapi.client.response.MarketQuotesResponse;
import br.com.pagseguro.uol.consultaprecosacoesbolsaapi.dto.AcaoDto;
import br.com.pagseguro.uol.consultaprecosacoesbolsaapi.entity.Acao;
import br.com.pagseguro.uol.consultaprecosacoesbolsaapi.error.exception.BusinessException;
import br.com.pagseguro.uol.consultaprecosacoesbolsaapi.error.exception.CustomErrorWithOneStringParameterException;
import br.com.pagseguro.uol.consultaprecosacoesbolsaapi.repository.AcoesRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import static br.com.pagseguro.uol.consultaprecosacoesbolsaapi.constants.GeneralConstants.*;

@Service
public class AcoesService {

    private final AcoesRepository acoesRepository;
    private final YahooFinanceServiceClient yahooFinanceServiceClient;

    public AcoesService(final AcoesRepository acoesRepository,
                        final YahooFinanceServiceClient yahooFinanceServiceClient) {
        this.acoesRepository = acoesRepository;
        this.yahooFinanceServiceClient = yahooFinanceServiceClient;
    }

    public List<Acao> listarTodasAcoes() {
        return acoesRepository.findAll();
    }

    public List<AcaoDto> listarPrecoMedioDezMaioresAcoes() {
        return mapAcoesAsAcoesDtoWithPriceMedian(getDezMaioresAcoes());
    }

    public void adicionarAcao(Acao acao) {
        acoesRepository.save(acao);
    }

    public Acao buscarAcaoPorCodigoAcao(String codigo) {
        Optional<Acao> acaoPorId = acoesRepository.findById(codigo);

        if (acaoPorId.isEmpty())
            throw new CustomErrorWithOneStringParameterException(ERRORPAGSEG_001, codigo, HttpStatus.NOT_FOUND);

        return acaoPorId.get();
    }

    public Acao buscarAcaoPorNomeAcao(String nome) {
        Acao acao = acoesRepository.findByNomeAcao(nome);

        if (acao == null)
            throw new CustomErrorWithOneStringParameterException(ERRORPAGSEG_008, nome, HttpStatus.NOT_FOUND);

        return acao;
    }

    public void deletarAcaoPorCodigoAcao(String codigo) {
        try {
            acoesRepository.deleteById(codigo);
        } catch (EmptyResultDataAccessException e) {
            throw new CustomErrorWithOneStringParameterException(ERRORPAGSEG_007, codigo, HttpStatus.NOT_FOUND);
        }
    }

    private List<Acao> getDezMaioresAcoes() {
        try {
            List<Acao> todasAcoes = acoesRepository.findAll();

            todasAcoes.sort(Comparator.comparing(Acao::getParticipacao).reversed());

            return todasAcoes.subList(
                    INDICE_INICIAL_LISTA_MAIORES_ACOES,
                    INDICE_FINAL_LISTA_MAIORES_ACOES
            );
        } catch (Exception e) {
            throw new BusinessException(ERRORPAGSEG_006, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private List<AcaoDto> mapAcoesAsAcoesDtoWithPriceMedian(List<Acao> maioresAcoes) {
        List<AcaoDto> acoesDto = new ArrayList<>();

        maioresAcoes.forEach(acao -> acoesDto.add(convertAcaoToAcaoDto(acao)));

        return acoesDto;
    }

    private AcaoDto convertAcaoToAcaoDto(Acao acao) {
        return new AcaoDto(
                acao.getCodigo(),
                acao.getNomeAcao(),
                acao.getQuantidadeTeorica(),
                acao.getParticipacao(),
                getPrecoMedioFromYahooFinanceByCodigo(acao.getCodigo()));
    }

    private BigDecimal getPrecoMedioFromYahooFinanceByCodigo(String codigo) {
        AutoCompleteResponse autoCompleteResponse =
                yahooFinanceServiceClient.getAutoComplete(codigo, REGIAO_PADRAO_PARA_BUSCA_DE_ACOES, getHeaders());

        if (autoCompleteResponse.getQuotes().isEmpty())
            throw new CustomErrorWithOneStringParameterException(ERRORPAGSEG_002, codigo, HttpStatus.INTERNAL_SERVER_ERROR);

        if (autoCompleteResponse.getQuotes().size() > 1)
            throw new CustomErrorWithOneStringParameterException(ERRORPAGSEG_003, codigo, HttpStatus.INTERNAL_SERVER_ERROR);

        MarketQuotesResponse marketQuotesResponse =
                yahooFinanceServiceClient.getMarketQuotes(
                        REGIAO_PADRAO_PARA_BUSCA_DE_ACOES,
                        autoCompleteResponse.getQuotes().get(INDICE_ACAO_AUTO_COMPLETE).getSymbol(),
                        getHeaders());

        if (marketQuotesResponse.getQuoteResponse().getResult().isEmpty()) {
            throw new CustomErrorWithOneStringParameterException(ERRORPAGSEG_004, codigo, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (marketQuotesResponse.getQuoteResponse().getResult().size() > 1)
            throw new CustomErrorWithOneStringParameterException(ERRORPAGSEG_005, codigo, HttpStatus.INTERNAL_SERVER_ERROR);

        return marketQuotesResponse
                .getQuoteResponse()
                .getResult().get(INDICE_ACAO_MARKET_QUOTES)
                .getTargetPriceMedian();
    }

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put(X_RAPIDAPI_KEY, getXApiKey());

        return headers;
    }

    private String getXApiKey() {
        return API_KEY_VALUE;
    }

}

