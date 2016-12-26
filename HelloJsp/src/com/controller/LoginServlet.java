package com.controller;

import com.model.Login;
import com.service.LoginService;
import com.util.Util;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by hdhamee on 12/13/16.
 */
public class LoginServlet extends javax.servlet.http.HttpServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        // get session
        HttpSession session = request.getSession(false);

        // check if new session; that's first time login
        if(null == session) {
            System.out.println("session null");
            String uname = request.getParameter("uname");
            String pass = request.getParameter("pass");
            boolean remember = "true".equalsIgnoreCase(request.getParameter("remember"));

            // successful login
            Login s = new Login();
            s.setUname(uname);
            s.setPass(pass);
            LoginService ss = new LoginService();
            boolean successful = ss.check(s);

            if (successful){
                request.setAttribute("Uname","first time");
                request.setAttribute("Remember","starting remembering");

                if (remember) {
                    String token = UUID.randomUUID().toString();
                    // rememberService.save(token, user);
                    Util.addCookie(response, "TokenCookie", token, 30*24*60*60);
                } else {
                    //rememberService.delete(user);
                    Util.removeCookie(response, "TokenCookie");
                }

                request.getSession(true).setAttribute("userId",uname);

                RequestDispatcher rd = request.getRequestDispatcher("data.jsp");
                rd.forward(request,response);

            }else {
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request,response);
            }

        }else if (null != session.getAttribute("token")){
            System.out.println("session not null");
            //second time; no login needed

            request.setAttribute("Uname","secnod time");
            request.setAttribute("Remember","remembered");

            RequestDispatcher rd = request.getRequestDispatcher("data.jsp");
            rd.forward(request,response);
        }else if ( null != session.getAttribute("userId")){
            request.setAttribute("Uname","secnod time");
            request.setAttribute("Remember","remembered");
            System.out.println("session still on");
            RequestDispatcher rd = request.getRequestDispatcher("data.jsp");
            rd.forward(request,response);
        }else {
            System.out.println("uname and pass null");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request,response);
        }

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }
}