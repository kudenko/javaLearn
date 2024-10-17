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
}
