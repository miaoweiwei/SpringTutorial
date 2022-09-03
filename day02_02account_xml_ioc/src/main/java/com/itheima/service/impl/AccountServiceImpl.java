package com.itheima.service.impl;


import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> findAllAccount() {
        return this.accountDao.findAllAccount();
    }

    public Account findAccountById(Integer accountId) {
        return this.accountDao.findAccountById(accountId);
    }

    public void saveAccount(Account account) {
        this.accountDao.saveAccount(account);
    }

    public void updateAccount(Account account) {
        this.accountDao.updateAccount(account);
    }

    public void deleteAccount(Integer accountId) {
        this.accountDao.deleteAccount(accountId);
    }
}
