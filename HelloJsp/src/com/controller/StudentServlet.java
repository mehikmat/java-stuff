package com.controller;

import com.model.Student;
import com.service.StudentService;

import javax.servlet.RequestDispatcher;
import java.io.IOException;

/**
 * Created by hdhamee on 12/13/16.
 */
public class StudentServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        String fName = request.getParameter("fName");
        String lName = request.getParameter("lName");
        String roll = request.getParameter("roll");

        Student s = new Student();
        s.setfName(fName);

        StudentService ss = new StudentService();
        boolean saved =ss.save(s);


        request.setAttribute("message","test");
        request.setAttribute("student",s);
        request.setAttribute("message","test");

        RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
        rd.forward(request,response);


    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.getWriter().print("hello world from get");

    }
}
// servelet API.jar and JSP API .jar