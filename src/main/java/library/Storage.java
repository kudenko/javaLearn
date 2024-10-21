package library;

import java.util.Arrays;

public class Storage {
    private Publication[] publications;
    private int  size = 16;
    private int index = 0;
    private int magnificationFactor = 2;

    public Storage() {
        this.publications = new Publication[size];
    }

    public Storage(int size) {
        this.size = size;
        this.publications = new Publication[size];
    }

    public void addPublication(Publication publication) {
        if(index == size) {
            this.size = size * magnificationFactor;
            publications = Arrays.copyOf(publications, size);
        }
        publications[index] = publication;
        index++;
    }

    public Publication[] getPublications() {
        return publications;
    }

    public void removePublication(Publication publication) {
        if (publications == null || publications.length == 0){
            System.out.println("Publications are empty");
            return;
        }
        int deletedPublications = 0;
        for (int i = 0; i < publications.length - deletedPublications; i++) {
            if (publications[i] != null && publications[i].equals(publication)) {
                Publication[] copiedPublications = new Publication[publications.length - 1];
                System.arraycopy(publications, 0, copiedPublications, 0, i);
                System.arraycopy(publications, i + 1, copiedPublications, i, publications.length - i - 1);
                publications = copiedPublications;
            }
        }
    }
}
