package com.lxy.springcore;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author LXY
 * @email 403824215@qq.com
 * @date 2018/7/24 14:48
 */
public class TransactionalTest {
    private static ApplicationContext applicationContext;
    private static DataSource dataSource;
    private static Logger logger;

    @BeforeClass
    public static void beforeClass()
    {
        applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        dataSource = applicationContext.getBean(DataSource.class);
    }

    @Test
    public void test_databaseMetaDate() throws SQLException {
        Connection connection = dataSource.getConnection();
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        System.out.println("supportsANSI92FullSQL" + databaseMetaData.supportsANSI92FullSQL());
        System.out.println("supportsTransactions" + databaseMetaData.supportsTransactions());
        System.out.println("supportsSavepoints" + databaseMetaData.supportsSavepoints());
        System.out.println("supportsTransactionIsolationLevel" + databaseMetaData.supportsTransactionIsolationLevel(Connection.TRANSACTION_REPEATABLE_READ));
    }

    @Test
    public void test_commit()
    {
        Connection connection = null;
        Statement statement = null;
        try {
            //获取数据库连接
            connection = dataSource.getConnection();

            //关闭自动提交
            connection.setAutoCommit(false);

            //创建命令
            statement = connection.createStatement();

            //执行SQL
            int updateEffect = statement.executeUpdate("update memo_group set name='JDBC' where id = 2");

            int deleteEffect = statement.executeUpdate("delete from memo_group where id=12");

            //提交事务
            if(updateEffect == 1 && deleteEffect == 1)
            {
                connection.commit();
                System.out.println("SQL commit");
            }
            else
            {
                connection.rollback();
                System.out.println("SQL rollback");
            }

        } catch (SQLException e) {
            if(connection != null)
            {
                try {
                    connection.rollback();
                    System.out.println("SQLException roolback");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        finally {
            if(statement != null)
            {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null)
            {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test_transactionTemplate()
    {
        TransactionTemplate transactionTemplate = (TransactionTemplate) applicationContext.getBean("transactionTemplate");

        Object retValue = transactionTemplate.execute(new TransactionCallback<Object>() {

            public Object doInTransaction(TransactionStatus status) {
                //进行数据库操作
                DataSourceUtils.getConnection(dataSource);
                return null;
            }
        });
    }
}
