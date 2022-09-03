package com.itheima.ui;

import com.itheima.service.IAccountService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {
    public static void main(String[] args) {
        //1. 获取核心容器对象
        // 由于close()方法不在接口 ApplicationContext 中定义，所以 如果使用多态，ApplicationContext这个类型的ac是不能调用close方法的
        // ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");

        //2.根据id获取bean对象
//        IAccountService as = (IAccountService) ac.getBean("accountService");
//        as.saveAccount();

//        IAccountService as = (IAccountService) ac.getBean("accountService2");
//        as.saveAccount();

        IAccountService as = (IAccountService) ac.getBean("accountService3");
        as.saveAccount();
    }
}
