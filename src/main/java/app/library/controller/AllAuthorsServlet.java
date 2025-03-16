package app.library.controller;

import app.library.model.Author;
import app.library.repository.AuthorRepository;
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
import java.util.Optional;

@Configurable
@WebServlet(urlPatterns = "/authors")
public class AllAuthorsServlet extends HttpServlet {

    private AuthorRepositoryCustom<Author> authorRepository;
    private final Logger logger = LoggerFactory.getLogger(AllAuthorsServlet.class);

    @Override
    public void init() throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Author repository getting authors from Servlet");
        
        List<Author> authors = Optional.ofNullable(req.getParameter("email"))
                .map(authorRepository::findByEmail)
                .orElseGet(authorRepository::findAll);

        req.setAttribute("authors", authors);
        logger.info("Redirect to all authors");
        req.getRequestDispatcher("/html/allAuthors.jsp").forward(req, resp);
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
