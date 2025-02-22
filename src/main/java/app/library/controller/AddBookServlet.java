package app.library.controller;

import app.library.author.Author;
import app.library.config.DatabaseConnectionManager;
import app.library.config.PropertyConfig;
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

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/addBook")
public class AddBookServlet extends HttpServlet {
    private BookRepositoryCustom<Book> bookRepository;
    private DatabaseConnectionManager connectionManager;
    private AuthorRepositoryCustom<Author> authorRepository;

    @Override
    public void init() throws ServletException {
        connectionManager = new DatabaseConnectionManager(new PropertyConfig());
        authorRepository = new AuthorRepository(connectionManager);
        bookRepository = new BookRepository(connectionManager);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authors = authorRepository.findAll();
        req.setAttribute("authors", authors);
        if(authors.isEmpty()) {
            req.getRequestDispatcher("/html/addEmptyAuthor.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("/html/addBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int countPages = Integer.parseInt(req.getParameter("countPages"));
        long authorId = Long.parseLong(req.getParameter("authorId"));

        try {
            bookRepository.save(new Book(name, countPages, authorRepository.findById(authorId)));
            req.setAttribute("success", "Book Was Successfully Added!!! You can add another one.");
        } catch (AuthorRepositoryException | BookRepositoryException e) {
            req.setAttribute("error", "Error, while adding a Book. Please try again or contact administrator");
        }
        req.getRequestDispatcher("/html/addBook.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        connectionManager.close();
        super.destroy();
    }
}
