import library.*;
import library.author.Author;
import library.author.InMemoryAuthorStorage;
import library.console.Console;
import library.storage.ListStorage;
import library.storage.Storage;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage(2);
        Publication publication = new Publication("Test1", 32);
        storage.addPublication(publication);
        storage.addPublication(new Journal("Test3", 34, 1, 2024));
        storage.addPublication(new Publication("Test1", 32));

        storage.addPublication(new Book("Test4", 35, 1));
        storage.addPublication(new Book("Test5", 36, 2));

        Library.printPublications(storage.getPublications());
        storage.removePublication(publication);

        //Output:
//        name=Test1, countPages=32
//Journal{name=Test3, countPages=34, number=1, year=2024}
//name=Test1, countPages=32
//Book{name=Test4, countPages=35, author=Yanov Oleksander}
//Book{name=Test5, countPages=36, author=Author}

        System.out.println();
        System.out.println("Deleted publications: ");
        Library.printPublications(storage.getPublications());
//        Deleted publications:
//        Journal{name=Test3, countPages=34, number=1, year=2024}
//        Book{name=Test4, countPages=35, author=Yanov Oleksander}
//        Book{name=Test5, countPages=36, author=Author}
        storage.findPublications("Test5");
        //Found publications:
        //Book{name=Test5, countPages=36, author=Author}
        System.out.println("Found: ");
        System.out.println(storage.findByIndex(0).print());
        //Journal{name=Test3, countPages=34, number=1, year=2024}
        System.out.println("Deleted: ");
        storage.removeByIndex(0);
        storage.print();
        //Book{name=Test4, countPages=35, author=Yanov Oleksander}
        //Book{name=Test5, countPages=36, author=Author}

        // new Console(storage, authors).getConsole();

        InMemoryAuthorStorage authors = new InMemoryAuthorStorage();
        for (int i = 1; i < 31; i++) {
            authors.addAuthor(new Author(String.format("FirstName_%d", i), String.format("LastName_%d", i), String.format("email@test%d", i)));
        }
        System.out.println(authors);

        new Console(new ListStorage(), authors).getConsole();


    }


}
