package br.com.blz.testjava.interceptors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ErrorDTO {

    private int httpStatus;
    private String message;
}
