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

@WebServlet(urlPatterns = "/authors")
public class AllAuthorsServlet extends HttpServlet {

    private AuthorRepositoryCustom<Author> authorRepository;
    private final Logger logger = LoggerFactory.getLogger(AllAuthorsServlet.class);

    @Override
    public void init() throws ServletException {
        logger.info("Author repository initialization");
        authorRepository = new AuthorRepository();
        logger.info("Author repository initialization successful");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authors;
        
        String email = req.getParameter("email");
        if(email != null && !email.isEmpty()) {
            logger.info("Getting info for email: {}", email);
            authors = authorRepository.findByEmail(email);
            logger.info("Getting info for email: {} successful", email);
        } else {
            logger.info("Getting all authors");
            authors = authorRepository.findAll();
            logger.info("Getting all authors successful");
        }
        req.setAttribute("authors", authors);
        logger.info("Redirect to all authors");
        req.getRequestDispatcher("/html/allAuthors.jsp").forward(req, resp);
        logger.info("Redirect to all authors successful");
    }

    @Override
    public void destroy() {
        logger.info("Destroy started.");
        super.destroy();
        logger.info("Destroy completed.");
    }
}
