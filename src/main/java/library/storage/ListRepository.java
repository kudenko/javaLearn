package library.storage;

import library.model.Library;
import library.model.Publication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListRepository implements Repository<Publication> {
    private List<Publication> publicationList;

    public ListRepository() {
        publicationList = new ArrayList<>();
    }

    public ListRepository(List<Publication> publicationList) {
        this.publicationList = publicationList;
    }

    @Override
    public void addEntity(Publication publication) {
        publicationList.add(publication);
    }

    @Override
    public List<Publication> getEntitiesList() {
        return publicationList;
    }

    @Override
    public Publication[] getEntitiesArray() {
        return publicationList.toArray(new Publication[0]);
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
        Library.printPublications(getEntitiesArray());
    }
}
