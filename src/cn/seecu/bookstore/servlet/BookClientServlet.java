package cn.seecu.bookstore.servlet;

import cn.seecu.bookstore.bean.Book;
import cn.seecu.bookstore.bean.Page;
import cn.seecu.bookstore.service.BookService;
import cn.seecu.bookstore.service.impl.BookServiceImpl;
import cn.seecu.bookstore.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookClientServlet extends BaseServlet {
    private BookService service = new BookServiceImpl();

    protected void findPageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取页码 minPrice maxPrice
        String pageNumber = request.getParameter("pageNumber");
        String min = request.getParameter("min");
        String max = request.getParameter("max");
        // 2.设置每页显示的记录数
        int size = 4;
        // 3.截取访问路径
        String path = WebUtils.getPath(request);
        // 4.调用service查询分页数据
        Page<Book> pageByPrice = service.getPageByPrice(pageNumber, size, min, max);
        // 设置路径
        pageByPrice.setPath(path);
        // pageByPrice对象存到域中
        request.setAttribute("page",pageByPrice);
        // 转发到index.jsp
        request.getRequestDispatcher("/pages/list/list.jsp").forward(request,response);


    }

    protected void findPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取页码
        String pageNumber = request.getParameter("pageNumber");
        //2. 设置每页显示的记录数
        int size = 4;
        //3.截取访问路径
        String path = WebUtils.getPath(request);
        //4.调用service查询分页数据
        Page<Book> page = service.getPage(pageNumber, size);
        // 设置路径
        page.setPath(path);
        // System.out.println(page);
        // 将page设置到request域中
        request.setAttribute("page",page);
        // 转发到index.jsp
        request.getRequestDispatcher("/pages/list/list.jsp").forward(request,response);

    }

}
