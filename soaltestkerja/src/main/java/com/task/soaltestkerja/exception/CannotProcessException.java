package com.task.soaltestkerja.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CannotProcessException extends RuntimeException{
    public CannotProcessException(String message) {
        super(message);
    }
}
