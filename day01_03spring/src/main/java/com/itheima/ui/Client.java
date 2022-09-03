package com.itheima.ui;

import com.itheima.dao.IAccountDao;
import com.itheima.service.IAccountService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {
    /*
     * 获取Spring的IOC核心容器，并根据id获取对象
     * ApplicationContext的三个常用实现类，
     *      ClassPathXmlApplicationContext：它可以加载类路径下的配置文件，要求配置文件必须在类路径下。不在的话就加载不了(推荐使用)
     *      FileSystemXmlApplicationContext：它可以加载磁盘任意路径下的配置文件（必须要有访问权限）
     *
     *      AnnotationXmlConfigApplicationContext：它适用于读取注解创建容器的。（在后一天的内容中说明）
     * 核心容器的两个接口引发的问题：
     * ApplicationContext：单例对象适用（更多是使用这个接口）
     *      它在构建对象核心容器时，创建对象采用的策略是采用立即加载的方式。也就是说，只要一读取完配置文件就马上创建配置文件中配置的对象
     * BeanFactory：多例对象适用
     *      它在构建对象核心容器时，创建对象化采取的策略是采用延迟加载的方式。也就是说，什么时候根据id获取对象了，什么时候才真正的创建对象。
     *
     * @param args
     */
    public static void main(String[] args) {
        //1. 获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // ApplicationContext ac = new FileSystemXmlApplicationContext("D:\\Myproject\\JavaProject\\IDEA\\Tutorial\\Spring\\day01_03spring\\src\\main\\resources\\bean.xml");

        //2.根据id获取bean对象
        IAccountService as = (IAccountService) ac.getBean("accountService");
        IAccountDao adao = ac.getBean("accountDao", IAccountDao.class);

        System.out.println(as);
        System.out.println(adao);

        /********************* BeanFactory **************************/
        Resource resource = new ClassPathResource("bean.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        IAccountService as1 = (IAccountService) factory.getBean("accountService");
        IAccountDao adao1 = factory.getBean("accountDao", IAccountDao.class);

        System.out.println(as1);
        System.out.println(adao1);
    }
}
