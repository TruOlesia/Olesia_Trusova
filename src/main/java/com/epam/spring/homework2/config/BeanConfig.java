package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Bean;

@Configuration
@PropertySource("application.properties")
@ComponentScan("com.epam.spring.homework2.beans")
@Import(OtherConfig.class)
public class BeanConfig {

    @Bean(initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    @DependsOn({"beanD"})
    public BeanB getBeanB() {
        return new BeanB();
    }

    @Bean(initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    @DependsOn({"beanB"})
    public BeanC getBeanC() {

        return new BeanC();
    }

    @Bean(initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    public BeanD getBeanD() {
        return new BeanD();
    }
}
