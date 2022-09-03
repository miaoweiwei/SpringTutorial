package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class AccountServiceImpl implements IAccountService {
    private IAccountDao accountDao;

    private TransactionTemplate transactionTemplate;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    public Account findAccountBuId(final Integer id) {
        return transactionTemplate.execute(new TransactionCallback<Account>() {
            public Account doInTransaction(TransactionStatus transactionStatus) {
                return accountDao.findAccountById(id);
            }
        });
    }

    public void transfer(final String sourceName, final String targetName, final float money) {
        transactionTemplate.execute(new TransactionCallback<Object>() {
            public Object doInTransaction(TransactionStatus transactionStatus) {
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

                // int i = 1 / 0;

                accountDao.updateAccount(targetAccount);
                return null;
            }
        });
    }
}
