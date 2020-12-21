package br.com.blz.testjava.persistence.converter;

import br.com.blz.testjava.enums.BrazilianStates;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static java.util.Objects.nonNull;

@Converter
public class BrazilianStatesConverter implements AttributeConverter<BrazilianStates, String> {
    @Override
    public String convertToDatabaseColumn(BrazilianStates brazilianStates) {
        return nonNull(brazilianStates) ? brazilianStates.getValue() : null;
    }

    @Override
    public BrazilianStates convertToEntityAttribute(String s) {
        return BrazilianStates.fromCode(s).orElseThrow(() -> new IllegalArgumentException("Invalid Code!"));
    }
}
