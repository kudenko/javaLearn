package app.library.controller;

import app.library.model.Author;
import app.library.exceptions.AuthorRepositoryException;
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

@WebServlet(urlPatterns = "/authors/creation")
public class AddAuthorServlet extends HttpServlet {
    private AuthorRepositoryCustom<Author> authorRepository;
    private final Logger logger = LoggerFactory.getLogger(AddAuthorServlet.class);

    @Override
    public void init() throws ServletException {
        logger.info("Initialization of authorsRepository");
        authorRepository = new AuthorRepository();
        logger.info("Successful Initialization of authorsRepository");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        logger.info("Request with parameters name: {}, lastName: {}, email {}", name, lastname, email);
        try {
            logger.info("Saving entity");
            authorRepository.save(new Author(name, lastname, email));
            logger.info("Entity saved");
            req.setAttribute("success", "Author Was successfully Added!!! You can add another one.");
        } catch (AuthorRepositoryException e) {
            logger.error(e.toString());
            req.setAttribute("error", "Error, while adding an Author. Please try again or contact administrator");
        }
        logger.info("Redirecting");
        req.getRequestDispatcher("/html/addAuthor.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        logger.info("Destroy started.");
        super.destroy();
        logger.info("Destroy completed.");
    }
}
