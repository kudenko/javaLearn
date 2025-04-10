package app.library.service;

import app.library.exception.AuthorRepositoryException;
import app.library.model.Author;
import app.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void addAuthor(Author author) {
        if (authorRepository.existsByEmail(author.getEmail())) {
            throw new AuthorRepositoryException(String.format("Email %s already exists", author.getEmail()));
        }
        authorRepository.save(author);
    }

    public List<Author> getAuthors(String email) {
        return Optional.ofNullable(email)
                .map(authorRepository::findByEmail)
                .orElseGet(authorRepository::findAll);
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long authorId) {
        return authorRepository.getReferenceById(authorId);
    }
}
