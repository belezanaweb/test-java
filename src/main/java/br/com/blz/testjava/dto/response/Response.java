package br.com.blz.testjava.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
public class Response {

    public Response() {
        this.timestamp = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    }

    private String message;
    private LocalDateTime timestamp;
}
