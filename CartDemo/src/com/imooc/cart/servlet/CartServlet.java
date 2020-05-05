package com.imooc.cart.servlet;

import com.imooc.cart.data.Cart;
import com.imooc.cart.data.LocalCache;
import com.imooc.cart.data.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 *
 * TODO {file desc}
 *
 * @version 1.0
 */
public class CartServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Objects.equals("/cart/cart.do", req.getServletPath())) {
            String productId = req.getParameter("productId");
            if (null != productId) {
                Product product = LocalCache.getProduct(Long.valueOf(productId));
                LocalCache.addCart(product);
            }
            resp.sendRedirect("/cart/list.do");
        } else if (Objects.equals("/cart/delete.do", req.getServletPath())) {
            String productId = req.getParameter("productId");
            if (null != productId) {
                LocalCache.delCart(Long.valueOf(productId));
            }
            resp.sendRedirect("/cart/list.do");
        } else if (Objects.equals("/cart/incr.do", req.getServletPath())) {
            String productId = req.getParameter("productId");
            if (null != productId) {
                LocalCache.incrCart(Long.valueOf(productId));
            }
            resp.sendRedirect("/cart/list.do");
        } else if (Objects.equals("/cart/decr.do", req.getServletPath())) {
            String productId = req.getParameter("productId");
            if (null != productId) {
                LocalCache.decrCart(Long.valueOf(productId));
            }
            resp.sendRedirect("/cart/list.do");
        } else if (Objects.equals("/cart/settlement.do", req.getServletPath())) {
            String[] cartIds = req.getParameterValues("carts");
            int totalPrice = 0;
            for (int i = 0; i < cartIds.length; i++) {
                Cart cart = LocalCache.getCart(Long.valueOf(cartIds[i]));
                totalPrice += cart.getTotalPrice();
                LocalCache.delCart(cart.getId());
            }
            req.setAttribute("totalPrice", totalPrice);
            req.getRequestDispatcher("/WEB-INF/views/biz/settlement.jsp").forward(req, resp);
        } else if (Objects.equals("/cart/list.do", req.getServletPath())) {
            req.setAttribute("carts", LocalCache.getCarts());
            req.getRequestDispatcher("/WEB-INF/views/biz/cart.jsp").forward(req, resp);
        }
    }
}
