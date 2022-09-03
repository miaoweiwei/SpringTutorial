package com.itheima.service.impl;

import com.itheima.service.IAccountService;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {
    public AccountServiceImpl() {
        System.out.println("对象被创建了...");
    }

    public void saveAccount() {
        System.out.println("service 中的saveAccount方法执行了");
    }

    public void init() {
        System.out.println("service 对象初始化了");
    }

    public void destroy() {
        System.out.println("service 对象销毁了");
    }
}
