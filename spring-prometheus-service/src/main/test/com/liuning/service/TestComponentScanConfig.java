package com.liuning.service;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 测试扫描配置
 *
 * @author liuning
 * @since 2020-10-19 23:40
 */
@Configuration
@MapperScan(value = {"com.liuning.dao.*"})
@ComponentScan(basePackages = {"com.liuning.service.*"})
public class TestComponentScanConfig {

    @Bean
    public DataSource druidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/liuning");
        dataSource.setUsername("root");
        dataSource.setPassword("open123");
        return dataSource;
    }
}
