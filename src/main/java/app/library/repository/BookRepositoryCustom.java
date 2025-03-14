package app.library.repository;

import app.library.model.Book;

import java.util.List;

public interface BookRepositoryCustom<T> extends Repository<T> {
    List<Book> findBooksByAuthorId(Long authorId);

    List<Book> findBooksByName(String name);

}
