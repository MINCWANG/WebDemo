package cn.seecu.bookstore.test;

import cn.seecu.bookstore.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @program: WebDemo
 * @description: JDBCUtils测试
 * @author: Mr.Wang
 * @create: 2018-09-04 00:02
 **/
public class TestJDBCUtils {
    @Test
    public void test() {
        Connection conn = JDBCUtils.getConn();
        System.out.println(conn);
        JDBCUtils.releaseConn(conn);
    }
    @Test
    public void testHello() {
        System.out.println("helloworld");
    }
}

