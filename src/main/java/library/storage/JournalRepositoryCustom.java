package library.storage;

import library.model.Journal;

import java.util.List;

public interface JournalRepositoryCustom<T> extends Repository<T>{

    List<Journal> findByNameYearNumber(String name, int year, int number);
}
