package com.systex.common.converter.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

@JsonComponent
public class Date2StringConverter extends JsonSerializer<Date> implements Converter<Date, String> {

    @Override
    public String convert(@Nullable Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(date);
    }

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String typeValue = convert(date);
        if (typeValue == null) {
            gen.writeNull();
            return;
        }

        gen.writeString(convert(date));
    }
}
