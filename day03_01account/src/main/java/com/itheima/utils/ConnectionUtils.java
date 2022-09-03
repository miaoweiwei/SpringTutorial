package com.itheima.utils;

import javax.sql.DataSource;
import java.sql.Connection;

/*
 * 连接的工具类，它同于从数据源中获取一个连接并且实现和一个线程的绑定
 */
public class ConnectionUtils {
    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取当前线程上的连接
     *
     * @return
     */
    public Connection getThreadConnection() {
        //1.先从ThreadLocal上获取
        Connection conn = tl.get();
        try {
            //2.判断当前线程上是否有连接
            if (conn == null) {
                //3.从数据源中获取一个连接，并且存入ThreadLocal中
                conn = dataSource.getConnection();
                tl.set(conn);
            }
            //4.返回当前线程上的连接
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 当我们把连接close()之后应该把连接从线程中移除出去。
     * 把连接和线程解绑
     */
    public void removeConnection() {
        tl.remove();
    }
}
