package cn.seecu.bookstore.dao.impl;

import cn.seecu.bookstore.bean.*;
import cn.seecu.bookstore.dao.OrderDao;
import cn.seecu.bookstore.dao.OrderItemDao;
import org.junit.Test;


import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDaoImplTest {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    @Test
    public void test1() {
        // 1、判断用户s是否登录
        // 2、如果登录，在servlet中获取购物车对象，获取用户对象
        ShoppingCart shoppingCart = new ShoppingCart();
        Book book1 = new Book(1, "Android开发", "王敏超", "/static/img/default.jpg", 20, 0, 100);
        Book book2 = new Book(2, "java开发", "王敏超", "/static/img/default.jpg", 20, 0, 100);
        Book book3 = new Book(3, "PHP开发", "王敏超", "/static/img/default.jpg", 20, 0, 100);
        shoppingCart.addBook2Cart(book1);
        shoppingCart.addBook2Cart(book2);
        shoppingCart.addBook2Cart(book2);
        shoppingCart.addBook2Cart(book3);
        shoppingCart.addBook2Cart(book3);
        shoppingCart.addBook2Cart(book3);
        User user = new User(1, "wang", "123456", "admin@163.com");
        // 在servlet中调用OrderService中的createOrder方法处理业务
        // 3、调用orderService 将购物车转化为订单，购物项转化为订单项
        // 3.1、将购物车转化为订单
        String id = System.currentTimeMillis() + "" + user.getId();
        int state = 0;
        // 当前时间就是订单创建的时间，Date使用Until下的包
        Date date = new Date();
        Order order = new Order(id, shoppingCart.getTotalCount(), shoppingCart.getTotalAmount(), date, state, user.getId());
        List<CartItem> cartItemList = shoppingCart.getCartItemList();
        System.out.println(orderDao.saveOrder(order));
        for (CartItem cartItem : cartItemList) {
            Book book = cartItem.getBook();
            OrderItem orderItem = new OrderItem(null,book.getTitle(),book.getAuthor(),book.getImgPath(),book.getPrice(),cartItem.getCount(),cartItem.getAmount(),id);

            System.out.println(orderItemDao.saveOrderItem(orderItem));
        }
        // 4、调用OrderDao和OrderItemDao将数据保存到数据库中


    }

    /**
     * 注意：
     *      1、插入订单项之前必须要先将对应的订单插到数据库中
     *      2、使用外键时，外键的值在关联表中要存在
     */
    @Test
    public void saveOrder() {
    }

    @Test
    public void getOrderListById() {
    }

    @Test
    public void updateState() {
    }

    @Test
    public void getOrderList() {
    }
}