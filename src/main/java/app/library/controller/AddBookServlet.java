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

import java.io.IOException;

@WebServlet(urlPatterns = "/books/creation")
public class AddBookServlet extends HttpServlet {
    private BookRepositoryCustom<Book> bookRepository;
    private AuthorRepositoryCustom<Author> authorRepository;

    @Override
    public void init() throws ServletException {
        authorRepository = new AuthorRepository();
        bookRepository = new BookRepository();
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
        super.destroy();
    }
}
