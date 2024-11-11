package library.model;

import java.util.Objects;

public class Book extends Publication {
    private final long authorId;

    public Book(String name, int countPages, long authorId) {
        super(name, countPages);
        this.authorId = authorId;
    }

    @Override
    public String print() {
        return String.format("Book{%s, authorId=%d}", super.print(), authorId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book that = (Book) o;
        return authorId == that.authorId && super.getPublicationId() == that.getPublicationId() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), authorId, super.getPublicationId());
    }
}
