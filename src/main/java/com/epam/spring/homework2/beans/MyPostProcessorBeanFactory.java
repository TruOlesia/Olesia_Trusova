package com.epam.spring.homework2.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyPostProcessorBeanFactory implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        BeanFactoryPostProcessor bp = configurableListableBeanFactory1 -> {
            BeanDefinition beanDefinition = configurableListableBeanFactory1.getBeanDefinition("beanB");
            beanDefinition.setInitMethodName("changedInitMethod");
            System.out.println("myPostProcessorBeanFactory");
        };

    }
}
