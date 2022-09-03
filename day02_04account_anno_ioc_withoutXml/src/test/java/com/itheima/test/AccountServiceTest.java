package com.itheima.test;

import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import config.SpringConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.swing.*;
import java.util.List;

/*
 * 使用Junit单元测试，测试我们的配置
 * Spring整合junit的配置
 *      1.导入spring整合junit的jar(坐标)
 *      2.使用junit提供的一个注解把原有的main方法替换掉，替换成spring提供的
 *          @Runwith
 *      3.告知spring的运行器，spring的ioc创建是基于xml的还是基于注解的，并且说明位置
 *          @ContextConfiguration
 *              locations：指定xml文件的位置，加上classpath关键字，表示在类路径下
 *              classes：指定注解类所在的位置
 *  当我们使用spring 5.x版本的时候，要求junit的jar包必须是4.12及以上
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class AccountServiceTest {
    // private ApplicationContext ac;
    // private ClassPathXmlApplicationContext ac;
    // private AnnotationConfigApplicationContext ac;
    @Autowired
    IAccountService as;

    @Before
    public void init() {
        //1. 获取核心容器对象
        // ac = new ClassPathXmlApplicationContext("bean.xml");
        // ac = new AnnotationConfigApplicationContext(SpringConfiguration.class, JdbcConfig.class); // 参数是用 Configuration 注解修饰的类的 class
        // ac = new AnnotationConfigApplicationContext(SpringConfiguration.class); // 参数是用 Configuration 注解修饰的类的 class
        // as = ac.getBean("accountService", IAccountService.class);
    }

    @After
    public void destroy() {
//        ac.close();
    }

    @Test
    public void testFindAll() {
        List<Account> accounts = as.findAllAccount();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne() {
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave() {
        Account account = new Account();
        account.setName("test anno");
        account.setMoney(5000f);
        as.saveAccount(account);
    }

    @Test
    public void testUpdate() {
        Account account = as.findAccountById(4);
        account.setName("test_update");
        as.updateAccount(account);
    }

    @Test
    public void testDelete() {
        as.deleteAccount(4);
    }
}
