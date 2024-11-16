package library.utils;

import library.console.View;

import java.util.List;
import java.util.stream.Collectors;

public final class Pagination {

    private Pagination() {
        throw new UnsupportedOperationException("This class cannot be initialized");
    }

    public static <T> void printPagination(List<T> list, int itemsPerPage, int page, View view) {
        if (arePaginationArgumentsValid(list, itemsPerPage, page, view)) {
            view.write("Select ID from the list: ");
            view.write("Page: " + page + ", Items: " + (((page - 1)) * itemsPerPage) + " - " + ((page - 1) * itemsPerPage + itemsPerPage));
            List<T> printList = list.subList((page - 1) * itemsPerPage, ((page - 1) * itemsPerPage + itemsPerPage));
            view.write(printList.stream().map(Object::toString).collect(Collectors.joining("\n")));
        }
    }

    public static <T> int getPagePaginationPageCount(List<T> list, int itemsPerPage) {
        int pagesCount = list.size() / itemsPerPage;
        if (list.size() - pagesCount * itemsPerPage > 0) {
            pagesCount++;
        }
        return pagesCount;
    }

    private static <T> boolean arePaginationArgumentsValid(List<T> list, int itemsPerPage, int page, View view) {
        if (itemsPerPage <= 0) {
            view.write("items per page should be > 0");
            throw new IllegalArgumentException("items Per page should be > 0");
        }
        int pagesCount = getPagePaginationPageCount(list, itemsPerPage);
        if (page > pagesCount) {
            view.write("There is no " + page + " page. We have only " + pagesCount + " pages. Use 'p' key for previous page");
            return false;
        }
        if (page < 0 || ((page - 1) * itemsPerPage < 0)) {
            view.write("There is no negative pages. Use 'n' key for the next one");
            return false;
        }
        return true;
    }
}
