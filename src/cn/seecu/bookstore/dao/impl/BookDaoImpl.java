package cn.seecu.bookstore.dao.impl;

import cn.seecu.bookstore.bean.Book;
import cn.seecu.bookstore.bean.Page;
import cn.seecu.bookstore.dao.BaseDAO;
import cn.seecu.bookstore.dao.BookDao;

import java.util.List;

/**
 * @program: WebDemo
 * @description: 实现BookDao
 * @author: Mr.Wang Mc
 * @create: 2018-09-26 23:56
 */
public class BookDaoImpl extends BaseDAO<Book> implements BookDao {

    @Override
    public Book getBookById(String id) {
        String sql = "SELECT id,title,author,price,sales,stock,img_path imgPath FROM bs_book WHERE id=?";
//        Book book = getBean(sql, id);

        return this.getBean(sql, id);
    }


    @Override
    public List<Book> getBookList() {
        /**
         * 查询时，如果表的字段在JavaBean的字段不一致，一定使用别名
         * BeanUtils在封装对象时，会根据查找到的表的字段去JavaBean中找对应的set方法后面的属性
         */
        String sql = "SELECT id,title,author,price,sales,stock,img_path imgPath " +
                "FROM bs_book";


        return getBeanList(sql);
    }

    @Override
    public int saveBook(Book book) {
//        int id,String title,String author,double price,int sales,int stock,String imgPath

        String sql = "INSERT INTO bs_book(title,author,price,sales,stock,img_path) VALUES(?,?,?,?,?,?)";

        return this.upDate(sql, book.getTitle(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int updateBook(Book newBook) {
        String sql = "UPDATE bs_book SET title=?,author=?,price=?,sales=?,stock=?,img_path=? WHERE id=?";
        return this.upDate(sql,newBook.getTitle(),newBook.getAuthor(),newBook.getPrice(),newBook.getSales(),newBook.getStock(),newBook.getImgPath(),newBook.getId());
    }

    @Override
    public void batchUpdateStockAndSales(Object[][] params) {
        String sql = "UPDATE bs_book SET sales=?,stock=?  WHERE id=?";
        this.bitchUpdate(sql,params);
    }

    @Override
    public int delBook(String id) {
        String sql = "DELETE FROM bs_book WHERE id=?";
        return this.upDate(sql,id);
    }

    @Override
    public Page<Book> getPageBook(Page<Book> page) {
        // BookService中调用此方法，相当于有了三个已知属性：pageNumber,size，index
        // 查询图书记录的总条数
        String sql = "SELECT COUNT(*) FROM bs_book";
        int count = (int) this.getCount(sql);
        // 将查询到的值设置给page对象 totalPage，totalCount都已知了
        page.setTotalCount(count);
        // 查询分页显示的图书集合
        String dataSql = "SELECT id,title,author,price,sales,stock,img_path imgPath FROM bs_book LIMIT ?,? ";
        List<Book> bookList = this.getBeanList(dataSql, page.getIndex(), page.getSize());
        // 将查询到的图书集合设置给page对象
        page.setData(bookList);

        return page;
    }

    @Override
    public Page<Book> getPageBookByPrice(Page<Book> page, double min, double max) {
        //
        String sql = "SELECT COUNT(*) FROM bs_book WHERE price>= ? AND price<= ?";
        //
        int count = (int) this.getCount(sql, min, max);
        // 将查询到的值设置给page对象，totalPage
        page.setTotalCount(count);
        // 查询分页显示的图书集合
        sql = "SELECT id,title,author,price,sales,stock,img_path imgPath FROM bs_book WHERE price>=?  AND price<= ? LIMIT ?,? ";
        List<Book> list = this.getBeanList(sql, min, max, page.getIndex(), page.getSize());
        // 将图书集合设置给page对象
        page.setData(list);
        return page;
    }

}
