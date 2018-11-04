package cn.seecu.bookstore.utils;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    // 将数据库连接设置为单例模式
    // private static Connection conn;
    // 我们可以为每个线程分配一个数据库连接   HashMap时线程不安全的
    private static Map<Thread, Connection> map = new ConcurrentHashMap<>();
    //  ThreadLocal：此类z中维护了一个静态的线程安全的map，默认的key是当前的线程我们可以使用ThreadLocal绑定一个对象
    private static ThreadLocal<Connection> local = new ThreadLocal<>();

    /**
     * 从ThreadLocal中获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        //    1、获取Connection
        Connection conn = local.get();
        //    2、判断conn是否为空
        if (conn == null) {
            //    第一次获取conn
            //    创建connection
            try {
                conn = source.getConnection();
                //    将数据库连接和当前线程进行绑定
                local.set(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static void closeConnection(){
    //    1、通过local获取数据库连接
        Connection conn = local.get();
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {

            }
        }
    //    移出当前线程绑定的数据库连接
        local.remove();
    }

        /**
         * 获取数据库连接
         *
         * @return
         */
        public static Connection getConn () {

//         Connection conn = null;
//         try {
//             conn = source.getConnection();
// //            System.out.println("建立连接");
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
     /*   if (conn == null) {
            try {
                conn = source.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/
            // 使用当前的线程对象从map中获取数据库连接
            Connection conn = map.get(Thread.currentThread());
            if (conn == null) {
                //    如果当前线程没有绑定数据库连接
                try {
                    conn = source.getConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //    将当前数据库连接和线程绑定
                map.put(Thread.currentThread(), conn);
            }
            return conn;
        }

        /**
         * 释放当前线程绑定的数据库连接方法
         */
        public static void closeConn(){
            //    获取数据库连接
            Connection conn = map.get(Thread.currentThread());
            //    关闭连接
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //    清除map中的存的线程数据
            map.remove(Thread.currentThread());

        }

        /**
         * 释放数据库连接池
         *
         * @param conn
         */
        public static void releaseConn(Connection conn){
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
