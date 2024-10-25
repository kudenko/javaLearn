package library.storage;

import library.model.Library;
import library.model.Publication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListRepository implements Repository {
    private List<Publication> publicationList;

    public ListRepository() {
        publicationList = new ArrayList<>();
    }

    public ListRepository(List<Publication> publicationList) {
        this.publicationList = publicationList;
    }

    @Override
    public void addPublication(Publication publication) {
        publicationList.add(publication);
    }

//    @Override
//    public Publication[] getPublications() {
//        return publicationList.toArray(new Publication[0]);
//    }

    @Override
    public void removePublication(Publication publication) {
        publicationList.remove(publication);
    }

    @Override
    public void removeByIndex(int index) {
        publicationList.remove(index);
    }

    @Override
    public Repository findPublications(String publicationName) {
        return new ListRepository(publicationList.stream().filter(publication -> publication.getName().equals(publicationName)).collect(Collectors.toList()));
    }

    @Override
    public Publication findByIndex(int index) {
        return publicationList.get(index);
    }

    @Override
    public void print() {
//        Library.printPublications(getPublications());
    }
}
