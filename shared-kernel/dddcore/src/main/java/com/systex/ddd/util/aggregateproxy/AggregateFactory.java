package com.systex.ddd.util.aggregateproxy;

import com.systex.ddd.domain.exception.ValidateFailedException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import lombok.AllArgsConstructor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class AggregateFactory {

    public static <T> T getProxyInstance(Class<T> targetType, T target) {
        if (target == null) {
            throw new RuntimeException("target is null");
        }

        Enhancer en = new Enhancer();
        en.setSuperclass(targetType);
        en.setCallback(new AggregateMethodInterceptor(target));

        return targetType.cast(en.create());
    }

    @AllArgsConstructor
    static class AggregateMethodInterceptor implements MethodInterceptor {

        private Object target;

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            AggregateValidate aggregateValidate = method.getAnnotation(AggregateValidate.class);
            if (aggregateValidate != null) {
                Validate[] validates = aggregateValidate.value();

                for (Validate validate : validates) {
                    String[] methods = validate.value();
                    boolean allowFailure = validate.allowFailure();
                    ValidateFailedException exception = new ValidateFailedException();
                    for (String m : methods) {
                        Method x = target.getClass()
                                         .getDeclaredMethod(m, method.getParameterTypes());
                        try {
                            x.setAccessible(true);
                            x.invoke(target, objects);
                        } catch (InvocationTargetException ex) {
                            Throwable e = ex.getTargetException();
                            if (allowFailure && (e instanceof ValidateFailedException)) {
                                exception.add((ValidateFailedException)e);
                            } else {
                                throw e;
                            }
                        }
                    }
                    if (exception.hasErrors()) {
                        throw exception;
                    }
                }
            }

            return method.invoke(target, objects);
        }
    }

}
