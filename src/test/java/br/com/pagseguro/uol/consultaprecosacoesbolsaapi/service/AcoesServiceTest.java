package br.com.pagseguro.uol.consultaprecosacoesbolsaapi.service;

import br.com.pagseguro.uol.consultaprecosacoesbolsaapi.client.YahooFinanceServiceClient;
import br.com.pagseguro.uol.consultaprecosacoesbolsaapi.dto.AcaoDto;
import br.com.pagseguro.uol.consultaprecosacoesbolsaapi.error.exception.BusinessException;
import br.com.pagseguro.uol.consultaprecosacoesbolsaapi.mocks.MockCreator;
import br.com.pagseguro.uol.consultaprecosacoesbolsaapi.repository.AcoesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.List;

import static br.com.pagseguro.uol.consultaprecosacoesbolsaapi.constants.GeneralConstants.ERRORPAGSEG_009;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class AcoesServiceTest extends MockCreator {

    private final AcoesRepository repository = mock(AcoesRepository.class);

    private final YahooFinanceServiceClient yahooFinanceServiceClient = mock(YahooFinanceServiceClient.class);

    private final AcoesService service = new AcoesService(repository, yahooFinanceServiceClient);

    @Test
    public void deveListarTodasAcoesEOPrecoMedioDelasComSucesso() {
        beforeDeveListarTodasAcoesEOPrecoMedioDelasComSucesso();

        List<AcaoDto> acoesComPrecoMedio = service.listarPrecoMedioDezMaioresAcoes();

        assertEquals(10, acoesComPrecoMedio.size());
        verify(yahooFinanceServiceClient, times(10)).getAutoComplete(any(), any(), any());
        verify(yahooFinanceServiceClient, times(10)).getMarketQuotes(any(), any(), any());
    }

    @Test
    public void deveLancarExcecaoComCodigoERRORPAGSEG009QuandoTenhoMenosDeDezAcoesNoBancoParaBuscarPrecoMedio() {
        when(repository.findAll()).thenReturn(getCincoAcoesMock());

        try {
            service.listarPrecoMedioDezMaioresAcoes();
        } catch (BusinessException e) {
            assertEquals(ERRORPAGSEG_009, e.getErrorCode());
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, e.getStatusCode());
        }
    }

    private void beforeDeveListarTodasAcoesEOPrecoMedioDelasComSucesso() {
        when(repository.findAll()).thenReturn(getVinteAcoesMock());

        when(yahooFinanceServiceClient.getAutoComplete(eq("BBDC4"), anyString(), any())).thenReturn(getAutoCompleteResponse("BBDC44"));
        when(yahooFinanceServiceClient.getMarketQuotes(anyString(), eq("BBDC44"), any())).thenReturn(getMarketQuotesResponse(new BigDecimal(1)));

        when(yahooFinanceServiceClient.getAutoComplete(eq("B3SA3"), anyString(), any())).thenReturn(getAutoCompleteResponse("B3SA33"));
        when(yahooFinanceServiceClient.getMarketQuotes(anyString(), eq("B3SA33"), any())).thenReturn(getMarketQuotesResponse(new BigDecimal(2)));

        when(yahooFinanceServiceClient.getAutoComplete(eq("ABEV3"), anyString(), any())).thenReturn(getAutoCompleteResponse("ABEV33"));
        when(yahooFinanceServiceClient.getMarketQuotes(anyString(), eq("ABEV33"), any())).thenReturn(getMarketQuotesResponse(new BigDecimal(3)));

        when(yahooFinanceServiceClient.getAutoComplete(eq("BBAS3"), anyString(), any())).thenReturn(getAutoCompleteResponse("BBAS33"));
        when(yahooFinanceServiceClient.getMarketQuotes(anyString(), eq("BBAS33"), any())).thenReturn(getMarketQuotesResponse(new BigDecimal(4)));

        when(yahooFinanceServiceClient.getAutoComplete(eq("BBDC3"), anyString(), any())).thenReturn(getAutoCompleteResponse("BBDC33"));
        when(yahooFinanceServiceClient.getMarketQuotes(anyString(), eq("BBDC33"), any())).thenReturn(getMarketQuotesResponse(new BigDecimal(5)));

        when(yahooFinanceServiceClient.getAutoComplete(eq("BPAC11"), anyString(), any())).thenReturn(getAutoCompleteResponse("BPAC111"));
        when(yahooFinanceServiceClient.getMarketQuotes(anyString(), eq("BPAC111"), any())).thenReturn(getMarketQuotesResponse(new BigDecimal(6)));

        when(yahooFinanceServiceClient.getAutoComplete(eq("BRFS3"), anyString(), any())).thenReturn(getAutoCompleteResponse("BRFS33"));
        when(yahooFinanceServiceClient.getMarketQuotes(anyString(), eq("BRFS33"), any())).thenReturn(getMarketQuotesResponse(new BigDecimal(7)));

        when(yahooFinanceServiceClient.getAutoComplete(eq("BBSE3"), anyString(), any())).thenReturn(getAutoCompleteResponse("BBSE33"));
        when(yahooFinanceServiceClient.getMarketQuotes(anyString(), eq("BBSE33"), any())).thenReturn(getMarketQuotesResponse(new BigDecimal(8)));

        when(yahooFinanceServiceClient.getAutoComplete(eq("BRAP4"), anyString(), any())).thenReturn(getAutoCompleteResponse("BRAP44"));
        when(yahooFinanceServiceClient.getMarketQuotes(anyString(), eq("BRAP44"), any())).thenReturn(getMarketQuotesResponse(new BigDecimal(9)));

        when(yahooFinanceServiceClient.getAutoComplete(eq("CCRO3"), anyString(), any())).thenReturn(getAutoCompleteResponse("CCRO33"));
        when(yahooFinanceServiceClient.getMarketQuotes(anyString(), eq("CCRO33"), any())).thenReturn(getMarketQuotesResponse(new BigDecimal(10)));
    }
}
