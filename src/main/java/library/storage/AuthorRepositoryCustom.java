package library.storage;

import library.author.Author;

import java.util.List;

public interface AuthorRepositoryCustom<T> extends Repository<T> {
    List<Author> findByEmail(String email);
}
