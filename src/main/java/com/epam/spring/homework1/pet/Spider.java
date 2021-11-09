package com.epam.spring.homework1.pet;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class Spider implements Animals{
    public String getAnimal(){
        return "spider";
    }
}
