package edu.soft1.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletDemo",value = "/ServletDemo")
public class ServletDemo extends HttpServlet {
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        request.getParameter("");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }



}
