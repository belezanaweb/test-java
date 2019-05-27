package br.com.blz.testjava.jsons;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.blz.testjava.models.Inventory;

public class JsonChildSerializer extends JsonSerializer<JsonRecord> {

    @Override
    public void serialize(JsonRecord value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", value.getId());
        if (value instanceof Inventory)
        	jgen.writeNumberField("quantity", ((Inventory) value).getQuantity());
        	jgen.writeObjectField("warehouses", ((Inventory) value).getWarehouses());
        jgen.writeEndObject();
    }

}
