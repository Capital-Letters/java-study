package com.imooc.cart.servlet;

import com.imooc.cart.data.LocalCache;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 *
 * 浏览记录Servlet
 *
 * @version 1.0
 */
public class BrowseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Objects.equals(request.getServletPath(), "/browse/list.do")) {
            //从Cookie中取出全部，根据ID在LocalCache中查询出对应的全部Product
            //将Product全部放入List中，并写入request
            request.setAttribute("products", LocalCache.getBrowseLogs());
            request.getRequestDispatcher("/WEB-INF/views/biz/browse_list.jsp").forward(request, response);
        } else if (Objects.equals(request.getServletPath(), "/browse/delete.do")) {
            //从request中取出cookie（按cookie的name匹配）
            //从value字符串中删除相应的ID，把剩余的数据重新放入cookie
//            for (Cookie cookie : request.getCookies()) {
//                if (Objects.equals("productIds", cookie.getName())) {
//                    //do
//                    break;
//                }
//            }
            String productId = request.getParameter("productId");
            if (null != productId) {
                LocalCache.deleteBrowseLog(Long.valueOf(productId));
            }
            response.sendRedirect("/browse/list.do");
        }
    }
}
