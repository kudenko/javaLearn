package app.library.controller;

import app.library.model.Book;
import app.library.storage.BookRepository;
import app.library.storage.BookRepositoryCustom;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/books")
public class AllBooksServlet extends HttpServlet {

    private BookRepositoryCustom<Book> bookRepository;
    private final Logger logger = LoggerFactory.getLogger(AllBooksServlet.class);


    @Override
    public void init() throws ServletException {
        logger.info("Book repository initialization");
        bookRepository = new BookRepository();
        logger.info("Book repository initialization successful");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        List<Book> books;
        if (name != null && !name.isEmpty()) {
            logger.info("Getting books by name {} parameter", name);
            books = bookRepository.findBooksByName(name);
            logger.info("Getting books by name {} parameter successful", name);
        } else {
            logger.info("Getting all books");
            books = bookRepository.findAll();
            logger.info("Getting all books successful");
        }
        req.setAttribute("books", books);
        logger.info("Redirecting to all books");
        req.getRequestDispatcher("/html/allBooks.jsp").forward(req, resp);
        logger.info("Redirecting to all books successful");
    }

    @Override
    public void destroy() {
        logger.info("Destroy started.");
        super.destroy();
        logger.info("Destroy completed.");
    }
}
