package library.storage;

import library.model.Library;
import library.model.Publication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListRepository implements Repository<Publication> {
    private final List<Publication> publicationList;
    private static long publicationGeneralId = 0;


    public ListRepository() {
        publicationList = new ArrayList<>();
    }

    public ListRepository(List<Publication> publicationList) {
        this.publicationList = publicationList;
    }

    @Override
    public void addEntity(Publication publication) {
        if(!publicationList.contains(publication)) {
            publication.setPublicationId(++publicationGeneralId);
            publicationList.add(publication);
        } else {
            System.out.println("This publication isn't new. Publication " + publication.getPublicationId() + " " + publication.getName() + " was modified");
            editEntity(publication);
        }
    }

    @Override
    public List<Publication> getEntitiesList() {
        return publicationList;
    }

    @Override
    public void removeEntity(Publication publication) {
        publicationList.remove(publication);
    }

    @Override
    public void removeByIndex(int index) {
        publicationList.remove(index);
    }

    @Override
    public Publication findById(long entityId) {
        return publicationList.stream().filter(publication ->
                publication.getPublicationId() == entityId).findFirst().orElse(null);
    }

    public ListRepository findEntityByName(String publicationName) {
        return new ListRepository(publicationList.stream().filter(publication -> publication.getName().equals(publicationName)).collect(Collectors.toList()));
    }

    @Override
    public Publication findByIndex(int index) {
        return publicationList.get(index);
    }

    @Override
    public void addAllEntities(List<Publication> entities) {
        publicationList.addAll(entities);
    }

    @Override
    public void print() {
        Library.printPublications(getEntitiesList());
    }

    @Override
    public Publication editEntity(Publication publication) {
        if (publicationList.contains(publication)) {
            publicationList.set(publicationList.indexOf(publication), publication);
        } else {
            System.out.println("This publication isn't in the system. Please check the data.");
        }
        return publication;
    }

    @Override
    public void removeById(Long id) {
        Publication publication = findById(id);
        if(publication != null) {
            removeEntity(publication);
        }
    }

    public List<Publication> findBooks(String bookName, long authorId) {
        return publicationList.stream().filter(book -> book.getName().equals(bookName))
                .filter(book -> book.getAuthorId() == authorId)
                .toList();
    }
}
