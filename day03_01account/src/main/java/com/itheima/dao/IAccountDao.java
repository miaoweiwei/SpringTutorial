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

    /**
     * 根据名称查询账户
     *
     * @param accountName 账户名称
     * @return 如果有唯一的一个结果就返回，如果没有结果就返回null
     * 如果结果集超过一个就跑出异常
     */
    Account findAccountByName(String accountName);
}
