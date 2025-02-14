package app.library.controller;

import app.JavaLearnApp;
import app.library.author.Author;
import app.library.config.DatabaseConnectionManager;
import app.library.config.PropertyConfig;
import app.library.storage.AuthorRepository;
import app.library.storage.AuthorRepositoryCustom;
import app.library.storage.Repository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/addAuthor")
public class AddAuthorServlet extends HttpServlet {
    private AuthorRepositoryCustom<Author> authorRepository;
    private DatabaseConnectionManager connectionManager;

    @Override
    public void init() throws ServletException {
        connectionManager = new DatabaseConnectionManager(new PropertyConfig());
        authorRepository = new AuthorRepository(connectionManager);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/html/addAuthor.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");

        authorRepository.save(new Author(name, lastname, email));

        resp.sendRedirect("/javaLearnApp/success");
    }

    @Override
    public void destroy() {
        connectionManager.close();
        super.destroy();
    }
}
