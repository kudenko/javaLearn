package library.storage;


import java.util.List;

public interface  Repository<T> {
    void addEntity(T entity);

    List<T> getEntitiesList();

    T[] getEntitiesArray();

    void removeEntity(T entity);

    void removeByIndex(int index);

    T findById(long entityId);

    T findByIndex(int index);

    void print();
}
