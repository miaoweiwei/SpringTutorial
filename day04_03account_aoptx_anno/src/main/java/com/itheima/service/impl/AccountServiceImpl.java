package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账户的业务层实现类
 * <p>
 * 事务控制应该都是在业务层的
 */
@Service("accountService")
public class AccountServiceImpl implements IAccountService {

    @Autowired // 因为只有一个dao所以可以直接使用Autowired
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

    public void transfer(String sourceName, String targetName, Float money) {
        System.out.println("transfer....");
        //2.1.根据名称查询转出账户
        Account source = accountDao.findAccountByName(sourceName);
        //2.2.根据名称查询转入账户
        Account target = accountDao.findAccountByName(targetName);
        //2.3.转出账户的存款减去转账金额
        source.setMoney(source.getMoney() - money);
        //2.4.转入账户的存款加转账金额
        target.setMoney(target.getMoney() + money);
        //2.5.更新转出账户
        accountDao.updateAccount(source);

        int i = 1 / 0;

        //2.6.更新转入账户
        accountDao.updateAccount(target);
    }
}
