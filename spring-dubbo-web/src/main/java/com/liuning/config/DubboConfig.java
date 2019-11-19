package com.liuning.config;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConsumerConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DubboConfig {

    /**
     * zookeeper注册中心地址
     */
    @Value("${dubbo.register.address}")
    private String dubboRegisterAdress;
    /**
     * 消费者超时时间
     */
    @Value("${dubbo.consumer.timeout:30000}")
    private Integer consumerTimeout;
    @Value("${dubbo.consumer.retries:-1}")
    private Integer consumerRetries;
    /**
     * 生产者超时时间
     */
    @Value("${dubbo.provider.timeout:30000}")
    private Integer providerTimeout;
    /**
     * 默认服务使用Dubbo服务
     */
    @Value("${dubbo.protocol.name:dubbo}")
    private String protocolName;
    /**
     * 在20881端口监听服务
     */
    @Value("${dubbo.protocol.port:20881}")
    private Integer protocolPort;
    /**
     * 线程池类型，默认fixed
     */
    @Value("${dubbo.protocol.threadPool:fixed}")
    private String protocolThreadPool;
    /**
     * 线程池大小，默认200
     */
    @Value("${dubbo.protocol.threads:200}")
    private Integer protocolThreads;

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("spring-dubbo");
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(dubboRegisterAdress);
        registryConfig.setTimeout(providerTimeout);
        return registryConfig;
    }

    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setTimeout(consumerTimeout);
        consumerConfig.setRetries(consumerRetries);
        consumerConfig.setCheck(false);
        return consumerConfig;
    }

    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName(protocolName);
        protocolConfig.setThreadpool(protocolThreadPool);
        protocolConfig.setThreads(protocolThreads);
        protocolConfig.setPort(protocolPort);
        return protocolConfig;
    }

}
