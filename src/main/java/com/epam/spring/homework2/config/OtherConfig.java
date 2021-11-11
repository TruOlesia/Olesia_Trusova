package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.BeanF;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

public class OtherConfig {
    @Bean
    @Lazy
    public BeanF getBeanF() {
        return new BeanF();
    }
}
