package library.author;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryAuthorStorage {

    //влению автора и удалению автора (удалять автора нужно по имейл адрессу,
    //так как имя и фамилия могут повторятся, а имейл уникальный)
    //(поиск автора по id, добавление автора, удаление автора, редактирование автора)

    List<Author> authorsStorage;

    public InMemoryAuthorStorage() {
        authorsStorage = new ArrayList<>();
    }

    public InMemoryAuthorStorage(List<Author> authorsStorage) {
        this.authorsStorage = authorsStorage;
    }

    public Author findById(int id) {
        return authorsStorage.stream().filter(author -> author.getId() == id).findFirst().orElse(null);
    }

    public Author findByEmail(String email) {
        return authorsStorage.stream().filter(author -> author.getEmail().equals(email)).findFirst().orElse(null);
    }

    public void addAuthor(Author author) {
        if(!authorsStorage.contains(author)) {
            authorsStorage.add(author);
        } else {
            System.out.println("This author isn't new. Author " + author.getFirstName() + " " + author.getLastName() + " was modified");
            editAuthor(author);
        }
    }

    //TODO should it add in case of author isn't in the system? Same question is for addAuthor method.
    public void editAuthor(Author author) {
        if(authorsStorage.contains(author)) {
            authorsStorage.set(authorsStorage.indexOf(author), author);
        } else {
            System.out.println("This author isn't in the system. Please check the data.");
        }
    }

    public void removeAuthor(String email) {
        authorsStorage = authorsStorage.stream().filter(author -> !author.getEmail().equals(email)).collect(Collectors.toList());
    }
}
