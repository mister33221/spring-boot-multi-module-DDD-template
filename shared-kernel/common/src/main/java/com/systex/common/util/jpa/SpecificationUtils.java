package com.systex.common.util.jpa;

import org.springframework.data.jpa.domain.Specification;

public class SpecificationUtils {

    public static <T> Specification<T> equal(String columnName, Object value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null) {
                return criteriaBuilder.conjunction();
            } else {
                return criteriaBuilder.equal(root.get(columnName), value);
            }
        };
    }
}
