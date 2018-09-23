package cn.seecu.bookstore.servlet;

import cn.seecu.bookstore.bean.User;
import cn.seecu.bookstore.service.UserService;
import cn.seecu.bookstore.service.impl.UserServiceImpl;
import cn.seecu.bookstore.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UserServlet extends BaseServlet {
    private UserService service = new UserServiceImpl();

//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request, response);
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // 获取请求方式参数
//        String method = request.getParameter("method");
//        if ("login".equals(method)) {
//            // 调用注册方法
//            login(request,response);
//        } else if ("regist".equals(method)) {
//            // 调用注册方法
//            regist(request,response);
//
//        }
//    }


    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取账号和密码
 /*       String username = request.getParameter("username");
        String password = request.getParameter("password");*/
        //2.封装为User对象

        /*User user = new User(null, username, password, null);*/
        User user = WebUtils.param2Bean(request, new User());
        //3.调用UserDAO查询用户

        User login = service.login(user);
//        User usernameAndPassword = dao.getUserByUsernameAndPassword(user);
        //4.判断
        System.out.println(login);
        if (login == null) {
            // 查询失败，用户不存在，转发到登录的页面
            String errorMsg = "用户名或密码错误";
            request.setAttribute("errorMsg", errorMsg);
            request.getRequestDispatcher(request.getContextPath()+"/pages/user/login.jsp").forward(request, response);
        } else {
            System.out.println("success");
            // 查询成功，用户存在，重定向到成功页面
            response.sendRedirect(request.getContextPath()+"/pages/user/login_success.jsp");
        }
    }

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户账号密码邮箱
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        User user = new User(null, username, password, email);
        boolean regist = service.regist(user);
//        int saveUser = dao.saveUser(user);
        if (regist) {
            // 注册成功，数据库插入成功，重定向注册成功页面

            response.sendRedirect("/pages/user/regist_success.jsp");
        } else {

            String registFial = username + "已经存在";
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.setAttribute("registFila", registFial);
            //注册失败,数据库已有相同账号,转发到注册页面
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }


    }
}
