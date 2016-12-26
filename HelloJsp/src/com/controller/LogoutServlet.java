package com.controller;

import com.util.Util;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by hdhamee on 12/13/16.
 */
public class LogoutServlet extends javax.servlet.http.HttpServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        HttpSession session = request.getSession(false);
        if(null != session){
            session.removeAttribute("token");
            session.removeAttribute("userId");
            session.invalidate();
            Util.removeCookie(response,"TokenCookie");
        }
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        rd.forward(request,response);
    }
}