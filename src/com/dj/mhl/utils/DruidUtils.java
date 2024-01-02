package com.dj.mhl.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Druid连接池+Apache-Utils，用于获取、释放连接，并且简化了crud操作。
 */
public class DruidUtils {
    private static DataSource ds;

    //类加载时，创建数据源
    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src//druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //从数据源得到一个连接
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    //把连接归还给数据源、关闭statement、关闭结果集
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
