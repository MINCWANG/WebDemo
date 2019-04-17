package cn.seecu.bookstore.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EncodeFilter extends HttpFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {


        String requestURL = String.valueOf(request.getRequestURL());
        if (requestURL.contains(".css") || requestURL.contains(".js") || requestURL.contains(".png") || requestURL.contains(".jpg")) {
            chain.doFilter(request, response);
        } else {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            chain.doFilter(request, response);
        }
    }
}
