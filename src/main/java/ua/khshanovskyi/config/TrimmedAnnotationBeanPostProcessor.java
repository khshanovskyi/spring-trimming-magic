package ua.khshanovskyi.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import ua.khshanovskyi.annotation.Trimmed;

public class TrimmedAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(bean.getClass());

        if (beanClass.isAnnotationPresent(Trimmed.class)) {
            enhancer.setCallback((MethodInterceptor) (obj, method, arguments, proxy) -> {
                for (int i = 0; i < arguments.length; i++) {
                    if (arguments[i].getClass().isAssignableFrom(String.class)) {
                        Object arg = arguments[i];
                        arguments[i] = StringUtils.trim(String.valueOf(arg));
                    }
                }

                Object returnValue = proxy.invokeSuper(obj, arguments);
                if (method.getReturnType().isAssignableFrom(String.class)) {
                    returnValue = StringUtils.trim(String.valueOf(returnValue));
                }

                return returnValue;
            });
        }

        return enhancer.create();
    }
}
