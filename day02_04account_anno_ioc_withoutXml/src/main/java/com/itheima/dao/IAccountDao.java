package com.itheima.dao;

import com.itheima.domain.Account;

import java.util.List;

/**
 * 账户的持久层接口
 */
public interface IAccountDao {

    /**
     * 查询所有的Account
     *
     * @return
     */
    List<Account> findAllAccount();

    /**
     * 根据id查询Account
     *
     * @param accountId
     * @return
     */
    Account findAccountById(Integer accountId);

    /**
     * 保存Account
     *
     * @param account
     */
    void saveAccount(Account account);

    /**
     * 更新Account
     *
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 删除
     *
     * @param accountId
     */
    void deleteAccount(Integer accountId);
}
