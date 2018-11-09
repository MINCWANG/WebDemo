package cn.seecu.bookstore.servlet;

import cn.seecu.bookstore.bean.Order;
import cn.seecu.bookstore.bean.ShoppingCart;
import cn.seecu.bookstore.bean.User;
import cn.seecu.bookstore.service.OrderService;
import cn.seecu.bookstore.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class OrderClientServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();
    /**
     * 处理用户的结账请求
     *  1、判断用户是否登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void checkOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // OrderService：处理业务时需要使用cart，user 都存在session域中
        //1、获取用户登录信息
        HttpSession session = request.getSession();
        // 用胡登录成功时会将user存在session域中，
        User user = (User) session.getAttribute("user");

        // 如果在session中获取到user对象
//        if (user != null) {
            // 用户已登录，调用service完成结账
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            String orderId = orderService.createOrder(cart, user);
            cart.clearCart();
            // 跳转到结账成功页面，显示订单编号
            request.setAttribute("orderId",orderId);

            request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(request,response);
//        }else {
//            // 用户未登录，给出提示跳转到登录页面
//            String msg = "结账需要登录";
//            request.setAttribute("errorMsg",msg);
//            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
//        }

    }

    protected void getOrderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
//
//        if (user != null) {
            // 根据userId查询订单
            List<Order> orderList = orderService.getOrderListByUserId(user.getId());

            // 将查询到的list存到request域中
            request.setAttribute("orderList",orderList);
            // 转发到order.jsp页面显示数据
            request.getRequestDispatcher("/pages/order/order.jsp").forward(request,response);


    }

    /**
     * 用户确认收货
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void takeGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        int state = 2;
        orderService.updateState(orderId, state);
        response.sendRedirect(request.getHeader("referer"));

    }
}
