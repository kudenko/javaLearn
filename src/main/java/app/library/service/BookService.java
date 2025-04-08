package app.library.service;

import app.library.exceptions.AuthorRepositoryException;
import app.library.model.Author;
import app.library.model.Book;
import app.library.repository.AuthorRepository;
import app.library.repository.BookRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class BookService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

   public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
       this.bookRepository = bookRepository;
       this.authorRepository = authorRepository;
   }

    @Transactional
    public void addBook(Book book) throws AuthorRepositoryException {
        bookRepository.save(book);
    }

    public List<Book> getBooks(String name) {
        return Optional.ofNullable(name)
                .map(bookRepository::findBooksByName)
                .orElseGet(() -> bookRepository.findAll());
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
}
