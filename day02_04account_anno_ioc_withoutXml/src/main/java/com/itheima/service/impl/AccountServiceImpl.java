package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 账户的业务层实现类
 */
@Service("accountService")
public class AccountServiceImpl implements IAccountService {

    @Autowired// 因为ioc容器中只有一个 IAccountDao 对象，所以这里使用只这个注解也可以
//    @Resource(name = "accountDao")
    private IAccountDao accountDao;

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
