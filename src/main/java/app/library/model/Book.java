package app.library.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "book")
@EqualsAndHashCode(callSuper = true)
public class Book extends Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @SequenceGenerator(name = "book_seq", sequenceName = "book_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
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
}
