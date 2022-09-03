package com.itheima.service.impl;

import com.itheima.annotation.LoggerAnnotation;
import com.itheima.service.IAccountService;
import org.springframework.stereotype.Service;

/**
 * @author weiweimiao.mww@alibaba-inc.com
 * @date 2022/9/3
 */
@LoggerAnnotation
@Service("accountServiceImpl1")
public class AccountServiceImpl1 implements IAccountService {
    @Override
    public void saveAccount() {
        System.out.println("AccountServiceImpl1 执行了保存");
    }

    @Override
    public void updateAccount(int i) {
        System.out.println("AccountServiceImpl1 执行了更新");
    }

    @Override
    public int deleteAccount() {
        System.out.println("AccountServiceImpl1 执行了删除");
        return 0;
    }
}
