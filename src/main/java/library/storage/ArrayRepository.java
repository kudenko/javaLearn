package library.storage;

import library.model.Library;
import library.model.Publication;

import java.util.Arrays;
import java.util.List;

//Array repository can be removed
public class ArrayRepository implements Repository<Publication> {
    private Publication[] publications;
    private int size = 16;
    private int index = 0;
    private final int magnificationFactor = 2;

    public ArrayRepository() {
        this.publications = new Publication[size];
    }

    public ArrayRepository(int size) {
        this.size = size;
        this.publications = new Publication[size];
    }

    public void addEntity(Publication publication) {
        if (index <= size) {
            this.size = size * magnificationFactor;
            publications = Arrays.copyOf(publications, size);
        }
        publications[index] = publication;
        index++;
    }

    @Override
    public List<Publication> getEntitiesList() {
        return List.of(publications);
    }

    public Publication[] getEntitiesArray() {
        return publications;
    }


    public void removeEntity(Publication publication) {
        if (publications == null || publications.length == 0) {
            System.out.println("Publications are empty");
            return;
        }
        for (int i = 0; i < publications.length; i++) {
            if (publications[i] != null && publications[i].equals(publication)) {
                Publication[] copiedPublications = new Publication[publications.length - 1];
                System.arraycopy(publications, 0, copiedPublications, 0, i);
                System.arraycopy(publications, i + 1, copiedPublications, i, publications.length - i - 1);
                publications = copiedPublications;
            }
        }
    }

    public void removeByIndex(int index) {
        if (publications[index] != null) {
            Publication[] copiedPublications = new Publication[publications.length - 1];
            System.arraycopy(publications, 0, copiedPublications, 0, index);
            System.arraycopy(publications, index + 1, copiedPublications, index, publications.length - index - 1);
            publications = copiedPublications;
        }
    }

    @Override
    public Publication findById(long entityId) {
        return Arrays.stream(publications).filter(publication ->
                publication.getPublicationId() == entityId).findFirst().orElse(null);
    }

    public ArrayRepository findEntityByName(String publicationName) {
        ArrayRepository foundPublications = new ArrayRepository();
        for (int i = 0; i < publications.length; i++) {
            if (publications[i] != null && publications[i].getName().equals(publicationName)) {
                foundPublications.addEntity(publications[i]);
            }
        }
        if (foundPublications.isEmpty()) {
            System.out.println("There is no publication with the name: " + publicationName);
        } else {
            System.out.println("Found publications: ");
            foundPublications.print();
        }
        return foundPublications;
    }

    public Publication findByIndex(int index) {
        try {
            return publications[index];
        } catch (NullPointerException e) {
            return null;
        }
    }

    private boolean isEmpty() {
        if (publications[0] == null) {
            return true;
        }
        return false;
    }

    public void print() {
        Library.printPublications(publications);
    }
}
