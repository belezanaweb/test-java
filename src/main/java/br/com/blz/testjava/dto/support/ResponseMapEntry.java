package br.com.blz.testjava.dto.support;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Map;

import lombok.Value;

@JsonFormat(shape = JsonFormat.Shape.STRING)
@Value(staticConstructor = "of")
public class ResponseMapEntry implements Map.Entry<Object, Object> {

    Object key;

    Object value;

    @Override
    public String setValue(Object value) {
        return null;
    }

}
