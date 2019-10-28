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

    @Value("${dubbo.register.address}")
    private String dubboRegisterAdress;

    @Value("${dubbo.consumer.timeout:30000}")
    private Integer consumerTimeout;
    @Value("${dubbo.consumer.retries:-1}")
    private Integer consumerRetries;

    @Value("${dubbo.provider.timeout:30000}")
    private Integer providerTimeout;

    @Value("${dubbo.protocol.name:dubbo}")
    private String protocolName;
    @Value("${dubbo.protocol.port:20881}")
    private Integer protocolPort;
    @Value("${dubbo.protocol.threadPool:fixed}")
    private String protocolThreadPool;
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
