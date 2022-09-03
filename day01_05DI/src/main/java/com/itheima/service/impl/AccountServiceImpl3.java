package com.itheima.service.impl;

import com.itheima.service.IAccountService;

import java.util.*;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl3 implements IAccountService {
    private String[] myStr;
    private List<String> myList;
    private Set<String> mySet;
    private Map<String, String> myMap;
    private Properties myProps;

    //因为原理上还是set方法的注入，所以要提供set方法。
    public void setMyStr(String[] myStr) {
        this.myStr = myStr;
    }

    public void setMyList(List<String> myList) {
        this.myList = myList;
    }

    public void setMySet(Set<String> mySet) {
        this.mySet = mySet;
    }

    public void setMyMap(Map<String, String> myMap) {
        this.myMap = myMap;
    }

    public void setMyProps(Properties myProps) {
        this.myProps = myProps;
    }

    public void saveAccount() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "AccountServiceImpl3{" + '\n' +
                "myStr=" + Arrays.toString(myStr) + ",\n" +
                "myList=" + myList + ",\n" +
                "mySet=" + mySet + ",\n" +
                "myMap=" + myMap + ",\n" +
                "myProps=" + myProps + ",\n" +
                '}';
    }
}
