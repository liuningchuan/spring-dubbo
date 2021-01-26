package com.liuning.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * 获取BeanFactory容器
 *
 * @author liuning
 * @since 2021-01-26 23:34
 */
@Component
public class SpringBeanFactoryHolder implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(String name) {
        return (T) beanFactory.getBean(name);
    }
}
