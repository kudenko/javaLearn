package app.library.controller;

import app.JavaLearnApp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

@WebServlet(urlPatterns = "/init")
public class InitServlet extends HttpServlet {

    @Override
    public void init(){
        JavaLearnApp.initMyLearnApp();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/html/init.jsp").forward(req, resp);
        resp.getWriter().write("I'm starting to work");
    }
}
