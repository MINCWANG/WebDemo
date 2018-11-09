package cn.seecu.bookstore.service;

import cn.seecu.bookstore.bean.Order;
import cn.seecu.bookstore.bean.ShoppingCart;
import cn.seecu.bookstore.bean.User;

import java.util.List;

/**
 * @Auther: Wang MC
 * @Date: 2018/10/28 22:42
 * @Description: 处理订单的业务逻辑
 */
public interface OrderService {
    /**
     * 处理用户结账时创建订单id的业务
     * @param cart
     * @param user
     * @return
     */
    String createOrder(ShoppingCart cart, User user);

    /**
     * 处理用户根据id查询订单
     * @param userId
     * @return
     */
    List<Order> getOrderListByUserId(int userId);

    /**
     * 管理员查询所有订单
     * @return
     */
    List<Order> getOrderList();

    /**
     * 修改订单的方法
     * @param orderId
     * @param state
     * @return
     */
    boolean updateState(String orderId, int state);
}
