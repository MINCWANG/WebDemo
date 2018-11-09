package cn.seecu.bookstore.dao;

import cn.seecu.bookstore.bean.Book;
import cn.seecu.bookstore.bean.ShoppingCart;
import cn.seecu.bookstore.service.impl.BookServiceImpl;
import org.junit.Test;

/**
 * @Auther: Wang MC
 * @Date: 2018/10/18 13:32
 * @Description:
 */
public class CartTest {
    @Test
    public void test1(){
        // 模拟用户添加图书的流程
        //1、提交bookId给CartServlet
        //2、servlet中使用bookService根据bookId查询Book对象
        //3、从session域中获取shoppingCart对象，如果有直接使用，调用添加book2cart的方法
//                                           如果没有，则创建一个shoppingCart对象存到session域中
        String bookId = "33";
        String bookId1 = "34";
        BookServiceImpl imp = new BookServiceImpl();
        Book book = imp.getBook(bookId);

        Book book1 = imp.getBook(bookId1);
        ShoppingCart shoppingCart = new ShoppingCart();
        // 调用购物车的addBook2Cart方法
        shoppingCart.addBook2Cart(book);
        shoppingCart.addBook2Cart(book);
        shoppingCart.addBook2Cart(book);
        shoppingCart.addBook2Cart(book1);
        shoppingCart.addBook2Cart(book1);
        System.out.println("count "+shoppingCart.getTotalAmount());
        System.out.println("price "+shoppingCart.getTotalCount());
    }
    @Test
    public void test2(){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addBook2Cart(new Book(1,"Java","wangmc",".img",0.1,1,100));
        shoppingCart.addBook2Cart(new Book(1,"Java","wangmc",".img",0.1,1,100));
        shoppingCart.addBook2Cart(new Book(1,"Java","wangmc",".img",0.1,1,100));
        shoppingCart.addBook2Cart(new Book(2,"Java","wangmc",".img",0.2,1,100));
        shoppingCart.addBook2Cart(new Book(2,"Java","wangmc",".img",0.2,1,100));
        System.out.println(shoppingCart.getTotalAmount());
    }

}
