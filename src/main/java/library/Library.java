package library;

import java.util.Arrays;
import java.util.Objects;

public class Library {
    public void printPublications(Publication[] publications) {
        if(publications == null || Arrays.stream(publications).noneMatch(Objects::nonNull)) {
            System.out.println("Нет публикаций для печати");
            return;
        }
        Arrays.stream(publications)
                .filter(Objects::nonNull).forEach(publication -> System.out.println(publication.print()));
    }
}
