package br.com.pagseguro.uol.consultaprecosacoesbolsaapi.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    @JsonProperty("error_code")
    String errorCode;
    String message;

}
