package com.itheima.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/*
 * 一个创建 Bean 对象的工厂
 * Bean：在计算机英语中，表示有重用组件的含义
 *
 * JavaBean：用Java语言编写的可重用的组件
 *      JavaBean的范围 远大于 实体类
 *
 *      他就是创建我们的service和dao对象的。
 *      第一个：需要一个配置文件来配置我们的service和dao
 *           配置的内容：唯一标志=全限定类名 （key=value）
 *      第二个：通过读取配置文件中配置的内容来反射创建对象。
 *
 *      我们的配置可以使用xml也可以是properties
 */
public class BeanFactory {
    //定义一个Properties对象
    private static Properties props;

    //定义一个Map，用于存放我们要创建的对象，我们把它称之为容器
    private static Map<String, Object> beans;

    //使用静态代码块为 Properties 对象赋值
    static {
        InputStream in = null;
        try {
            //实例化对象
            props = new Properties();
            //获取 Properties 文件流对象
            in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            props.load(in);

            // 在加载完Properties后再去对 beans 实例化,把所有要创建的bean类提前创建好。
            beans = new HashMap<String, Object>();
            //取出配置文件中所有的key
            Enumeration<Object> keys = props.keys();
            //遍历这个枚举
            while (keys.hasMoreElements()) {
                //取出每一个key
                String key = keys.nextElement().toString();
                //根据key获取Object的value
                String beanPath = props.getProperty(key);
                Object value = Class.forName(beanPath).newInstance();
                //把key和value存入到容器中
                beans.put(key, value);
            }
        } catch (Exception e) { // 如果这个配置文件我们没有读取成功的话，后面的内容就什么也干不了，所以就应该抛出异常
            throw new ExceptionInInitializerError("初始化Properties失败！");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public static <T> T getBean(String beanName) {
        return (T) beans.get(beanName);
    }

    /**
     * 根据Bean的名称获取全限定类名通过反射获取bean对象
     *
     * @param beanName
     * @param <T>
     * @return
     */
//    public static <T> T getBean(String beanName) {
//        T bean = null;
//        try {
//            String beanPath = props.getProperty(beanName);
//            Class<T> clazz = (Class<T>) Class.forName(beanPath);
//            bean = clazz.newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return bean;
//    }

}
