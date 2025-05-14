package app.library.service;

import app.library.model.Author;
import app.library.repository.AuthorRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@ContextConfiguration(classes = TestConfig.class)
@ActiveProfiles({"test"})
@Transactional
class AuthorServiceTest {

    @Autowired
    AuthorService authorService;

    @Autowired
    AuthorRepository authorRepository;

    @AfterEach
    public void remove() {
        authorRepository.deleteAll();
    }

    @Test
    void saveAuthor() {

        // Given
        Author author = new Author("John", "Doe", "john@example.com");

        // When
        authorService.addAuthor(author);

        // Then
        List<Author> authors = authorRepository.findAll();
        assertEquals(1, authors.size());
        assertEquals("john@example.com", authors.get(0).getEmail());
    }

    @Test
    void getAuthorByEmail() {
        // Given
        Author author1 = new Author("A", "One", "a@example.com");
        Author author2 = new Author("B", "Two", "b@example.com");
        List<Author> authors = List.of(author1, author2);

        authorRepository.saveAll(authors);

        // When
        List<Author> result = authorService.getAuthors("a@example.com");

        // Then
        assertEquals(1, result.size());
    }

    @Test
    void getAllAuthors() {
        // Given
        Author author1 = new Author("A", "One", "a@example.com");
        Author author2 = new Author("B", "Two", "b@example.com");
        List<Author> authors = List.of(author1, author2);

        authorRepository.saveAll(authors);

        // When
        List<Author> result = authorService.getAuthors();

        // Then
        assertEquals(2, result.size());
    }

    @Test
    void getAuthorById() {
        // Given
        Author author1 = new Author("A", "One", "a@example.com");
        Author author2 = new Author("B", "Two", "b@example.com");
        List<Author> authors = List.of(author1, author2);

        authorRepository.saveAll(authors);

        // When
        Author result = authorService.getAuthorById(1L);

        // Then
        assertEquals(1, result.getId());
    }
}
