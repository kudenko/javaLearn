package app.library.controller;

import app.library.model.Author;
import app.library.model.Book;
import app.library.service.AuthorService;
import app.library.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    private final Logger logger = LoggerFactory.getLogger(BookController.class);

    @PostMapping("/creation")
    protected String createBook(@ModelAttribute("book") @Valid Book book, @RequestParam("authorId") Long authorId, BindingResult result,
                                Model model, HttpServletRequest request) {

        request.setAttribute("viewName", "addBook");

        if (result.hasErrors()) {
            model.addAttribute("error", result.getAllErrors());
            return "addBook";
        }

        logger.info("Request parameters: name: {}, countPages: {}", book.getName(), book.getCountPages());
        bookService.addBook(book, authorId);
        model.addAttribute("success", "Book Was successfully Added!!! You can add another one.");
        List<Author> authors = authorService.getAuthors();
        model.addAttribute("authors", authors);
        logger.info("Redirecting to form");
        return "addBook";
    }

    @GetMapping("/creation/form")
    protected String getAuthors(Model model, HttpServletRequest request) {
        logger.info("Get list of authors");
        List<Author> authors = authorService.getAuthors();
        request.setAttribute("viewName", "addBook");
        model.addAttribute("authors", authors);
        model.addAttribute("book", new Book());
        if (authors.isEmpty()) {
            logger.info("Authors are empty. Redirecting for creation.");
            return "addEmptyAuthor";
        }
        logger.info("Redirecting for books displaying");
        return "addBook";
    }

    @GetMapping
    protected String getAllBooks(@RequestParam(required = false) String name, Model model, HttpServletRequest request) {
        List<Book> books = bookService.getBooks(name);
        request.setAttribute("viewName", "allBooks");
        model.addAttribute("books", books);
        return "allBooks";
    }

    @GetMapping("/search/form")
    protected String getBookFindForm(HttpServletRequest request) {
        request.setAttribute("viewName", "findBook");
        return "findBook";
    }
}
