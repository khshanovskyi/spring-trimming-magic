package ua.khshanovskyi.annotation;

import org.springframework.context.annotation.Import;
import ua.khshanovskyi.config.EnableTrimmedConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(EnableTrimmedConfig.class)
public @interface EnableStringTrimming {
}
