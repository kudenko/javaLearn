package app.library.model;

import java.util.Objects;

public class Book extends Publication {
    private final long authorId;
    private long id;

    public Book(String name, int countPages, long authorId) {
        super(name, countPages);
        this.authorId = authorId;
    }

    public Book(long id, String name, int countPages, long authorId) {
        super(name, countPages);
        this.authorId = authorId;
        this.id = id;
    }

    @Override
    public String print() {
        return String.format("Book{%s, authorId = %d, publicationId = %d}", super.print(), authorId, id);
    }

    @Override
    public Long getAuthorId() {
        return authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book that = (Book) o;
        return authorId == that.authorId && id == that.getId()
                && Objects.equals(getName(), that.getName());
    }


    public long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), authorId, id, super.getName());
    }

    @Override
    public String toString() {
        return "Book{" +
                "id = " + id + " " +
                "name = " + super.getName() + " " +
                "pagesCount = " + super.getCountPages() + " " +
                "authorId = " + authorId +
                '}';
    }
}
