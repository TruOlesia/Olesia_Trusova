package com.epam.spring.homework2.beans;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class BeanE implements Bean {
    private String name;
    private int value;

    public BeanE(){

    }

    public BeanE(String name, int value) {
        this.value = value;
        this.name = name;
    }

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

    @PostConstruct
    public void doPostConstruct() {
        System.out.println("postConstruct");
    }

    @PreDestroy
    public void doPreDestroy() {
        System.out.println("preDestroy");
    }
}
