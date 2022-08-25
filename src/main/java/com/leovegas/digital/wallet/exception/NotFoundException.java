package com.leovegas.digital.wallet.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class NotFoundException extends ResponseStatusException {

    public NotFoundException(String defaultMessage) {
        super(HttpStatus.NOT_FOUND, defaultMessage);
    }
}
