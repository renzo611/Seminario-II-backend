package com.seminario.backend.exception;


import lombok.Data;

@Data
public class GeneralException extends Exception{
    private final long code;

    public GeneralException(String message, long code) {
        super(message);
        this.code = code;
    }
}
