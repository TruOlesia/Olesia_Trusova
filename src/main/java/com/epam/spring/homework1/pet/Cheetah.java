package com.epam.spring.homework1.pet;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class Cheetah implements Animals{
    public String getAnimal(){
        return "cheetah";
    }
}
