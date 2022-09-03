package com.itheima.factory;

import com.itheima.service.IAccountService;
import com.itheima.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用于创建Service的代理对象的工厂
 */
public class BeanFactory {
    private IAccountService accountService;
    private TransactionManager txManager;

    // 用于Spring的IOC依赖注入
    public void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    //这个方法用于spring的IOC依赖注入
    public final void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    //获取Service的代理对象
    public IAccountService getAccountService() {
        IAccountService service = (IAccountService) Proxy.newProxyInstance(this.accountService.getClass().getClassLoader(), this.accountService.getClass().getInterfaces(),
                new InvocationHandler() {
                    // 添加事务的支持
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if ("test".equals(method.getName())) { // 这个方法没有增强就不叫切入点
                            return method.invoke(args);
                        }
                        Object returnValue = null;
                        try {
                            //1.开启事务
                            txManager.beginTransaction();
                            //2.执行操作
                            returnValue = method.invoke(accountService, args);
                            //3.提交事务
                            txManager.commit();
                            //4.返回结果
                            return returnValue;
                        } catch (Exception e) {
                            //5.回滚操作
                            txManager.rollback();
                            throw new RuntimeException(e);
                        } finally {
                            //6.释放事务
                            txManager.release();
                        }
                    }
                });
        return service;
    }
}
