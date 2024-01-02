package com.dj.mhl.dao;

import com.dj.mhl.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author SongDongJie
 * @create 2023/12/31 - 22:38
 */
//这里面的都是各个DAO类通用的操作
public class BasicDao<T> {//泛型制定具体类型
    QueryRunner queryRunner = new QueryRunner();

    /**
     * dml操作
     *
     * @param sql   sql语句
     * @param param 可变参数，替代sql语句的占位符
     * @return 返回影响的行数
     */
    public int update(String sql, Object... param) {
        Connection connection = null;
        try {
            connection = DruidUtils.getConnection();
            return queryRunner.update(connection, sql, param);
        } catch (SQLException e) {
            throw new RuntimeException();//编译异常转运行异常
        } finally {
            DruidUtils.close(connection, null, null);
        }
    }

    /**
     * 查询，返回多行多列
     *
     * @param sql   sql语句
     * @param cls   POJO类对应的Class对象
     * @param param 可变参数，替代sql语句的占位符
     * @return 返回List集合，其中包含查询的结果
     */
    public List<T> queryMultiplyRow(String sql, Class<T> cls, Object... param) {
        Connection connection = null;
        try {
            connection = DruidUtils.getConnection();
            return queryRunner.query(connection, sql, new BeanListHandler<T>(cls), param);
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            DruidUtils.close(connection, null, null);
        }
    }

    /**
     * 查询，返回单行多列
     *
     * @param sql   sql语句
     * @param cls   POJO类对应的Class对象
     * @param param 可变参数，替代sql语句的占位符
     * @return 返回一个POJO对象
     */
    public T querySingleRow(String sql, Class<T> cls, Object... param) {
        Connection connection = null;
        try {
            connection = DruidUtils.getConnection();
            return queryRunner.query(connection, sql, new BeanHandler<T>(cls), param);
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            DruidUtils.close(connection, null, null);
        }
    }

    /**
     * 查询，返回单行单列
     *
     * @param sql   sql语句
     * @param param 可变参数，替代sql语句的占位符
     * @return 返回一个结果值
     */
    public Object queryScalar(String sql, Object... param) {
        Connection connection = null;
        try {
            connection = DruidUtils.getConnection();
            return queryRunner.query(connection, sql, new ScalarHandler(), param);
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            DruidUtils.close(connection, null, null);
        }
    }
}
