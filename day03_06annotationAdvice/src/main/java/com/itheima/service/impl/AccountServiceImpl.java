package com.itheima.service.impl;

import com.itheima.annotation.LoggerAnnotation;
import com.itheima.service.IAccountService;
import org.springframework.stereotype.Service;

/**
 * 账户的业务层实现类
 */
@Service("accountService")
public class AccountServiceImpl implements IAccountService {

    @Override
    @LoggerAnnotation
    public void saveAccount() {
        System.out.println("执行了保存");
        //        int i = 1 / 0;
    }

    @Override
    public void updateAccount(int i) {
        System.out.println("执行了更新");
    }

    @Override
    public int deleteAccount() {
        System.out.println("执行了删除");
        return 0;
    }
}
