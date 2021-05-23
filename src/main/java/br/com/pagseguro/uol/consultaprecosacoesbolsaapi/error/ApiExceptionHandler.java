package br.com.pagseguro.uol.consultaprecosacoesbolsaapi.error;

import br.com.pagseguro.uol.consultaprecosacoesbolsaapi.error.exception.BusinessException;
import br.com.pagseguro.uol.consultaprecosacoesbolsaapi.error.exception.CustomErrorWithOneStringParameterException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

    private static final String NO_MESSAGE_AVAILABLE = "No message available";

    private static final Logger LOG = LoggerFactory.getLogger(ApiExceptionHandler.class);

    private final MessageSource apiErrorMessageSource;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handlerBusinessException(BusinessException exception, Locale locale) {
        final String errorCode = exception.getErrorCode();
        final HttpStatus statusCode = exception.getStatusCode();

        final ErrorResponse errorResponse = createApiError(errorCode, locale, null);

        return ResponseEntity.status(statusCode).body(errorResponse);
    }

    @ExceptionHandler(CustomErrorWithOneStringParameterException.class)
    public ResponseEntity<ErrorResponse> handlerNotFoundException(CustomErrorWithOneStringParameterException exception, Locale locale) {
        final String errorCode = exception.getErrorCode();
        final HttpStatus statusCode = exception.getStatusCode();
        final String stringNotFound = exception.getStringNotFound();

        final ErrorResponse errorResponse = createApiError(errorCode, locale, stringNotFound);

        return ResponseEntity.status(statusCode).body(errorResponse);
    }

    public ErrorResponse createApiError(String errorCode, Locale locale, String stringNotFound) {
        String message;
        String[] stringNotFoundAsArray = { stringNotFound };
        try {
            message = apiErrorMessageSource.getMessage(errorCode, stringNotFoundAsArray, locale);
        } catch (NoSuchMessageException e) {
            LOG.error("Could not find any message for {} code under {} locale", errorCode, locale);
            message = NO_MESSAGE_AVAILABLE;
        }

        return new ErrorResponse(errorCode, message);
    }

}
