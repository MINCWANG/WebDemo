package cn.seecu.bookstore.dao;

import cn.seecu.bookstore.bean.Order;

import java.util.List;

/**
 * @Auther: Wang MC
 * @Date: 2018/10/27 22:15
 * @Description: 约束对bs_order表的操作
 */
public interface OrderDao {
    /**
     * 保存订单
     * @param order
     * @return
     */
    int saveOrder(Order order);

    /**
     * 根据用户id查询所属订单集合
     * @param userId
     * @return
     */
    List<Order> getOrderListById(int userId);

    /**
     * 修改订单状态
     * @param orderId
     * @param state
     * @return
     */
    int updateState(String orderId,int state);

    /**
     * 管理员查询所有订单集合
     * @return
     */
    List<Order> getOrderList();
}
