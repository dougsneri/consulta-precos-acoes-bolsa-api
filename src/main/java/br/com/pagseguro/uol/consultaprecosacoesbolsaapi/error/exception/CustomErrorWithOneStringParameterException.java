package br.com.pagseguro.uol.consultaprecosacoesbolsaapi.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class CustomErrorWithOneStringParameterException extends RuntimeException {

    private final String errorCode;
    private final String stringNotFound;
    private final HttpStatus statusCode;

}
