package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/*
 * 该类是一个配置类，它的作用就和bean.xml的作用是一样的
 * spring中的新注解
 * Configuration
 *      作用：指定当前类是一个配置类
 *      细节：当配置类做为 AnnotationConfigurationContext对象创建的参数时，该注解可以不写。
 * ComponentScan
 *      作用：用于通过注解指定spring在创建容器时要扫描的包
 *      属性：
 *          value：它和 basePackages 的作用是一样的，都是用于指定创建容器时要扫描的包
 *                 我们使用此注解就等同于在xml中配置了
 *                      <context:component-scan base-package="com.itheima"></context:component-scan>
 * Bean
 *      作用：用于把当前方法的返回值作为bean对象存入spring的ioc容器中
 *      属性：用于指定bean的id。当不写时，默认值就是当前方法的名称
 *      细节：当使用注解配置方法方法时，如果方法有参数，spring框架会去容器中查找有没有可用的Bean对象，
 *           查找的方式和Autowired注解的方式是一样的。
 *
 * Import
 *      作用：用于导入其他的配置类
 *      属性：
 *          value：用于指定其他配置类的字节码。
 *                当我们使用Import的注解之后，有Import修饰的类就是主配置类或者叫父配置类，其他的配置类叫配置类。
 * PropertySource
 *      作用：用于指定properties文件的位置
 *      属性：
 *          value：指定properties配置文件的名称和路径
 *              关键字：classPath，表示类路径下
 *
 */
@Configuration
//@ComponentScan(value = "com.itheima")
//@ComponentScan(basePackages = "com.itheima")
//@ComponentScan({"com.itheima", "config"})// 扫描包，只能扫描到代有注解的类里
@ComponentScan("com.itheima")// 扫描包，只能扫描到代有注解的类里
@Import(JdbcConfig.class)
@PropertySource("classpath:jdbcConfig.properties") // 加载jdbc配置文件
//不写 classpath: 也可以但是jdbcConfig.properties要在resources中不能有二级目录
//@PropertySource("jdbcConfig.properties") // 加载jdbc配置文件
public class SpringConfiguration {

}
