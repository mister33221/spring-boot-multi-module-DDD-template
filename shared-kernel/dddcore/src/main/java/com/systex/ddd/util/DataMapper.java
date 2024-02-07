package com.systex.ddd.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.ErrorMessage;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataMapper {

    private static final ModelMapper mapper = new ModelMapper();

    static {
        mapper.getConfiguration()
              .setFullTypeMatchingRequired(true);
        mapper.getConfiguration()
              .setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public static <T> T map(Object resource, Class<T> targetClass) {
        if (resource == null) {
            return null;
        }
        targetIsSubset(resource, targetClass);
        return mapper.map(resource, targetClass);
    }

    public static <T> List<T> map(Iterable<?> iterable, Class<T> targetClass) {
        List<T> resources = new ArrayList<>();
        if (iterable != null) {
            for (Object element : iterable) {
                targetIsSubset(element, targetClass);
                resources.add(map(element, targetClass));
            }
        }
        return resources;
    }

    private static <T> void targetIsSubset(Object resource, Class<T> targetClass) {
        for (Field targetField : targetClass.getDeclaredFields()) {
            boolean match = false;
            for (Field resourceField : resource.getClass()
                                               .getDeclaredFields()) {
                if (targetField.getName()
                               .equals(resourceField.getName()) && (targetField.getType() == resourceField.getType())) {
                    match = true;
                    break;

                }
            }
            if (!match) {
                throw new MappingException(Arrays.asList(new ErrorMessage("無法在來源物件裡找到目標類別的所有屬性!")));
            }
        }
    }
}
