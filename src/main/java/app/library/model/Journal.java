package app.library.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "journal", schema = "library")
public class Journal extends Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "journal_seq")
    @SequenceGenerator(name = "journal_seq", sequenceName = "journal_id_seq", allocationSize = 1)
    private long id;

    @Column(name = "number")
    private int number;

    @Column(name = "publication_year")
    private int publicationYear;

    protected Journal() {
        super("defName", 0);
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Journal(String name, int countPages, int number, int publicationYear) {
        super(name, countPages);
        this.number = number;
        this.publicationYear = publicationYear;
    }

    public Journal(long id, String name, int countPages, int number, int publicationYear) {
        super(name, countPages);
        this.number = number;
        this.publicationYear = publicationYear;
        this.id = id;
    }


    @Override
    public String print() {
        return String.format("Journal{%s, number=%d, year=%d}", super.print(), number, publicationYear);
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public long getId() {
        return id;
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
