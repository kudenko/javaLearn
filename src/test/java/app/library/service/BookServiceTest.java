package app.library.service;

import app.library.model.Author;
import app.library.model.Book;
import app.library.repository.BookRepository;
import app.library.service.config.TestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@ContextConfiguration(classes = TestConfig.class)
@ActiveProfiles({"test"})
@Transactional
class BookServiceTest {

    @Autowired
    BookService bookService;

    @Autowired
    BookRepository bookRepository;

    @AfterEach
    public void remove() {
        bookRepository.deleteAll();
    }


    @Test
    void getBooks() {
        Book actual = new Book("test-book", 10, new Author("name", "name", "email@email.com"));
        bookRepository.save(actual);

        List<Book> expected = bookService.getBooks("test-book");

        assertNotNull(expected);
        assertEquals(expected.stream().findFirst().get(), actual);
    }

    @Test
    void getAllBooks() {

        Book book1 = new Book("Book One", 123, new Author("John", "Doe", "john@example.com"));
        Book book2 = new Book("Book Two", 456, new Author("Jane", "Smith", "jane@example.com"));
        bookRepository.saveAll(List.of(book1, book2));

        List<Book> result = bookService.getBooks();

        assertEquals(2, result.size());
    }
}
