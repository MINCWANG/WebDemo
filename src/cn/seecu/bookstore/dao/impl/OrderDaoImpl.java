package cn.seecu.bookstore.dao.impl;

import cn.seecu.bookstore.bean.Order;
import cn.seecu.bookstore.dao.BaseDAO;
import cn.seecu.bookstore.dao.OrderDao;

import java.util.List;

/**
 * @Auther: Wang MC
 * @Date: 2018/10/27 22:20
 * @Description: 实现OrderDao的操作
 */
public class OrderDaoImpl extends BaseDAO<Order> implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "INSERT INTO bs_order(id,total_count ,total_amount ,order_time ,state,user_id ) VALUES(?,?,?,?,?,?)";
        return this.upDate(sql,order.getId(),order.getTotalCount(),order.getTotalAmount(),order.getOrderTime(),order.getState(),order.getUserId());
    }

    @Override
    public List<Order> getOrderListById(int userId) {
        String sql = "SELECT id,total_count totalCount,total_amount totalAmount,order_time orderTime,state,user_id userId FROM bs_order WHERE user_id = ? ORDER BY order_time DESC";
        return this.getBeanList(sql,userId);
    }

    @Override
    public int updateState(String orderId, int state) {
        String sql = "UPDATE bs_order SET state = ? WHERE id=?";
        return this.upDate(sql,state,orderId);
    }

    @Override
    public List<Order> getOrderList() {
        String sql = "SELECT 1id,total_count totalCount,total_amount totalAmount,order_time orderTime,state,user_id userId FROM bs_order ORDER BY order_time DESC";
        return this.getBeanList(sql);
    }
}
