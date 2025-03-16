package app.library.controller;

import app.library.model.Author;
import app.library.exceptions.AuthorRepositoryException;
import app.library.exceptions.BookRepositoryException;
import app.library.model.Book;
import app.library.repository.AuthorRepository;
import app.library.repository.AuthorRepositoryCustom;
import app.library.repository.BookRepository;
import app.library.repository.BookRepositoryCustom;
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
@WebServlet(urlPatterns = "/books/creation")
public class AddBookServlet extends HttpServlet {
    private BookRepositoryCustom<Book> bookRepository;
    private AuthorRepositoryCustom<Author> authorRepository;

    private final Logger logger = LoggerFactory.getLogger(AddBookServlet.class);

    @Override
    public void init() throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int countPages = Integer.parseInt(req.getParameter("countPages"));
        long authorId = Long.parseLong(req.getParameter("authorId"));
        logger.info("Request parameters: name: {}, countPages: {}", name, countPages);

        try {
            bookRepository.save(new Book(name, countPages, authorRepository.findById(authorId)));
            req.setAttribute("success", "Book Was successfully Added!!! You can add another one.");
        } catch (AuthorRepositoryException | BookRepositoryException e) {
            logger.info("error {}", e.toString());
            req.setAttribute("error", "Error, while adding a Book. Please try again or contact administrator");
        }
        logger.info("Redirecting to form");
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

    @Autowired
    public void setBookRepository(BookRepositoryCustom<Book> bookRepository) {
        this.bookRepository = bookRepository;
    }
}
