package app.library.service;

import app.library.exceptions.AuthorRepositoryException;
import app.library.model.Author;
import app.library.model.Book;
import app.library.repository.AuthorRepository;
import app.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void addBook(String name, int countPages, long authorId) throws AuthorRepositoryException {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorRepositoryException("Author not found"));

        Book book = new Book(name, countPages, author);
        bookRepository.save(book);
    }
}
