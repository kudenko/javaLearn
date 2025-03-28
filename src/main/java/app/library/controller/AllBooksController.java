package app.library.controller;

import app.library.model.Book;
import app.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class AllBooksController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    protected String getAllBooks(@RequestParam(required = false) String name, Model model) {
        List<Book> books = Optional.ofNullable(name)
                .map(bookRepository::findBooksByName)
                .orElseGet(() -> bookRepository.findAll());
        model.addAttribute("books", books);
       return "allBooks";
    }
}
