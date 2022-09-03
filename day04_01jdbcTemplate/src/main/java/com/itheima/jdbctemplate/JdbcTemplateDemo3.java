package com.itheima.jdbctemplate;

import com.itheima.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


/**
 * JdbcTemplate 的CRUD操作
 */
public class JdbcTemplateDemo3 {
    public static void main(String[] args) {
        //1.获取IOC容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.根据id获取bean对象
        JdbcTemplate jt = (JdbcTemplate) ac.getBean("jdbcTemplate");
        //3.执行操作
        //保存
        // jt.update("insert into account(name, money) values (?,?)", "eee", 3333f);
        //更新
        //jt.update("update account set name = ?, money = ? where id = ?", "test", 4567f, 7);
        //删除
        //jt.update("delete from account where id = ?", 5);
        //查询所有,使用 我们自定义Account的封住策略
//        List<Account> accounts = jt.query("select * from account where money > ?;", new AccountRowMapper(), 1000f);
        //使用Spring为我们提供的封住策略
//        List<Account> accounts = jt.query("select * from account where money > ?;", new BeanPropertyRowMapper<Account>(Account.class), 1000f);
//        System.out.println(Arrays.toString(accounts));
        //查询一个
//        List<Account> accounts = jt.query("select * from account where id = ?;", new BeanPropertyRowMapper<Account>(Account.class), 1);
//        System.out.println(accounts.isEmpty() ? "没有内容" : accounts.get(0));
        //查询返回一行一列（使用聚合函数，但不加group by句子）
        Long count = jt.queryForObject("select count(*) from account where money > ?", Long.class, 1000f); // 避免返回值大于Integer的最大值，所以使用Long
        System.out.println(count);
    }
}

/**
 * 定义Account的封住策略
 */
class AccountRowMapper implements RowMapper<Account> {

    /**
     * 把结果集中的数据封装到Account中，然后Spring把每个Account加到集合中
     *
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account();
//        account.setId(resultSet.getInt("id"));
//        account.setName(resultSet.getString("name"));
//        account.setMoney(resultSet.getFloat("money"));

        //使用反射设置属性值
        ResultSetMetaData rsmd = resultSet.getMetaData();
        // 通过ResultSetMetaData获取结果集中的列数
        int columnCount = rsmd.getColumnCount();
        for (int j = 0; j < columnCount; j++) {
            try {
                // 获取每个列的列名
                String columName = rsmd.getColumnName(j + 1);
                Field field = Account.class.getDeclaredField(columName);
                field.setAccessible(true);

                // 获取每个列的列值
                Object columValue = resultSet.getObject(j + 1);
                // field.set(account, columValue);
                field.set(account, resultSet.getObject(columName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return account;
    }
}