package app.library.controller;

import app.library.model.Author;
import app.library.exceptions.AuthorRepositoryException;
import app.library.repository.AuthorRepository;
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

@Configurable
@WebServlet(urlPatterns = "/authors/creation")
public class AddAuthorServlet extends HttpServlet {

    private AuthorRepository authorRepository;
    private final Logger logger = LoggerFactory.getLogger(AddAuthorServlet.class);

    public AddAuthorServlet() {

    }

    @Override
    public void init() throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        logger.info("Request with parameters name: {}, lastName: {}, email {}", name, lastname, email);
        try {
            authorRepository.save(new Author(name, lastname, email));
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

    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
}
