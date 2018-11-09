package cn.seecu.bookstore.service.impl;

import cn.seecu.bookstore.bean.*;
import cn.seecu.bookstore.dao.BookDao;
import cn.seecu.bookstore.dao.OrderDao;
import cn.seecu.bookstore.dao.OrderItemDao;
import cn.seecu.bookstore.dao.impl.BookDaoImpl;
import cn.seecu.bookstore.dao.impl.OrderDaoImpl;
import cn.seecu.bookstore.dao.impl.OrderItemDaoImpl;
import cn.seecu.bookstore.service.OrderService;

import java.util.Date;
import java.util.List;

/**
 * @Auther: Wang MC
 * @Date: 2018/10/28 22:44
 * @Description: 处理订单服务的实现类
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(ShoppingCart cart, User user) {
        // 1、将购物车转化为订单对象
        // 创建订单id【唯一，便于售后查看】
        String id = System.currentTimeMillis() + "" + user.getId();
        // 当前时间就是创建订单时间
        Date date = new Date();
        // 当前订单的state状态  0 未发货 1 已发货 2订单完成
        int state = 0;

        Order order = new Order(id, cart.getTotalCount(), cart.getTotalAmount(), date, state, user.getId());

        // 保存order到数据库中
        orderDao.saveOrder(order);
        // 创建2个二维数组

        // 2、将购物项转化为订单项
        // 获取购物车的购物项
        List<CartItem> cartItemList = cart.getCartItemList();
        // 批量修改图书的库存销量的数组 第一维:sql次数 集合长度
        Object[][] bookParams = new Object[cartItemList.size()][];
        // 批量保存订单项的数组
        Object[][] orderParamas = new Object[cartItemList.size()][];
        // 遍历数据交给二位数组
        int i = 0;
        for (CartItem cartItem : cartItemList) {
            Book book = cartItem.getBook();
            int sales = book.getSales() + cartItem.getCount(); // 计算出售 后的销量
            int stock = book.getStock() - cartItem.getCount(); // 计算出售 后的库存
            if (stock < 0){
                throw new RuntimeException("库存不能小于0");
            }
            book.setSales(sales);
            book.setStock(stock);
            // 调用bookDao更新修改后的图书销量与库存
            bookParams[i] = new Object[]{book.getSales(),book.getStock(), book.getId()};
            orderParamas[i] = new Object[]{book.getTitle(), book.getAuthor(), book.getImgPath(), book.getPrice(), cartItem.getCount(), cartItem.getAmount(), id};
            // 将购物项转化为订单项
            i++;
        }
        bookDao.batchUpdateStockAndSales(bookParams);
        orderItemDao.batchSaveOrderItem(orderParamas);
        // 返回订单编号
        return id;
    }

    @Override
    public List<Order> getOrderListByUserId(int userId) {

        return orderDao.getOrderListById(userId);
    }

    @Override
    public List<Order> getOrderList() {
        return orderDao.getOrderList();
    }

    @Override
    public boolean updateState(String orderId, int state) {
        return orderDao.updateState(orderId, state) > 0;
    }


}
