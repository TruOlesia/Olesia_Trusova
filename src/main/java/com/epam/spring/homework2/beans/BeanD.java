package com.epam.spring.homework2.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BeanD implements Bean{
    @Value("${beanD.name}")
    private String name;
    @Value("${beanD.value}")
    private int value;


    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getValue() {
        return value;
    }

    public String toString() {
        return "bean name = " + name + ", " + "bean value = " + value;
    }

    private void customInitMethod(){
        System.out.println("init method " + this.getClass().getSimpleName() );
    }

    private void customDestroyMethod(){
        System.out.println("destroy method " + this.getClass().getSimpleName() );
    }
}
