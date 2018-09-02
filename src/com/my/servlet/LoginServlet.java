package com.my.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username);
        System.out.println("!!!!!!!!!!!!!");
        if ("admin".equals(username) && "123456".equals(password)){
            // 转发
            //  req.getRequestDispatcher("success.html").forward(req,resp);
            // 登录成功 重定向到成功页面
            resp.sendRedirect("success.html");
        }else {
            // 登录失败 重定向到失败页面
            resp.sendRedirect("fail.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req, resp);
    }
}
