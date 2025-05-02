package app.library.service;

import app.library.model.Author;
import app.library.model.Book;
import app.library.repository.BookRepository;
import app.library.service.config.TestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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
        //given
        Book actual = new Book("test-book", 10, new Author("name", "name", "email@email.com"));
        bookRepository.save(actual);
        //when
        List<Book> expected = bookService.getBooks("test-book");

        //then
        assertNotNull(expected);
        assertEquals(expected.stream().findFirst().get(), actual);
    }
}