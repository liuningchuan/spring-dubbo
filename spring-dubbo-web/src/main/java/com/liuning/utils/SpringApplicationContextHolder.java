package com.liuning.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 在多线程下，无法使用@Autowired或@Resource自动注入bean
 * 可以使用此方法获取bean：SpringApplicationContextHolder.getBean(xxx.class)
 */
@Component
public class SpringApplicationContextHolder implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        applicationContext = arg0;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> cls) {
        return applicationContext.getBean(cls);
    }
}
