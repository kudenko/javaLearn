package app.library.model;

import app.library.author.Author;

import java.util.Objects;

public class Book extends Publication {
    private Author author;
    private long id;

    public Book(String name, int countPages, Author author) {
        super(name, countPages);
        this.author = author;
    }

    public Book(long id, String name, int countPages, Author author) {
        super(name, countPages);
        this.author = author;
        this.id = id;
    }

    @Override
    public String print() {
        return String.format("Book{%s, authorId = %d, publicationId = %d}", super.print(), author.getId(), id);
    }

    @Override
    public Long getAuthorId() {
        return author.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book that = (Book) o;
        return author.getId() == that.author.getId() && id == that.getId()
                && Objects.equals(getName(), that.getName());
    }


    public long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), author.getId(), id, super.getName());
    }

    @Override
    public String toString() {
        return "Book{" +
                "id = " + id + " " +
                "name = " + super.getName() + " " +
                "pagesCount = " + super.getCountPages() + " " +
                "authorId = " + author.getId() +
                '}';
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
