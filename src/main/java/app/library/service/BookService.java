package app.library.service;

import app.library.exceptions.AuthorRepositoryException;
import app.library.model.Author;
import app.library.model.Book;
import app.library.repository.AuthorRepository;
import app.library.repository.BookRepository;
import org.springframework.transaction.annotation.Transactional;

public class BookService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

   public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
       this.bookRepository = bookRepository;
       this.authorRepository = authorRepository;
   }

    @Transactional
    public void addBook(String name, int countPages, long authorId) throws AuthorRepositoryException {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorRepositoryException("Author not found"));

        Book book = new Book(name, countPages, author);
        bookRepository.save(book);
    }
}
