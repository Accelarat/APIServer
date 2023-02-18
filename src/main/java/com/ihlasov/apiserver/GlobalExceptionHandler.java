package com.ihlasov.apiserver;


import com.ihlasov.apiserver.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String DEFAULT_MESSAGE = "Что-то пошло не так :(";

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> globalException() {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(DEFAULT_MESSAGE);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<String> stringResponseEntity(ServiceException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }
}