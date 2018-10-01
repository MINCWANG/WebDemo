package cn.seecu.bookstore.utils;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @program: WebDemo
 * @description: 数据库连接单元
 * @author: Mr.Wang
 * @create: 2018-09-03 23:46
 **/
public class JDBCUtils {
    /**
     * 数据库连接池对象
     */
    private static DataSource source = new ComboPooledDataSource("webDataSource");

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConn() {

        Connection conn = null;
        try {
            conn = source.getConnection();
//            System.out.println("建立连接");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 释放数据库连接池
     *
     * @param conn
     */
    public static void releaseConn(Connection conn) {
        if (conn != null) {
            try {

                conn.close();
//                System.out.println("释放连接");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
