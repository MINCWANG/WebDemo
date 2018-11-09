package cn.seecu.bookstore.dao;

import cn.seecu.bookstore.bean.OrderItem;

import java.util.List;

/**
 * @Auther: Wang MC
 * @Date: 2018/10/27 23:51
 * @Description: 约束对bs_orderitem表的操作
 */
public interface OrderItemDao {
    /**
     * 保存订单项
     *
     * @param item
     * @return
     */
    int saveOrderItem(OrderItem item);

    /**
     * 根据订单项id查询所属订单项
     * @param orderId
     * @return
     */
    List<OrderItem> getOrderItemListByOrderId(String orderId);

    /**
     * 批量插入订单项
     * @param params
     */
    void batchSaveOrderItem(Object[][] params);

}
