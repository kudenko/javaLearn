package app.library.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "book", schema = "library")
public class Book extends Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @SequenceGenerator(name = "book_seq", sequenceName = "book_id_seq", allocationSize = 1)
    private long id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    public Book() {
        super("default", 0);
    }

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

    public void setId(long id) {
        this.id = id;
    }
}
