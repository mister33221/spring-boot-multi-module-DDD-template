package com.systex.common.converter.jpa;

import com.systex.ddd.domain.UUID;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UUIDConverter implements AttributeConverter<UUID, String> {

    @Override
    public String convertToDatabaseColumn(UUID attribute) {
        return attribute == null ? null : attribute.getValue();
    }

    @Override
    public UUID convertToEntityAttribute(String dbData) {
        return (dbData == null || dbData.isEmpty()) ? null : UUID.generateUUID(dbData);
    }
}
