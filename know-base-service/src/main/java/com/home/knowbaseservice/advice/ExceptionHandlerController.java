package com.home.knowbaseservice.advice;

import com.home.knowbaseservice.model.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.Instant;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Handle exception [{}]", e.getMessage());
        ResponseStatus status = e.getClass().getAnnotation(ResponseStatus.class);
        ErrorResponse responseBody = new ErrorResponse(e.getMessage(), Instant.now());

        return ResponseEntity.status(status.code()).body(responseBody);
    }
}
