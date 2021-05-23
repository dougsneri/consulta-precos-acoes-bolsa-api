package br.com.pagseguro.uol.consultaprecosacoesbolsaapi.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class BusinessException extends RuntimeException {

    private final String errorCode;
    private final HttpStatus statusCode;

}
