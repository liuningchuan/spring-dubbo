package com.liuning.dao.plugin;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.sql.Types;

/**
 * @author: liuning
 * @description: 将数据库类型TINYINT、BIGINT类型统一映射为Java Integer类型
 * @create: 2020-06-14 15:05
 * @version: 1.0
 */
public class JavaTypeResovlerPlugin extends JavaTypeResolverDefaultImpl {

    public JavaTypeResovlerPlugin() {
        super();
        super.typeMap.put(Types.BIGINT, new JdbcTypeInformation("BITINT",
                new FullyQualifiedJavaType(Integer.class.getSimpleName())));
        super.typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT",
                new FullyQualifiedJavaType(Integer.class.getSimpleName())));
    }
}
