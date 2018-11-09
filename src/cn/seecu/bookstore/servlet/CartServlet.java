package cn.seecu.bookstore.servlet;

import cn.seecu.bookstore.bean.Book;
import cn.seecu.bookstore.bean.CartItem;
import cn.seecu.bookstore.bean.ShoppingCart;
import cn.seecu.bookstore.service.BookService;
import cn.seecu.bookstore.service.impl.BookServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

public class CartServlet extends BaseServlet {
    private BookService service = new BookServiceImpl();

    /**
     * 处理购物车内删除购物项CartItem的请求
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void delItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数中的bookId
        String booKId = request.getParameter("booKId");
        HttpSession session = request.getSession();
        // 获取session域中当前的购物车对象
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart != null) {

            cart.delCartItemByBookId(booKId);
        }
        // 重定向到之前的页面
        response.sendRedirect(request.getHeader("referer"));

    }

    /**
     * 处理修改数量的请求
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前购物项的booKId和count
        String bookId = request.getParameter("bookId");
        String count = request.getParameter("count");
        // 获取cart对象
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        // 判断购物车是否为空，不为空则通过购物项的bookId更新count
        if (cart != null) {
            cart.updateCount(bookId, count);
//            System.out.println("update-cart");
        }
        // 通过bookId获取cart的Map，得到购物项对象
        CartItem cartItem = cart.getMap().get(bookId);
        // 回到修改之前的页面
        // response.sendRedirect(request.getHeader("referer"));
        // 响应数据给ajax cart.totalCount，cart.totalAmount,cartItem.amount
        Gson gson = new Gson();
        // 将数据存到map中
        HashMap<Object, Object> map = new HashMap<>();
        map.put("totalCount", cart.getTotalCount());
        map.put("totalAmount", cart.getTotalAmount());
        map.put("amount", cartItem.getAmount());
        String json = gson.toJson(map);
        response.getWriter().write(json);
    }

    /**
     * 处理清空购物车的请求
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前的session对象
        HttpSession session = request.getSession();
        // 从当前的session域中查找到cart对象
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart != null) {
            cart.clearCart();
        }
        response.sendRedirect(request.getHeader("referer"));

    }

    /**
     * 处理用户添加图书到购物车的请求
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void addBook2Cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、获取用户提交的图书id
        String bookId = request.getParameter("bookId");
        // 2、调用BookService根据id查询图书对象
        Book book = service.getBook(bookId);
        // 获取book的title属性
        String title = book.getTitle();

        // 3、保存到购物车
        // 3.1、购物车保存到session【一个会话代表一个用户，共享一个购物车】
        HttpSession session = request.getSession();
        // 将获取到的title设置到session域中，传入主页刚刚添加显示
        session.setAttribute("title", title);
        // 获取购物车并且判断是否存在
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        // 3.2、如果购物车对象不存在，创建一个新的对象存到域中
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }
        // 如果购物车对象存在，可以直接使用方法
        cart.addBook2Cart(book);
//        System.out.println(cart.getCartItemList());
        // 跳转到到之前的页面
        // response.sendRedirect(request.getHeader("referer"));
        // 创建Gson 对 map对象转为json
        Gson gson = new Gson();
        HashMap<Object, Object> map = new HashMap<>();
        // 将购物车总count存到key为count
        map.put("count",cart.getTotalCount());
        // 将图书标题存到key为title
        map.put("title", book.getTitle());
        String json = gson.toJson(map);
        response.getWriter().write(json);
    }
}
