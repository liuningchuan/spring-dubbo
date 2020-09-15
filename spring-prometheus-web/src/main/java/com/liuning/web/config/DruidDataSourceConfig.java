package com.liuning.web.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan(value = "com.liuning.dao.mapper")
public class DruidDataSourceConfig {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.druid.minEvictableIdleTimeMillis:60000}")
    private long minEvictableIdleTimeMillis;
    @Value("${spring.datasource.druid.maxEvictableIdleTimeMillis:90000}")
    private long maxEvictableIdleTimeMillis;

    @Bean
    public DataSource druidDataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setMaxEvictableIdleTimeMillis(maxEvictableIdleTimeMillis);
        //配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
        dataSource.setFilters("stat,wall,log4");
        return dataSource;
    }

    @Bean
    public ServletRegistrationBean<StatViewServlet> druidStatViewServlet() {
        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(new StatViewServlet(),  "/druid/*");
        registrationBean.addInitParameter("allow", "127.0.0.1");// IP白名单 (没有配置或者为空，则允许所有访问)
        registrationBean.addInitParameter("deny", "");// IP黑名单 (存在共同时，deny优先于allow)
        registrationBean.addInitParameter("loginUsername", "root");
        registrationBean.addInitParameter("loginPassword", "1234");
        registrationBean.addInitParameter("resetEnable", "false");
        return registrationBean;
    }
}
