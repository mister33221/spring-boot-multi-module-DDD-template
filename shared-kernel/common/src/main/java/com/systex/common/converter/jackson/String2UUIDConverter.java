package com.systex.common.converter.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.systex.ddd.domain.UUID;
import java.io.IOException;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

@JsonComponent
public class String2UUIDConverter extends JsonDeserializer<UUID> implements Converter<String, UUID> {

    @Override
    public UUID convert(@Nullable String uuid) {

        return UUID.generateUUID(uuid);
    }

    @Override
    public UUID deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {

        return convert(p.getText());
    }
}