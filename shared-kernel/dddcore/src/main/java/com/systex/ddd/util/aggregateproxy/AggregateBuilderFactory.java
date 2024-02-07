package com.systex.ddd.util.aggregateproxy;

import java.lang.reflect.Method;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class AggregateBuilderFactory {

    public static <B> B getProxyInstance(Class<B> builderClass) {
        Enhancer en = new Enhancer();
        en.setSuperclass(builderClass);
        en.setCallback(new BuilderMethodInterceptor());

        return builderClass.cast(en.create());
    }

    static class BuilderMethodInterceptor implements MethodInterceptor {

        // 每個方法都會執行
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            // 1. 取得 build method
            // 2. 加上 proxy (加上 Enhancer 與 interceptor)

            if ("build".equals(method.getName())) {
                Object aggregate = methodProxy.invokeSuper(o, objects);
                Method m = AggregateFactory.class.getDeclaredMethod("getProxyInstance", Class.class, Object.class);
                Object proxy = m.invoke(null, aggregate.getClass(), aggregate);
                return proxy;
            } else {
                return methodProxy.invokeSuper(o, objects);
            }
        }
    }
}
