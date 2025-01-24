package app.library.storage;

import java.util.List;

public interface  Repository<T> {
    void save(T entity);

    List<T> findAll();

    void delete(T entity);

    T findById(long id);

    void saveAll(List<T> entities);

    void print();

    T update(T entity);

    void delete(Long id);
}
