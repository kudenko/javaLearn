package library.storage;

import library.model.Publication;

public interface Repository {
    public void addPublication(Publication publication);

//    public <T> T[]  getPublications();

    public void removePublication(Publication publication);

    public void removeByIndex(int index);

    public Repository findPublications(String publicationName);

    public Publication findByIndex(int index);

    public void print();
}
