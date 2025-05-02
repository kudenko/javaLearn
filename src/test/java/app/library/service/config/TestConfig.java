package app.library.service.config;

import app.library.repository.AuthorRepository;
import app.library.repository.BookRepository;
import app.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Bean
    public BookService bookService() {
        return new BookService(bookRepository, authorRepository);
    }

}
