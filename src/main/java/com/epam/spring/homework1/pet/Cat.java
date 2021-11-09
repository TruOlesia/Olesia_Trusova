package com.epam.spring.homework1.pet;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class Cat implements Animals{
   public String getAnimal(){
       return "cat";
   }
}
