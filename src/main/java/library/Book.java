package library;

import java.util.Objects;

public class Book extends Publication{
    private String author;

    public Book(String name, int countPages, String author) {
        super(name, countPages);
        this.author = author;
    }

    @Override
    public String print() {
        return String.format("Book{%s, author=%s}", super.print(), author);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book that = (Book) o;
        return author.equals(that.author) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), author);
    }
}
