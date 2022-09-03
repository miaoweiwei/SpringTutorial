package com.config;

import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * spring 的配置类相当于 bean.xml
 */
@Configuration // 如果这个类以字节码的形式传给 AnnotationContext 对象，那么这个注解就可以不用写
@ComponentScan("com.itheima") //用于配置要扫描的包
@Import({JdbcConfig.class, TransactionConfig.class}) // 导入其他的配置类
@PropertySource("jdbcConfig.properties")
@EnableTransactionManagement // 实现 开启spring对注解事务的支持
public class SpringConfiguration {
}
