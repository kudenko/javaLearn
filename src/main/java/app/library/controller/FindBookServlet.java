package app.library.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/findBookForm")
public class FindBookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //catch
        //req.setAttribute("errorMessage", "ERROR");
        //req.getRequestDispatcher("/findBookForm").forward(req, resp);
        //add found books to findBook.jsp
        req.getRequestDispatcher("/html/findBook.jsp").forward(req, resp);
    }
}
