package library.model;

import java.util.Objects;

public class Publication {
    private final String name;
    private final int countPages;
    private final long publicationId;
    private static long publicationGeneralId = 0;

    public Publication(String name, int countPages) {
        this.name = name;
        this.countPages = countPages;
        this.publicationId = Publication.increaseId();
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

    public long getPublicationId() {
        return publicationId;
    }

    public static long getPublicationGeneralId() {
        return publicationGeneralId;
    }

    public static long increaseId() {
        return ++publicationGeneralId;
    }
}
