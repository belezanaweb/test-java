package br.com.blz.testjava.adapter_in.exception.custom_exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@NoArgsConstructor
@Getter
public class ValidacaoException extends RuntimeException {

    private final List<Map<String, Object[]>> messages = new ArrayList<>();

    public void addMessage(String message,Object... params) {
        Map<String, Object[]> messageMap = new HashMap<>();
        messageMap.put(message, params);
        this.messages.add(messageMap);
    }
    public Boolean existemErros() {
        return (!this.messages.isEmpty());
    }



}
