package cn.seecu.bookstore.dao;

import cn.seecu.bookstore.bean.Book;
import cn.seecu.bookstore.bean.Page;

import java.util.List;

/**
 * 规范对bs_book的操作
 */
public interface BookDao {
    /**
     * 根据id查询图书
     * @param id
     * @return
     */
    Book getBookById(String id);

    /**
     * 查询所有图书
     * @return
     */
    List<Book> getBookList();

    /**
     * 根据id保存图书
     * @param book
     * @return
     */
    int saveBook(Book book);

    /**
     * 通过newBook更新图书
     *  oldBook已经存在数据库
     *  newBook替换老的图书信息
     *  id 不变
     * @param newBook
     * @return
     */
    int updateBook(Book newBook);

    /**
     * 批量修改图书和库存的方法
     * @param params
     */
    void batchUpdateStockAndSales(Object[][] params);


    /**
     * 根据id删除图书
     * @param id
     * @return
     */
    int delBook(String id);

    /**
     * 查询分页图书
     * @param page
     * @return
     */
    Page<Book> getPageBook(Page<Book> page);

    /**
     * 通过价格区间查询图书集合分页
     * @param page
     * @param min
     * @param max
     * @return
     */
    Page<Book> getPageBookByPrice(Page<Book> page,double min,double max);

}
