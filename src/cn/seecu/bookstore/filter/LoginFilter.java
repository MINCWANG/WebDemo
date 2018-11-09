package cn.seecu.bookstore.filter;

import cn.seecu.bookstore.bean.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter extends HttpFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 当用户未登录
            String errorMsg = "用户名或密码错误";
            request.setAttribute("errorMsg", errorMsg);
            request.getRequestDispatcher("pages/user/login.jsp").forward(request, response);
        }else {
            // 用户已登录，放行通过，让目标资源处理请求
            chain.doFilter(request,response);
        }

    }
}
