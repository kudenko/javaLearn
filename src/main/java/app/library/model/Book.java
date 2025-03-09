package app.library.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
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

    public Book(String name, int countPages, Author author) {
        super(name, countPages);
        this.author = author;
    }

    @Override
    public String print() {
        return String.format("Book{%s, authorId = %d, publicationId = %d}", super.print(), author.getId(), id);
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
}
