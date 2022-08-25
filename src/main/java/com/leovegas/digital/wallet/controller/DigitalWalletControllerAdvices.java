package com.leovegas.digital.wallet.controller;

import com.leovegas.digital.wallet.data.dto.ErrorDto;
import com.leovegas.digital.wallet.exception.BadRequestException;
import com.leovegas.digital.wallet.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Slf4j
@ControllerAdvice
public class DigitalWalletControllerAdvices {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ErrorDto handleException(HttpServletRequest req, MethodArgumentNotValidException ex) {
        log.error(ex.getMessage());
        List<String> messages = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        return ErrorDto.builder().timestamp(new Date()).status(HttpStatus.BAD_REQUEST.value())
                .error(messages.toString()).path(req.getRequestURI()).build();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody
    ErrorDto handlePlayerNotExistsException(HttpServletRequest req, NotFoundException ex) {
        log.error(ex.getMessage());
        return ErrorDto.builder().timestamp(new Date()).status(HttpStatus.NOT_FOUND.value()).error(ex.getReason())
                .path(req.getRequestURI()).build();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ErrorDto handlePlayerNotExistsException(HttpServletRequest req, BadRequestException ex) {
        log.error(ex.getMessage());
        return ErrorDto.builder().timestamp(new Date()).status(HttpStatus.BAD_REQUEST.value()).error(ex.getReason())
                .path(req.getRequestURI()).build();
    }

    @ExceptionHandler(PropertyReferenceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ErrorDto handlePlayerNotExistsException(HttpServletRequest req, PropertyReferenceException ex) {
        log.error(ex.getMessage());
        return ErrorDto.builder().timestamp(new Date()).status(HttpStatus.BAD_REQUEST.value()).error(ex.getMessage())
                .path(req.getRequestURI()).build();
    }

}
