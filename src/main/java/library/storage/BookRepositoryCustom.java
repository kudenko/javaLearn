package library.storage;

import library.model.Book;

import java.util.List;

public interface BookRepositoryCustom<T> extends Repository<T> {
    List<Book> findBooksByAuthorId(Long authorId);
}
