package br.com.blz.testjava.base.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ExceptionResponse {

    private String message;
    private String error;
    private Integer status;

}
