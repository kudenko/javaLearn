package app.library.controller;

import app.library.config.DatabaseConnectionManager;
import app.library.config.PropertyConfig;
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

@WebServlet(urlPatterns = "/allBooks")
public class AllBooksServlet extends HttpServlet {

    private BookRepositoryCustom<Book> bookRepository;
    private DatabaseConnectionManager connectionManager;

    @Override
    public void init() throws ServletException {
        connectionManager = new DatabaseConnectionManager(new PropertyConfig());
        bookRepository = new BookRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookRepository.findAll();
        req.setAttribute("books", books);
        req.getRequestDispatcher("/html/allBooks.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        connectionManager.close();
        super.destroy();
    }
}
