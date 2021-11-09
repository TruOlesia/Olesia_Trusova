package com.epam.spring.homework1.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Pet {
    @Autowired
    private List<Animals> animals;

    public void printAnimals() {
        for (Animals animals : animals) {
            System.out.println(animals.getAnimal());}
    }

}
