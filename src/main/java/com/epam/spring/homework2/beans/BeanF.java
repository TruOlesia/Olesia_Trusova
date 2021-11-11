package com.epam.spring.homework2.beans;

import org.springframework.stereotype.Component;

@Component
public class BeanF implements Bean{
    private String name;
    private int value;

    public BeanF() {
    }
    public BeanF(String name, int value) {
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
}
