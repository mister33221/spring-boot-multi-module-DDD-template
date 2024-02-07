package com.systex.common.converter.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.systex.ddd.domain.UUID;
import java.io.IOException;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

@JsonComponent
public class UUID2StringConverter extends JsonSerializer<UUID> implements Converter<UUID, String> {

    @Override
    public String convert(@Nullable UUID uuid) {
        if (uuid == null) {
            return null;
        }
        return uuid.getValue();
    }

    @Override
    public void serialize(UUID value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String typeValue = convert(value);
        if (typeValue == null) {
            gen.writeNull();
            return;
        }
        gen.writeString(convert(value));
    }
}
