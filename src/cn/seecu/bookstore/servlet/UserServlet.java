package cn.seecu.bookstore.servlet;

import cn.seecu.bookstore.bean.User;
import cn.seecu.bookstore.service.UserService;
import cn.seecu.bookstore.service.impl.UserServiceImpl;
import cn.seecu.bookstore.utils.WebUtils;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class UserServlet extends BaseServlet {
    private UserService service = new UserServiceImpl();

    protected void checkUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        boolean b = service.checkUserByUsername(username);
        if (b){
        //     代表用户名可用
            response.getWriter().write("1");
        }else {
            // 代表用户名已占用
            response.getWriter().write("0");
        }
    }

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



    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取session域
        HttpSession session = request.getSession();
        // 移出session域中的属性
        session.invalidate();
        // 重定向到之前页面
        response.sendRedirect(request.getHeader("referer"));
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取账号和密码
 /*       String username = request.getParameter("username");
        String password = request.getParameter("password");*/
        //2.封装为User对象

        /*User user = new User(null, username, password, null);*/
        User user = WebUtils.param2Bean(request, new User());
        //3.调用UserDAO查询用户

        User loginUser = service.login(user);

//        User usernameAndPassword = dao.getUserByUsernameAndPassword(user);
        //4.判断
//        System.out.println(login);
        if (loginUser == null) {
            // 查询失败，用户不存在，转发到登录的页面
            String errorMsg = "用户名或密码错误";
            request.setAttribute("errorMsg", errorMsg);
            request.getRequestDispatcher("pages/user/login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("user",loginUser);
/**
 * 登录状态的持久化设置
 */
 /*           Cookie[] cookies = request.getCookies();
            if(cookies != null){
                for (Cookie cookie : cookies) {
                    //获取Cookie的名字
                    String name = cookie.getName();
                    if("JSESSIONID".equals(name)){
                        //持久化该Cookie对象
                        cookie.setMaxAge(60);
                        //将Cookie对象发送给浏览器
                        response.addCookie(cookie);
                    }
                }
            }*/
//            System.out.println("success");
            // 查询成功，用户存在，重定向到成功页面
            response.sendRedirect(request.getContextPath()+"/pages/user/login_success.jsp");
        }
    }

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取图片用户输入的图片验证码字符串
        String clientCode = request.getParameter("code");

        HttpSession session = request.getSession();
        // 获取kapche在session域中的验证码字符串
        String serviceCode = (String) session.getAttribute("code");
        // 移出session域中的验证码
        session.removeAttribute("code");
        //获取用户账号密码邮箱
      /*  String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");*/
      if (clientCode!=null && clientCode.equals(serviceCode)){

          User user = WebUtils.param2Bean(request,new User());
          boolean regist = service.regist(user);
//        int saveUser = dao.saveUser(user);

          if (regist) {
              // 注册成功，数据库插入成功，重定向注册成功页面

              response.sendRedirect(request.getContextPath()+"/pages/user/regist_success.jsp");
          } else {

              String errorMsg ="用户已经存在";
              request.setAttribute("errorMsg",errorMsg);
              //注册失败,数据库已有相同账号,转发到注册页面
              request.getRequestDispatcher("pages/user/regist.jsp").forward(request, response);
          }
      }else {
          String errorMsg ="验证码错误";
          request.setAttribute("errorMsg",errorMsg);
          //注册失败,数据库已有相同账号,转发到注册页面
          request.getRequestDispatcher(request.getContextPath()+"/pages/user/regist.jsp").forward(request, response);
      }


    }
}
