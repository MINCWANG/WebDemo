package cn.seecu.bookstore.dao.impl;

import cn.seecu.bookstore.bean.OrderItem;
import cn.seecu.bookstore.dao.BaseDAO;
import cn.seecu.bookstore.dao.OrderItemDao;

import java.util.List;

/**
 * @Auther: Wang MC
 * @Date: 2018/10/28 00:14
 * @Description: 实现OrderItemDao
 */
public class OrderItemDaoImpl extends BaseDAO<OrderItem> implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem item) {
        String sql = "INSERT INTO bs_orderitem(title,author,img_path,price,count,amount,order_id) VALUES(?,?,?,?,?,?,?)";
        return this.upDate(sql,item.getTitle(),item.getAuthor(),item.getImgPath(),item.getPrice(),item.getCount(),item.getAmount(),item.getOrderId());
    }

    @Override
    public List<OrderItem> getOrderItemListByOrderId(String orderId) {
        String sql = "SELECT id,title,author,img_path imgPath,price,amount,count,order_id orderId FROM bs_orderitem WHERE order_id=?";
        return this.getBeanList(sql, orderId);
    }

    @Override
    public void batchSaveOrderItem(Object[][] params) {
        String sql = "INSERT INTO bs_orderitem(title,author,img_path,price,count,amount,order_id) VALUES(?,?,?,?,?,?,?)";
        this.bitchUpdate(sql,params);
    }
}
