package br.com.pagseguro.uol.consultaprecosacoesbolsaapi.mocks;

import br.com.pagseguro.uol.consultaprecosacoesbolsaapi.client.response.*;
import br.com.pagseguro.uol.consultaprecosacoesbolsaapi.entity.Acao;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MockCreator {
    protected List<Acao> getVinteAcoesMock() {
        try {
            return new ObjectMapper().readValue(new File("src/test/java/br/com/pagseguro/uol/consultaprecosacoesbolsaapi/mocks/json/lista_vinte_acoes_mock.json"), new TypeReference<List<Acao>>() {});
        } catch (IOException e) {
            System.out.println("Erro ao pegar os mocks do json.");
        }
        return new ArrayList<>();
    }

    protected List<Acao> getCincoAcoesMock() {
        try {
            return new ObjectMapper().readValue(new File("src/test/java/br/com/pagseguro/uol/consultaprecosacoesbolsaapi/mocks/json/lista_cinco_acoes_mock.json"), new TypeReference<List<Acao>>() {});
        } catch (IOException e) {
            System.out.println("Erro ao pegar os mocks do json.");
        }
        return new ArrayList<>();
    }

    protected AutoCompleteResponse getAutoCompleteResponse(String quoteSymbol) {
        List<Quote> quotes = new ArrayList<>();
        quotes.add(new Quote(quoteSymbol));
        return new AutoCompleteResponse(quotes);
    }

    protected MarketQuotesResponse getMarketQuotesResponse(BigDecimal priceMedian) {
        List<Result> results = new ArrayList<>();
        results.add(new Result(priceMedian));
        return new MarketQuotesResponse(new QuoteResponse(results));
    }

}
