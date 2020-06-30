package com.wuwenxu.codecamp.base.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Spring Bean 工具类
 *
 * @author KK
 * @Description
 * @createDate 2019/06/05 14:12
 */
@Component
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @SuppressWarnings("static-access")
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        SpringUtils.applicationContext = applicationContext;

    }

    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    public static <T> Collection<T> getBeansOfType(Class<T> classClass) {
        return applicationContext.getBeansOfType(classClass).values();
    }
}
