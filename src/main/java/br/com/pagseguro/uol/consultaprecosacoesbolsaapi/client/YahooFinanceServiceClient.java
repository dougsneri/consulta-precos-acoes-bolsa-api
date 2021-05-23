package br.com.pagseguro.uol.consultaprecosacoesbolsaapi.client;

import br.com.pagseguro.uol.consultaprecosacoesbolsaapi.client.response.AutoCompleteResponse;
import br.com.pagseguro.uol.consultaprecosacoesbolsaapi.client.response.MarketQuotesResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(url= "https://apidojo-yahoo-finance-v1.p.rapidapi.com/" , name = "yahoofinance")
public interface YahooFinanceServiceClient {

    @GetMapping("auto-complete")
    AutoCompleteResponse getAutoComplete(@RequestParam("q") String q,
                                         @RequestParam("region") String region,
                                         @RequestHeader Map<String, String> headers);

    @GetMapping("market/v2/get-quotes")
    MarketQuotesResponse getMarketQuotes(@RequestParam("region") String region,
                                         @RequestParam("symbols") String symbols,
                                         @RequestHeader Map<String, String> headers);

}
