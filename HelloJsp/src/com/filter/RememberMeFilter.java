package com.filter;

import com.util.Util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by hdhamee on 12/26/16.
 */
public class RememberMeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String token = Util.getCookieValue(request, "TokenCookie");

        if (token != null) {
            System.out.println("token got");
            // get session
            HttpSession session = request.getSession(false);
            if (null == session){
                System.out.println("session null in filter");
                request.getSession().setAttribute("token", token);
            }else {
                System.out.println("session not null in filter ");
                session.setAttribute("token", token);
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {

    }
}
