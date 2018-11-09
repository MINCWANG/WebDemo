package cn.seecu.bookstore.service;

import cn.seecu.bookstore.bean.Book;
import cn.seecu.bookstore.bean.Page;

import java.util.List;

/**
 * @program: WebDemo
 * @description: 处理图书操作的业务逻辑
 * @author: Mr.Wang Mc
 * @create: 2018-09-28 19:33
 */
public interface BookService {
    /**
     * 添加图书
     * @param book
     * @return
     */
   boolean addBook(Book book);

    /**
     * 删除图书
     * @param bookId
     * @return
     */
   boolean delBook(String bookId);

    /**
     * 编辑图书
     * @param book
     * @return
     */
   boolean editBook(Book book);

    /**
     * 查询所有图书
     * @return
     */
   List<Book> getBookList();

    /**
     * 通过id查询一条图书记录
     * @return
     */
    Book getBook(String bookId);

    /**
     * 通过pageNumber和size获取分页图书的集合
     * @param pageNumber
     * @param size
     * @return
     */
    Page<Book> getPage(String pageNumber, int size);

    /**
     * 根据价格区间查询图书分页集合
     * @param pageNumber
     * @param size
     * @param min
     * @param max
     * @return
     */
    Page<Book> getPageByPrice(String pageNumber, int size,String min,String max);


}


