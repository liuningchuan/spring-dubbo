package com.liuning.common.utils;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * properties属性获取工具
 *
 * @author liuning
 * @since 2021-05-25 21:46
 */
@Component
public class PropertiesUtil implements EmbeddedValueResolverAware {

    private StringValueResolver stringValueResolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        this.stringValueResolver = stringValueResolver;
    }

    public String getProperties(String key) {
        return stringValueResolver.resolveStringValue("${key}");
    }
}
