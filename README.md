### 核心技术选型

- 核心框架：**Spring Boot**
- 微服务框架：**Dubbo**
- 持久层框架：**MyBatis** + **Mapper**
- 数据库连接池：**Druid**
- 日志管理：**SLF4J**、**Logback**
- 基础组件：配置中心**Apollo**、缓存**Redis**、监控**Metrics**等
- 消息队列：**RocketMQ**、**Kafka**
- 文件系统：**FastDFS**



### 监控集成

Spring Boot 2.x 版本中， `spring-boot-actuator` 使用了Micrometer来实现监控。同时引入`micrometer-registry-prometheus` 依赖，该包对 Prometheus 进行了封装，可以很方便的集成到 Spring Boot 工程中。

```xml
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-prometheus</artifactId>
    <version>1.7.0</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
    <version>2.1.8.RELEASE</version>
</dependency>
```

在 `application.properties` 中配置如下：

```properties
#开启Actuator服务
management.endpoints.web.exposure.include=*
management.metrics.tags.application=application-name
```

访问 `http://{ip}:{port}/actuator/prometheus` 就可以看到应用的一系列不同类型 metrics 信息，配合Prometheus可以监控应用指标，配合Grafana Dashboard可以优雅直观地展示这些监控值。

访问 `http://{ip}:{port}/actuator/health` 可以进行应用探活。



### RocketMQ

#### 消费者组

一个消费者组，除了使用同一个group name外，订阅的 tag 也必须是一样的，只有符合这两个条件的 consumer 实例才能组成 consumer 集群。如果使用相同的group name但是订阅不同的tag，则会出现消息丢失的情况。

#### 消费模式

**集群消费**：当consumer使用集群消费时，每条消息只会被consumer集群内的任意一个consumer实例消费一次。使用集群消费时，consumer的消费进度是存储在broker上，consumer自身是不存储消费进度的，这样保证了消息不会被重复消费。同时，在集群消费模式下，并不能保证每一次消息失败重投都投递到同一个consumer实例。

**广播消费**：当consumer使用广播消费时，每条消息都会被consumer集群内所有的consumer实例消费一次，也就是说每条消息至少被每一个consumer实例消费一次。广播消费时，不会进行消费失败重投的，需要特别关注消费失败的情况。

```java
defaultMQPushConsumer.setMessageModel(MessageModel.BROADCASTING);
defaultMQPushConsumer.setMessageModel(MessageModel.CLUSTERING);
```





> Apache Dubbo是一款高性能、轻量级的开源Java RPC框架，它提供了三大核心能力：面向接口的远程方法调用，智能容错和负载均衡，以及服务自动注册和发现。
>
> 简单来说Dubbo是一个分布式服务框架，致力于提供高性能和透明化的RPC远程服务调用方案，以及SOA服务治理方案。

## 注解介绍

### @EnableDubbo

`@EnableDubbo` 注解是 `@EnableDubboConfig` 和 `@DubboComponentScan`两者组合的便捷表达方式。与注解驱动相关的是 `@DubboComponentScan`。

```java
package org.apache.dubbo.config.spring.context.annotation;

@EnableDubboConfig
@DubboComponentScan
public @interface EnableDubbo {
  
    @AliasFor(annotation = DubboComponentScan.class, attribute = "basePackages")
    String[] scanBasePackages() default {};

    @AliasFor(annotation = DubboComponentScan.class, attribute = "basePackageClasses")
    Class<?>[] scanBasePackageClasses() default {};    
}
```

通过 `@EnableDubbo` 可以在指定的包名下（通过 `scanBasePackages`），或者指定的类中（通过 `scanBasePackageClasses`）扫描 Dubbo 的服务提供者（以 `@Service` 标注）以及 Dubbo 的服务消费者（以 `Reference` 标注）。

扫描到 Dubbo 的服务提供方和消费者之后，对其做相应的组装并初始化，并最终完成服务暴露或者引用的工作。

当然，如果不使用外部化配置（External Configuration）的话，也可以直接使用 `@DubboComponentScan`。

通过 Spring 中 Java Config 的技术（`@Configuration`）和 annotation 扫描（`@EnableDubbo`）来发现、组装、并向外提供 Dubbo 的服务。

```java
@Configuration
@EnableDubbo(scanBasePackages = "com.alibaba.dubbo.samples.impl")
static class ProviderConfiguration {
    @Bean // #1
    public ProviderConfig providerConfig() {
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setTimeout(1000);
        return providerConfig;
    }

    @Bean // #2
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("dubbo-annotation-provider");
        return applicationConfig;
    }

    @Bean // #3
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("localhost");
        registryConfig.setPort(2181);
        return registryConfig;
    }

    @Bean // #4
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20880);
        return protocolConfig;
    }
}
```

说明：

- 通过 `@EnableDubbo` 指定在 `com.alibaba.dubbo.samples.impl` 下扫描所有标注有 `@Service` 的类
- 通过 `@Configuration` 将 ProviderConfiguration 中所有的 `@Bean` 通过 Java Config 的方式组装出来并注入给 Dubbo 服务，也就是标注有 `@Service` 的类。这其中就包括了：
  1. ProviderConfig：服务提供方配置
  2. ApplicationConfig：应用配置
  3. RegistryConfig：注册中心配置
  4. ProtocolConfig：协议配置

按照上面的方法，在使用过程中遇到了两个坑（dubbo版本2.7.2）：

- 使用**@EnableDubbo**注解扫描不到路径

  ```java
  @EnableDubbo(scanBasePackages = "com.liuning.dubbo.service.impl")
  ```

  最后使用**@DubboComponentScan**注解成功

  ```java
  @DubboComponentScan(basePackages = "com.liuning.dubbo.service.impl")
  ```

- **RegistryConfig**配置出现问题，使用

  ```java
  registryConfig.setAddress("localhost");
  registryConfig.setPort(2181);
  ```

  一直提示连接不了zookeeper注册中心，最后使用

  ```javascript
  registryConfig.setAddress("localhost:2181");
  registryConfig.setPort(2181);
  ```

  则成功连接上zk注册中心，删掉`registryConfig.setPort(2181)`也可成功连接。

这两个问题都是2.7.2版本下出现的，暂时搞不懂，希望后面深入后能找到答案。

### ZooKeeper

Zookeeper客户端提供了基本的操作，比如，创建会话、创建节点、读取节点、更新数据、删除节点和检查节点是否存在等。但对于开发人员来说，Zookeeper提供的基本操纵还是有一些不足之处。目前主要有两种主流的第三方开源客户端**ZkClient**和**Curator**。

#### ZkClient

ZkClient是一个开源客户端，在Zookeeper原生API接口的基础上进行了包装，更便于开发人员使用。内部实现了Session超时重连，Watcher反复注册等功能。

```xml
<dependency>
    <groupId>com.101tec</groupId>
    <artifactId>zkclient</artifactId>
    <version>0.11</version>
</dependency>
```

#### Curator

Curator是Netflix公司开源的一套Zookeeper客户端框架，和ZkClient一样，解决了非常底层的细节开发工作，包括连接重连、反复注册Watcher和NodeExistsException异常等。目前已经成为Apache的顶级项目。Curator的Maven依赖如下，一般直接使用curator-recipes即可。

```xml
<dependency>
    <groupId>org.apache.curator</groupId>
    <artifactId>curator-recipes</artifactId>
    <version>2.12.0</version>
</dependency>
```

如果需要自己封装一些底层些的功能的话，例如增加连接管理重试机制等，则可以引入curator-framework包。

```xml
<dependency>
    <groupId>org.apache.curator</groupId>
    <artifactId>curator-framework</artifactId>
    <version>2.12.0</version>
</dependency>
```

