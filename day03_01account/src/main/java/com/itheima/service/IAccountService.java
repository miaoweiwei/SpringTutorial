package com.itheima.service;

import com.itheima.domain.Account;

import java.util.List;

/**
 * 账户业务层接口
 */
public interface IAccountService {

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
     * 转账
     *
     * @param sourceName 转出账户的名称
     * @param targetName 转入账户的名称
     * @param money      转账金额
     */
    void transfer(String sourceName, String targetName, Float money);
}
