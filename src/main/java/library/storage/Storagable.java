package library.storage;

import library.Publication;

public interface Storagable {
    public void addPublication(Publication publication);

    public Publication[] getPublications();

    public void removePublication(Publication publication);

    public void removeByIndex(int index);

    public Storagable findPublications(String publicationName);

    public Publication findByIndex(int index);

    public void print();
}
