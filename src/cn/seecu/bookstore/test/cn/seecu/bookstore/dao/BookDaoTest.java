package cn.seecu.bookstore.dao;

import cn.seecu.bookstore.bean.Book;
import cn.seecu.bookstore.bean.Page;
import cn.seecu.bookstore.dao.impl.BookDaoImpl;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {
    BookDao dao = new BookDaoImpl();
    @Test
    public void getBookById() {
        Book book = dao.getBookById("1");
        System.out.println(book);
    }

    @Test
    public void getBookList() {
        List<Book> bookList = dao.getBookList();
        Iterator<Book> iterator = bookList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }


    @Test

    public void saveBook() {
        Book book = new Book(null, "Android开发", "王敏超", "/static/img/default.jpg", 20, 0, 100);
        int i = dao.saveBook(book);
        System.out.println(i);
    }

    @Test
    public void updateBook() {
        Book book = new Book(1, "水浒传", "卢本伟", "/static/img/default.jpg", 30, 50, 50);
        int i = dao.updateBook(book);
        System.out.println(i);
    }

    @Test
    public void delBook() {
        int i = dao.delBook("2");
        System.out.println(i);
    }
    @Test
    public void testPage(){
        // 测试查询分页数据
        // 模拟用户访问过程
        // 用户传入
        int pageNumber = 2;
        // servlet中设置
        int size = 5;
        // 调用service的getPage方法处理业务
        Page<Book> page = new Page<Book>();
        page.setPageNumber(pageNumber);
        page.setSize(size);
        // 调用BookDao的getPageBook方法查询相关数据
        Page<Book> pageBook = dao.getPageBook(page);
        System.out.println(pageBook);
    }
    @Test
    public void testPageByPrice(){
        // 测试查询分页数据
        // 模拟用户按照价格查询
        // 用户传入
        int pageNumber = 2;
        // servlet中设置
        int size = 4;
        double min = 20;
        double max = 30;
        // 调用service的getPage方法处理业务
        Page<Book> page = new Page<>();
        page.setPageNumber(pageNumber);
        page.setSize(size);
        // 调用BookDao的getPageBook方法查询相关数据
        Page<Book> pageBook = dao.getPageBookByPrice(page,min,max);
        System.out.println(pageBook);
    }
}