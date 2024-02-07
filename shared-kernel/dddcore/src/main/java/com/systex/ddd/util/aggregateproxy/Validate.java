package com.systex.ddd.util.aggregateproxy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {

    boolean allowFailure() default false;

    @AliasFor("methods") String[] value() default {};

    @AliasFor("value") String[] methods() default {};
}
