package cn.seecu.bookstore.servlet;

import cn.seecu.bookstore.bean.User;
import cn.seecu.bookstore.dao.UserDAO;
import cn.seecu.bookstore.dao.impl.UserDAOImpl;
import cn.seecu.bookstore.service.UserService;
import cn.seecu.bookstore.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    private UserService service = new UserServiceImpl();
//    private UserDAO dao = new UserDAOImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取账号和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //2.封装为User对象
        User user = new User(null, username, password, null);
        //3.调用UserDAO查询用户
        User login = service.login(user);
//        User usernameAndPassword = dao.getUserByUsernameAndPassword(user);
        //4.判断
        if (login == null) {
            // 查询失败，用户不存在，转发到登录的页面
            String errorMsg = "用户名或密码错误";
            request.setAttribute("errorMsg",errorMsg);
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else {
            // 查询成功，用户存在，重定向到成功页面
            response.sendRedirect("/pages/user/login_success.jsp");
        }
    }
}
