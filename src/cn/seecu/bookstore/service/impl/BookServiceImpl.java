package cn.seecu.bookstore.service.impl;

import cn.seecu.bookstore.bean.Book;
import cn.seecu.bookstore.bean.Page;
import cn.seecu.bookstore.dao.BookDao;
import cn.seecu.bookstore.dao.impl.BookDaoImpl;
import cn.seecu.bookstore.service.BookService;

import java.util.List;

/**
 * @program: WebDemo
 * @description: 实现BookService
 * @author: Mr.Wang Mc
 * @create: 2018-09-28 19:42
 */
public class BookServiceImpl implements BookService {
    // 对数据库的所有操作都是通过dao完成的
    private BookDao dao = new BookDaoImpl();

    @Override
    public boolean addBook(Book book) {
        return dao.saveBook(book) > 0;
    }

    @Override
    public boolean delBook(String bookId) {
        return dao.delBook(bookId) > 0;
    }

    @Override
    public boolean editBook(Book book) {
        return dao.updateBook(book) > 0;
    }

    @Override
    public List<Book> getBookList() {
        return dao.getBookList();
    }

    @Override
    public Book getBook(String bookId) {

        return dao.getBookById(bookId);
    }

    @Override
    public Page<Book> getPage(String pageNumber, int size) {
        // 创建Page对象
        Page<Book> page = new Page<>();
        // 默认查询第一页
        int i = 1;
        try {
            i = Integer.parseInt(pageNumber);
        } catch (NumberFormatException e) {
//            e.printStackTrace();
        }
        page.setSize(size);
        page.setPageNumber(i);

        return dao.getPageBook(page);
    }

    @Override
    public Page<Book> getPageByPrice(String pageNumber, int size, String min, String max) {
        Page<Book> page = new Page<>();
        // 转换异常时，默认设置页码为1
        int no = 1;
        try {
            no = Integer.parseInt(pageNumber);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Double minVal = Double.MIN_NORMAL;
        Double maxVal = Double.MAX_VALUE;
        try {
            minVal = Double.parseDouble(min);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            maxVal = Double.parseDouble(max);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        page.setSize(size);
        page.setPageNumber(no);
        // 调用dao完成通过价格区间的分页数据查询
        Page<Book> pageBookByPrice = dao.getPageBookByPrice(page, minVal, maxVal);
        return pageBookByPrice;
    }
}
