import library.*;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage(2);
        storage.addPublication(new Publication("Test1", 32));
        storage.addPublication(new Publication("Test2", 33));
        storage.addPublication(new Journal("Test3", 34, 1, 2024));
        storage.addPublication(new Book("Test4", 35, "Yanov Oleksander"));

        Library library = new Library();
        library.printPublications(storage.getPublications());

        //Output:
//        name=Test1, countPages=32
//        name=Test2, countPages=33
//        Journal{name=Test3, countPages=34, number=1, year=2024}
//        Book{name=Test4, countPages=35, author=Yanov Oleksander}
    }
}
