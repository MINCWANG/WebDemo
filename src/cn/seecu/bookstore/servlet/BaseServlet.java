package cn.seecu.bookstore.servlet;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class BaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //this代表子类，request和子类的一样
        // 获取页面的请求参数 method的值
        String method = request.getParameter("method");
        //获取子类类型
        Class<? extends BaseServlet> aClass = this.getClass();
        try {
            //方法名和形参 确定一个方法
            Method declaredMethod = aClass.getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            //调用方法
            declaredMethod.invoke(this, request, response);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
