package br.com.blz.testjava.utils;

import br.com.blz.testjava.config.Configs;
import br.com.blz.testjava.dto.response.Response;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class ConvertResponse {

    private Configs configs;

    public Response create() {
        Response response = new Response();
        response.setMessage(configs.getMessageCreate());
        log.info("ConvertResponse : create : " + response.toString());
        return response;
    }

    public Response update() {
        Response response = new Response();
        response.setMessage(configs.getMessageUpdate());
        log.info("ConvertResponse : update : " + response.toString());
        return response;
    }
}
