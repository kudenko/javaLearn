package app.library.storage;

import app.library.model.Author;

import java.util.List;

public interface AuthorRepositoryCustom<T> extends Repository<T> {
    List<Author> findByEmail(String email);
}
