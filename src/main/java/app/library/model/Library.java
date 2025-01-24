package app.library.model;

import java.util.List;
import java.util.Objects;

public class Library {
    public static void printPublications(List<Publication> publications) {
        if(publications.isEmpty()) {
            System.out.println("No publications for printing");
            return;
        }
        publications.stream()
                .filter(Objects::nonNull).forEach(publication -> System.out.println(publication.print()));
    }
}
