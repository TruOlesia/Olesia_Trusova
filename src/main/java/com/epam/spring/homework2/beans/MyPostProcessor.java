package com.epam.spring.homework2.beans;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (bean instanceof Bean) {
            if (((Bean) bean).getName() == null || ((Bean) bean).getValue() < 0) {
                System.out.println("Not valid");
            }
        }
        return bean;
    }
}