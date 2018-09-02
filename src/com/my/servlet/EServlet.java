package com.my.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class EServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.转发
        // 绝对路径
//      request.getRequestDispatcher("/a/1.html").forward(request,response);
        // 相对路径
//        request.getRequestDispatcher("a/1.html").forward(request,response);


        // 2.重定向
        // 绝对路径
//        String contextPath = request.getContextPath();
//        response.sendRedirect("/a/1.html");
        // 相对路径
        response.sendRedirect("../a/1.html");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
