package ua.khshanovskyi.config;

import org.springframework.context.annotation.Bean;

public class EnableTrimmedConfig {

    @Bean
    public TrimmedAnnotationBeanPostProcessor getTrimmedAnnotationBeanPostProcessor(){
        return new TrimmedAnnotationBeanPostProcessor();
    }
}
