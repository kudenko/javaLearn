package app.library.controller;

import app.library.model.Author;
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

@WebServlet(urlPatterns = "/books/creation/form")
public class AddBookFormServlet extends HttpServlet {
    private BookRepositoryCustom<Book> bookRepository;
    private AuthorRepositoryCustom<Author> authorRepository;

    @Override
    public void init() throws ServletException {
        authorRepository = new AuthorRepository();
        bookRepository = new BookRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authors = authorRepository.findAll();
        req.setAttribute("authors", authors);
        if (authors.isEmpty()) {
            req.getRequestDispatcher("/html/addEmptyAuthor.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("/html/addBook.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
