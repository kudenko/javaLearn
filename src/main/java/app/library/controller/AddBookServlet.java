package app.library.controller;

import app.library.model.Author;
import app.library.exceptions.AuthorRepositoryException;
import app.library.exceptions.BookRepositoryException;
import app.library.model.Book;
import app.library.storage.AuthorRepository;
import app.library.storage.AuthorRepositoryCustom;
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

@WebServlet(urlPatterns = "/books/creation")
public class AddBookServlet extends HttpServlet {
    private BookRepositoryCustom<Book> bookRepository;
    private AuthorRepositoryCustom<Author> authorRepository;

    private final Logger logger = LoggerFactory.getLogger(AddBookServlet.class);

    @Override
    public void init() throws ServletException {
        logger.info("Authors and books repositories initialization");
        authorRepository = new AuthorRepository();
        bookRepository = new BookRepository();
        logger.info("Authors and books repositories initialization completed");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int countPages = Integer.parseInt(req.getParameter("countPages"));
        long authorId = Long.parseLong(req.getParameter("authorId"));
        logger.info("Request parameters: name: {}, countPages: {}", name, countPages);

        try {
            logger.info("Saving a book.");
            bookRepository.save(new Book(name, countPages, authorRepository.findById(authorId)));
            req.setAttribute("success", "Book Was successfully Added!!! You can add another one.");
            logger.info("Saving successful.");
        } catch (AuthorRepositoryException | BookRepositoryException e) {
            logger.info("error {}", e.toString());
            req.setAttribute("error", "Error, while adding a Book. Please try again or contact administrator");
        }
        logger.info("Redirecting to form");
        req.getRequestDispatcher("/html/addBook.jsp").forward(req, resp);
        logger.info("Redirecting successful");
    }

    @Override
    public void destroy() {
        logger.info("Destroy started.");
        super.destroy();
        logger.info("Destroy completed.");
    }
}
