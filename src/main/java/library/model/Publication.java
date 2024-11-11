package library.model;

import java.util.Objects;

public class Publication {
    private final String name;
    private final int countPages;
    private long publicationId;

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
        return countPages == that.countPages && publicationId == that.publicationId && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, countPages, publicationId);
    }

    public long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(long publicationId) {
        this.publicationId = publicationId;
    }
}
