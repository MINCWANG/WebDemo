package cn.seecu.bookstore.servlet;

import cn.seecu.bookstore.bean.User;
import cn.seecu.bookstore.service.UserService;
import cn.seecu.bookstore.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {
    private UserService service = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    //    private UserDAO dao = new UserDAOImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

            String registFial = username+"已经存在";
            request.setAttribute("username",username);
            request.setAttribute("email",email);
            request.setAttribute("registFila",registFial);
            //注册失败,数据库已有相同账号,转发到注册页面
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }


    }
}
