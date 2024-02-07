package com.systex.common.converter.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.systex.ddd.domain.exception.ValidateFailedException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

@JsonComponent
public class String2DateConverter extends JsonDeserializer<Date> implements Converter<String, Date> {

    @Override
    public Date convert(@Nullable String value) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return dateFormat.parse(value);
        } catch (ParseException e) {
            throw new ValidateFailedException(
                ValidateFailedException.DomainErrorStatus.VALIDATE_FAILED, "Date pattern error.");
        }
    }

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return convert(p.getText());
    }
}
