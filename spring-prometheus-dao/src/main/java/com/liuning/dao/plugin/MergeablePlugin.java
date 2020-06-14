package com.liuning.dao.plugin;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author: liuning
 * @description: 禁用追加模式
 * @create: 2020-06-14 15:10
 * @version: 1.0
 */
public class MergeablePlugin extends PluginAdapter {

    public MergeablePlugin() {
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
        //强制禁用追加模式
        try {
            boolean flag = false;
            Field field = sqlMap.getClass().getDeclaredField("isMergeable");
            field.setAccessible(true);
            field.setBoolean(sqlMap, flag);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return super.sqlMapGenerated(sqlMap, introspectedTable);
    }
}
