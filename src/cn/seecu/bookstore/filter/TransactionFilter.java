package cn.seecu.bookstore.filter;

import cn.seecu.bookstore.utils.JDBCUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFilter extends HttpFilter {

    /**
     * 统一处理事务的Filter
     *
     * @param request
     * @param response
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 获取数据库连接
        // Connection conn = JDBCUtils.getConn();
        Connection conn = JDBCUtils.getConnection();

        //    开启事务
        try {
            conn.setAutoCommit(false);
            //    事务操作
            chain.doFilter(request, response);
            // 如果没有发生异常则提交事务
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //    如果发生异常
            try {
                conn.rollback();
                response.sendRedirect(request.getContextPath()+"/pages/error/error.jsp");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        }finally {
            // JDBCUtils.closeConn();
            JDBCUtils.closeConnection();
        }

    }
}
