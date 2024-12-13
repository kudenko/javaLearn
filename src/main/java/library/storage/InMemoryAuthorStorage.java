package library.storage;

import library.author.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryAuthorStorage implements Repository<Author> {

    List<Author> authorsStorage;
    private static int authorsId = 0;

    @Override
    public List<Author> findAll() {
        return authorsStorage;
    }

    @Override
    public void delete(Author entity) {
        authorsStorage.remove(entity);
    }

    @Override
    public void delete(int index) {
        authorsStorage.remove(index);
    }

    public int getAuthorsSize() {
        return authorsStorage.size();
    }

    public InMemoryAuthorStorage() {
        authorsStorage = new ArrayList<>();
    }

    public InMemoryAuthorStorage(List<Author> authorsStorage) {
        this.authorsStorage = authorsStorage;
    }

    @Override
    public Author findById(long id) {
        return authorsStorage.stream().filter(author -> author.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Author findByIndex(int index) {
        return authorsStorage.get(index);
    }

    @Override
    public void saveAll(List<Author> entities) {
        authorsStorage.addAll(entities);
    }

    @Override
    public void print() {
        authorsStorage.forEach(System.out::println);
    }

    public Author findByEmail(String email) {
        return authorsStorage.stream().filter(author -> author.getEmail().equals(email)).findFirst().orElse(null);
    }

    @Override
    public void save(Author author) {
        if (!authorsStorage.contains(author)) {
            author.setId(++authorsId);
            authorsStorage.add(author);
        } else {
            System.out.println("This author isn't new. Author " + author.getFirstName() + " " + author.getLastName() + " was modified");
            update(author);
        }
    }

    public void removeAuthor(String email) {
        authorsStorage = authorsStorage.stream().filter(author -> !author.getEmail().equals(email)).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return authorsStorage.stream().map(Author::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public Author update(Author author) {
        if (authorsStorage.contains(author)) {
            authorsStorage.set(authorsStorage.indexOf(author), author);
        } else {
            System.out.println("This author isn't in the system. Please check the data.");
        }
        return author;
    }

    @Override
    public void delete(Long id) {
        Author author = findById(id);
        if(author != null) {
            delete(author);
        }
    }
}
