package app.library.service;

import app.library.exceptions.AuthorRepositoryException;
import app.library.model.Author;
import app.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void addAuthor(Author author) {
        try {
            authorRepository.save(author);
        } catch (AuthorRepositoryException e) {

        }
    }

    public List<Author> getAuthors(String email) {
        try {
            return Optional.ofNullable(email)
                    .map(authorRepository::findByEmail)
                    .orElseGet(authorRepository::findAll);
        } catch (AuthorRepositoryException e) {
            return new ArrayList<>();
        }
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }
}
