package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("accountService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true) // 参数和使用xml配置时一样
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountDao accountDao;

    public Account findAccountBuId(Integer id) {
        return accountDao.findAccountById(id);
    }

    //因为这里的事务读写都需要，所以需要单独配置
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void transfer(String sourceName, String targetName, float money) {
        System.out.println("transfer....");
        //2.1根据账户名查询转出账户
        Account sourceAccount = accountDao.findAccountByName(sourceName);
        //2.2根据账户名查询转入账户
        Account targetAccount = accountDao.findAccountByName(targetName);
        //2.3转出账户减少money
        sourceAccount.setMoney(sourceAccount.getMoney() - money);
        //2.3转入账户增加money
        targetAccount.setMoney(targetAccount.getMoney() + money);

        //2.4更新账户
        accountDao.updateAccount(sourceAccount);

        int i = 1 / 0;

        accountDao.updateAccount(targetAccount);
    }
}
