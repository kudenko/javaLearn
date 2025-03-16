package app.library.controller;

import app.library.model.Author;
import app.library.repository.AuthorRepositoryCustom;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.util.List;

@Configurable
@WebServlet(urlPatterns = "/books/creation/form")
public class AddBookFormServlet extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(AddBookFormServlet.class);

    private AuthorRepositoryCustom<Author> authorRepository;

    @Override
    public void init() throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
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
    }

    @Override
    public void destroy() {
        logger.info("Destroy started.");
        super.destroy();
        logger.info("Destroy completed.");
    }

    @Autowired
    public void setAuthorRepository(AuthorRepositoryCustom<Author> authorRepository) {
        this.authorRepository = authorRepository;
    }
}
