package app.library.controller;

import app.library.model.Author;
import app.library.storage.AuthorRepository;
import app.library.storage.AuthorRepositoryCustom;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/authors/search/form")
public class FindAuthorFormServlet extends HttpServlet {

    private AuthorRepositoryCustom<Author> authorRepository;

    @Override
    public void init() throws ServletException {
        authorRepository = new AuthorRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/html/findAuthor.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
