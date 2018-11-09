package cn.seecu.bookstore.servlet;

import cn.seecu.bookstore.bean.Order;
import cn.seecu.bookstore.service.OrderService;
import cn.seecu.bookstore.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderManagerServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();
    /**
     * 管理员查看所有订单
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getAllOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orderList = orderService.getOrderList();
        // 存到域中
        request.setAttribute("orderList",orderList);
        // 转发到order_manager.jsp显示
        request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request,response);
    }

    /**
     * 管理员发货
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void sendGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、获取orderId
        String orderId = request.getParameter("orderId");
        // 2、设置订单修改之后的状态【0-1】
        int state = 1;
        // 3、调用OrderService处理业务
        orderService.updateState(orderId, state);
        // 4、回到之前的页面
        response.sendRedirect(request.getHeader("referer"));

    }
}
