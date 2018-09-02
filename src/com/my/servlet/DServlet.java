package com.my.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 浏览器（编码）--》服务器（解码）
 * 服务器（编码）--》浏览器（解码）
 *
 *
 * 1. get请求 乱码
 *
 *
 * 2.post请求 乱码
 *
 * 3.响应乱码
 *      在使用response对象之前设置一个响应头，告诉浏览器如何解析页面，告诉response如何读取
 */

public class DServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + "--" + password);
        response.getWriter().write("成功啦");
    }
}
