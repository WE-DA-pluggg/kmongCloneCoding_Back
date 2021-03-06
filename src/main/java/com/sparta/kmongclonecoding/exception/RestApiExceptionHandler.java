package com.sparta.kmongclonecoding.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import static com.sparta.kmongclonecoding.exception.ErrorCode.TEMPORARY_SERVER_ERROR;


@RestControllerAdvice//json형태로 익셉션을 처리한다.
public class RestApiExceptionHandler {

    @ExceptionHandler(value =  Exception.class )
        public ResponseEntity<RestApiException> handleApiRequestException(Exception ex) {
            RestApiException restApiException = RestApiException.of(TEMPORARY_SERVER_ERROR);
            restApiException.setHttpStatus(HttpStatus.BAD_REQUEST);
            restApiException.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(
                restApiException,
                HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(value = CustomException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestApiException> handleCustomException(CustomException e) {
        RestApiException restApiException = RestApiException.of(e.getErrorCode());
        restApiException.setErrorMessage(e.getMessage());
        return new ResponseEntity<>(restApiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value =  IllegalArgumentException.class )
    public ResponseEntity<RestApiException> handleApiRequestException(IllegalArgumentException ex) {
        RestApiException restApiException = RestApiException.of(TEMPORARY_SERVER_ERROR);
        restApiException.setHttpStatus(HttpStatus.BAD_REQUEST);
        restApiException.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(
                restApiException,
                HttpStatus.BAD_REQUEST
        );
    }


}
