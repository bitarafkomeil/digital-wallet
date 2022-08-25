package com.leovegas.digital.wallet.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class BadRequestException extends ResponseStatusException {

    public BadRequestException(String defaultMessage) {
        super(HttpStatus.BAD_REQUEST, defaultMessage);
    }
}
