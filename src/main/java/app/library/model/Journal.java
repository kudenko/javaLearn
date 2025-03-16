package app.library.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "journal", schema = "library")
public class Journal extends Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "journal_seq")
    @SequenceGenerator(name = "journal_seq", sequenceName = "journal_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "number")
    private int number;

    @Column(name = "publication_year")
    private int publicationYear;

    public Journal(String name, int countPages, int number, int publicationYear) {
        super(name, countPages);
        this.number = number;
        this.publicationYear = publicationYear;
    }

    @Override
    public String print() {
        return String.format("Journal{%s, number=%d, year=%d}", super.print(), number, publicationYear);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Journal journal = (Journal) o;
        return number == journal.number && publicationYear == journal.publicationYear && journal.getId() == getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number, publicationYear, id);
    }

    @Override
    public String toString() {
        return "Journal{" +
                "id=" + id +
                ", name=" + super.getName() +
                ", pagesCount=" + super.getCountPages() +
                ", number=" + number +
                ", publicationYear=" + publicationYear +
                '}';
    }
}
