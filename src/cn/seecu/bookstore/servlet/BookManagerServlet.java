package cn.seecu.bookstore.servlet;

import cn.seecu.bookstore.bean.Book;
import cn.seecu.bookstore.bean.Page;
import cn.seecu.bookstore.service.BookService;
import cn.seecu.bookstore.service.impl.BookServiceImpl;
import cn.seecu.bookstore.utils.WebUtils;
import com.mysql.fabric.xmlrpc.base.Value;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookManagerServlet extends BaseServlet {
    private BookService service = new BookServiceImpl();

    /**
     * 查询所有图书的方法
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getBookList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 通过service 查询到图书的集合
        List<Book> bookList = service.getBookList();
        // 将booklist存到域中
        request.setAttribute("booklist", bookList);
        // 交给book_manger.jsp页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);

    }

    protected void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 上一个页面的全路径
        String referer = request.getHeader("referer");
        // 获取参数 图书id
        String bookId = request.getParameter("bookId");
        // 调用service处理删除
        boolean b = service.delBook(bookId);
        // 删除后回到图书显示页面
//        response.sendRedirect(request.getContextPath()+"/pages/manager/book_manager.jsp");
        // 跳转到删除之前的页面
//        response.sendRedirect(request.getContextPath() + "/manager/BookManagerServlet?method=findPage");
        response.sendRedirect(referer);
    }


    protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //将请求参数封装为book对象
        Book book = WebUtils.param2Bean(request, new Book());
        book.setImgPath("/static/img/default.jsp");
        // 调用service将图书存到数据库中
        boolean b = service.addBook(book);
        // 重定向到图书显示页面
        //System.out.println(request.getContextPath());
        response.sendRedirect(request.getContextPath() + "/manager/BookManagerServlet?method=findPage&pageNumber="+Integer.MAX_VALUE);

    }


    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取修改之前的分页路径
        String referer = request.getHeader("referer");
        // 存到request域中共享referer
        request.setAttribute("referer",referer);
        // 获取图书id
        String bookId = request.getParameter("bookId");
        // 查找图书
//        System.out.println(bookId);
        Book book = service.getBook(bookId);
        // 将图书存到域中
//        System.out.println(book);
        request.setAttribute("book", book);
        // 转发到book_edit.jsp页面
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
    }


    protected void editBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数中的referer
        String referer = request.getParameter("referer");
        // 将参数封装为对象
        Book book = WebUtils.param2Bean(request, new Book());
        // 调用service完成修改
//        System.out.println("Edit" + book);
        boolean b = service.editBook(book);
        // 跳转到book_manger.jsp页面显示所有图书信息
//        response.sendRedirect(request.getContextPath() + "/manager/BookManagerServlet?method=findPage");
        response.sendRedirect(referer);
    }

    protected void findPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = WebUtils.getPath(request);
        String pageNumber = request.getParameter("pageNumber");
        // 设置size 每页显示多少条记录
        int size = 2;
        // 调用service处理业务
        Page<Book> page = service.getPage(pageNumber, size);
        // 将路径设置给page的对象
        page.setPath(path);
        // 存到域中
        request.setAttribute("page", page);
        // 转发到book_manager.jsp
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);

    }
}
