package library.storage;

import library.Library;
import library.Publication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListStorage implements Storagable {
    private List<Publication> publicationList;

    public ListStorage() {
        publicationList = new ArrayList<>();
    }

    public ListStorage(List<Publication> publicationList) {
        this.publicationList = publicationList;
    }

    @Override
    public void addPublication(Publication publication) {
        publicationList.add(publication);
    }

    @Override
    public Publication[] getPublications() {
        return publicationList.toArray(new Publication[0]);
    }

    @Override
    public void removePublication(Publication publication) {
        publicationList.remove(publication);
    }

    @Override
    public void removeByIndex(int index) {
        publicationList.remove(index);
    }

    @Override
    public Storagable findPublications(String publicationName) {
        return new ListStorage(publicationList.stream().filter(publication -> publication.getName().equals(publicationName)).collect(Collectors.toList()));
    }

    @Override
    public Publication findByIndex(int index) {
        return publicationList.get(index);
    }

    @Override
    public void print() {
        Library.printPublications(getPublications());
    }
}
