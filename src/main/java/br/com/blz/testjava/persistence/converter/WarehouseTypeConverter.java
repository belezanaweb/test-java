package br.com.blz.testjava.persistence.converter;

import br.com.blz.testjava.enums.BrazilianStates;
import br.com.blz.testjava.enums.WarehouseType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static java.util.Objects.nonNull;

@Converter
public class WarehouseTypeConverter implements AttributeConverter<WarehouseType, String> {

    @Override
    public String convertToDatabaseColumn(WarehouseType type) {
        return nonNull(type) ? type.getName() : null;
    }

    @Override
    public WarehouseType convertToEntityAttribute(String s) {
        return WarehouseType.fromCode(s).orElseThrow(() -> new IllegalArgumentException("Invalid Code!"));
    }
}
