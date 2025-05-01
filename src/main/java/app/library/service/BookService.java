package app.library.service;

import app.library.exception.BookRepositoryException;
import app.library.model.Author;
import app.library.model.Book;
import app.library.repository.AuthorRepository;
import app.library.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Transactional
    public void addBook(Book book, Long authorId) {
        Optional<Author> author = authorRepository.findById(authorId);
        if(author.isEmpty()) {
            throw new BookRepositoryException(String.format("There is no author with id %s", book.getAuthor().getId()));
        }
        if(book.getCountPages() == null) {
            throw new BookRepositoryException("Count of Pages value should be a number");
        }
        book.setAuthor(author.get());
        bookRepository.save(book);
    }

    public List<Book> getBooks(String name) {
        return Optional.ofNullable(name)
                .map(bookRepository::findBooksByName)
                .orElseGet(bookRepository::findAll);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
}
