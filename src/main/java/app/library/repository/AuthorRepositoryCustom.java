package app.library.repository;

import app.library.model.Author;

import java.util.List;

public interface AuthorRepositoryCustom<T> extends Repository<T> {
    List<Author> findByEmail(String email);
}
