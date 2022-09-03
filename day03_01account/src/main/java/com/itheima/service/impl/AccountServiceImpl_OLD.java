package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import com.itheima.utils.TransactionManager;

import java.util.List;

/**
 * 账户的业务层实现类
 * <p>
 * 事务控制应该都是在业务层的
 */
public class AccountServiceImpl_OLD implements IAccountService {

    private IAccountDao accountDao;
    private TransactionManager txManager;

    //这个方法用于spring的IOC依赖注入
    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    //这个方法用于spring的IOC依赖注入
    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    public List<Account> findAllAccount() {
        try {
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            List<Account> accounts = this.accountDao.findAllAccount();
            //3.提交事务
            txManager.commit();
            //4.返回结果
            return accounts;
        } catch (Exception e) {
            //5.回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            //6.释放事务
            txManager.release();
        }
    }

    public Account findAccountById(Integer accountId) {
        try {
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            Account account = this.accountDao.findAccountById(accountId);
            //3.提交事务
            txManager.commit();
            //4.返回结果
            return account;
        } catch (Exception e) {
            //5.回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            //6.释放事务
            txManager.release();
        }
    }

    public void saveAccount(Account account) {
        try {
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            this.accountDao.saveAccount(account);
            //3.提交事务
            txManager.commit();
            //4.返回结果
        } catch (Exception e) {
            //5.回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            //6.释放事务
            txManager.release();
        }
    }

    public void updateAccount(Account account) {
        try {
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            this.accountDao.updateAccount(account);
            //3.提交事务
            txManager.commit();
            //4.返回结果
        } catch (Exception e) {
            //5.回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            //6.释放事务
            txManager.release();
        }
    }

    public void deleteAccount(Integer accountId) {
        try {
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            this.accountDao.deleteAccount(accountId);
            //3.提交事务
            txManager.commit();
            //4.返回结果
        } catch (Exception e) {
            //5.回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            //6.释放事务
            txManager.release();
        }
    }

    public void transfer(String sourceName, String targetName, Float money) {
        try {
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
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
            //3.提交事务
            txManager.commit();
            //4.返回结果
        } catch (Exception e) {
            //5.回滚操作
            txManager.rollback();
            e.printStackTrace();
//            throw new RuntimeException(e);
        } finally {
            //6.释放事务
            txManager.release();
        }
    }
}
