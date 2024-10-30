package library.utils;

import library.console.View;

import java.util.List;

public class Pagination {

    public static <T> void printPagination(List<T> list, int itemsPerPage, int page, View view) {

        if (itemsPerPage <= 0) {
            view.write("items per page should be > 0");
            throw new RuntimeException("items Per page should be > 0");
        }

        view.write("Select ID from the list: ");
        int pagesCount = list.size() / itemsPerPage;
        if (list.size() - pagesCount * itemsPerPage > 0) {
            pagesCount++;
        }
        if (page > pagesCount) {
            view.write("There is no " + page + " page. We have only " + pagesCount + " pages. Use 'p' key");
            return;
        }
        if (page < 0) {
            view.write("There is no negative pages. Use 'n' key for the next one");
            return;
        }

        if ((page - 1) * itemsPerPage < 0) {
            view.write("There is no negative pages. Use 'n' key for the next one");
            return;
        }

        view.write("Page: " + page + ", Items: " + (((page - 1)) * itemsPerPage) + " " + ((page - 1) * itemsPerPage + itemsPerPage));
        List<T> printList = list.subList((page - 1) * itemsPerPage, ((page - 1) * itemsPerPage + itemsPerPage));
        view.write(printList.toString());
    }
}
