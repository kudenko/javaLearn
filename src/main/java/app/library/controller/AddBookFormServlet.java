package app.library.controller;

import app.library.model.Author;
import app.library.storage.AuthorRepository;
import app.library.storage.AuthorRepositoryCustom;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/books/creation/form")
public class AddBookFormServlet extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(AddBookFormServlet.class);

    private AuthorRepositoryCustom<Author> authorRepository;

    @Override
    public void init() throws ServletException {
        logger.info("Authors and books repositories initialization");
        authorRepository = new AuthorRepository();
        logger.info("Authors and books repositories initialization completed");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Get list of authors");
        List<Author> authors = authorRepository.findAll();
        req.setAttribute("authors", authors);
        if (authors.isEmpty()) {
            logger.info("Authors are empty. Redirecting for creation.");
            req.getRequestDispatcher("/html/addEmptyAuthor.jsp").forward(req, resp);
        }
        logger.info("Redirecting for books displaying");
        req.getRequestDispatcher("/html/addBook.jsp").forward(req, resp);
        logger.info("Redirecting for books displaying completed");
    }

    @Override
    public void destroy() {
        logger.info("Destroy started.");
        super.destroy();
        logger.info("Destroy completed.");
    }
}
