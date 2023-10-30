package com.seminario.backend.exception;

import com.seminario.backend.dto.GeneralResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<GeneralResponseDto> handleWeltioException(GeneralException ge) {
        return new ResponseEntity<>(new GeneralResponseDto(ge.getCode(), ge.getMessage()), HttpStatus.resolve((int) ge.getCode()));
    }
}
