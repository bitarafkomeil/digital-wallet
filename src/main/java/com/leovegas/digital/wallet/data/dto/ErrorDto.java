package com.leovegas.digital.wallet.data.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ErrorDto {

    private Date timestamp;
    private int status;
    private String error;
    private String path;
}