package app.library.controller;

import app.library.model.Book;
import app.library.storage.BookRepository;
import app.library.storage.BookRepositoryCustom;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/findBook")
public class FindBookServlet extends HttpServlet {

    private BookRepositoryCustom<Book> bookBookRepository;

    @Override
    public void init() throws ServletException {
        bookBookRepository = new BookRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/html/findBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        List<Book> books = bookBookRepository.findBooksByName(name);
        req.setAttribute("books", books);
        req.getRequestDispatcher("/html/allBooks.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
