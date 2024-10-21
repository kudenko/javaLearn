import library.*;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage(2);
        Publication publication = new Publication("Test1", 32);
        storage.addPublication(publication);
        storage.addPublication(new Journal("Test3", 34, 1, 2024));
        storage.addPublication(new Publication("Test1", 32));

        storage.addPublication(new Book("Test4", 35, "Yanov Oleksander"));
        storage.addPublication(new Book("Test5", 36, "Author"));

        Library library = new Library();
        library.printPublications(storage.getPublications());
        storage.removePublication(publication);

        //Output:
//        name=Test1, countPages=32
//Journal{name=Test3, countPages=34, number=1, year=2024}
//name=Test1, countPages=32
//Book{name=Test4, countPages=35, author=Yanov Oleksander}
//Book{name=Test5, countPages=36, author=Author}

        System.out.println();
        System.out.println("Deleted publications: ");
        library.printPublications(storage.getPublications());
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

    }


}
