package com.itheima.service.impl;

import com.itheima.dao.impl.AccountDaoImpl;
import com.itheima.dao.IAccountDao;
import com.itheima.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/*
 * 账户的业务层实现类
 * 曾经的xml配置
 * <bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl"
 *      scope="" init-method="" destroy-method="">
 *     <property name="" value=""(ref="")></property
 * </bean>
 *
 * 用于创建对象的注解
 *      他们的作用和在xml中配置文件中编写一个 <bean></bean> 标签实现的功能是一样的
 *      Component
 *          作用：用于把当前类对象存入到spring容器中
 *          属性：
 *              value：用于指定bean的id，。当我们不写时，它的默认值时当前类名，且首字母改小写
 *      Controller：一般用在表现层
 *      Service：一般用于业务层
 *      Repository：一般用于持久层
 *      以上三个注解他们的作用和属性与Component是一模一样的。
 *      他们三个是Spring框架为我们提供明确的三层架构使用的注解，使我们的三层对象更加清晰
 *
 * 用于注入数据的
 *      他们的作用就和在xml配置文件中 <bean>标签中写一个<property>标签的作用是一样的
 *      Autowired:
 *          作用:自动按照类型注入.只要容器中有唯一的一个bean对象类型和要注入的变量类型匹配,就可以注入成功.
 *              如果ioc容器中没有任何bean的类型和要注入的变量类型匹配，则报错。
 *              如果ioc容器中有多个类型匹配时：spring会把变量名当做id去容器中查找有没有对应类型并且id为变量名的对象，
 *                  如果找到，就直接注入，否则就报错。
 *          出现位置:
 *              可以是成员变量上,也可以是方法上
 *          细节：
 *              使用注解注入时，set方法就不是必须的了。
 *      Qualifier：
 *          作用：在按照类型注入的基础之上再按照名称注入。它在给类成员注入时不能单独使用。但是在给方法参数注入时可以（稍后讲）
 *          属性：
 *              value：用于指定注入bean的id。
 *      Resource
 *          作用：直接按照bean的id注入。它可以独立使用
 *          属性：
 *              name：用于指定bean的id
 *      以上三个注解都只能注入其他bean类型的数据，而基本数据类型和String类型无法使用上述注解实现。
 *      另外，集合类型的注入只能通过xml来实现。
 * 用户改变作用范围的
 *      他们的作用就和在bean标签中使用scope属性实现的功能是一样的
 *      Scope
 *          作用：指定bean的作用范围
 *          属性：
 *              value：指定范围的取值，常用取值：singleton(对应单例)、prototype(对应多例)
 *
 * 和生命周期相关的(了解)
 *      他们的作用就和在bean标签中使用 init-method 标签和 destroy-method 标签的作用是一样的
 *      PreDestroy
 *          作用：用于指定销毁方法
 *      PostConstruct
 *          作用：用于指定初始化方法
 */
//@Component(value = "accountService")
//@Component("accountService") //当注解中我们只用一个value属性，那这个value是可以不写的，如果同时有两个或者以上的属性赋值就必须要写
@Service("accountService")
@Scope("singleton")
public class AccountServiceImpl implements IAccountService {
    // @Autowired
    // @Qualifier("accountDao1")
    @Resource(name = "accountDao1")
    private IAccountDao accountDao = null;

    public AccountServiceImpl() {
        System.out.println("对象被创建了...");
    }

    @PostConstruct
    public void init() {
        System.out.println("AccountServiceImpl init ...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("AccountServiceImpl destroy ...");
    }

    public void saveAccount() {
        accountDao.saveAccount();
    }
}
