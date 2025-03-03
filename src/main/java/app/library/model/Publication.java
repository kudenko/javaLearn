package app.library.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.util.Objects;

@MappedSuperclass
public class Publication {
    @Column(name = "name", length = 500)
    private String name;

    @Column(name = "count_pages")
    private int countPages;

    public Publication(String name, int countPages) {
        this.name = name;
        this.countPages = countPages;
    }

    public String print() {
        return String.format("name=%s, countPages=%d", name, countPages);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return countPages == that.countPages && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, countPages);
    }


    public Long getAuthorId() {
        return null;
    }

    public int getCountPages() {
        return countPages;
    }

    public void setCountPages(int countPages) {
        this.countPages = countPages;
    }

    public void setName(String name) {
        this.name = name;
    }
}
