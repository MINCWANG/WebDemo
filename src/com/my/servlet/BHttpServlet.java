package com.my.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BHttpServlet extends HttpServlet {
    /*
    * HttpServletRequest : 请求报文
    *      浏览器请求时的请求报文，请求到达服务器将报文解析封装为HttpServletRequest对象
    *      获取：请求到服务器时，服务器直接创建HttpServletRequest然后传入到service方法中，最终传入到doGet
    *      作用：获取请求报文中的所有数据
    *           1.获取请求参数【input提交的数据】
    *           2.获取url地址内容
    *           3.获取请求转发器
    *               转发：
    *                   1.通过HttpServletRequest对象发起
    *                   2.转发后，url地址栏没有改变
    *                   3.浏览器只发起一次请求，最终显示转发后的结果
    *                   4.浏览器不知道转发的发生
    *                   5.服务器内部资源有2个资源文件处理了请求
    *
    *
    *
    * HttpServletResponse：响应报文
    *       服务器给浏览器的响应报文，请求到达服务器时，服务器将信息封装起来创建HttpServletResponse对象
    *       获取：请求到达服务器，服务器创建HttpServletResponse传入到service中最终传入doGet
    *       HttpServletResponse：
    *           1.通过HttpServletResponse 的流写入html片段   HttpServletResponse.getWrite().write("<h1>XXX</h1>")
    *           2.通过HttpServletResponse 重定向   HttpServletResponse.sendRedirect("source文件")
    *
    * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 根据参数名获取请求参数值
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        System.out.println(name +"______"+ age);

        // 主机地址

        String serverName = req.getServerName();
        int serverPort = req.getServerPort();
        String contextPath = req.getContextPath();
        String queryString = req.getQueryString();
        System.out.println("http://"+serverName+":"+serverPort+"/"+contextPath+"?"+queryString);

        resp.getWriter().write("  __+++"+name+age);
        req.getRequestDispatcher("success.html").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
