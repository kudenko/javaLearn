package app.library.service;

import app.library.model.Journal;
import app.library.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    JournalService(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    public void addJournal(String name, String countPages, String number, String publicationYear) {
        int intCountPages = Integer.parseInt(countPages);
        int intNumber = Integer.parseInt(number);
        int intPublicationYear = Integer.parseInt(publicationYear);

        journalRepository.save(new Journal(name, intCountPages, intNumber, intPublicationYear));
    }

    public List<Journal> getJournals(String name, String year, String number) {
        List<Journal> journals = Optional.ofNullable(name)
                .filter(journalName -> !journalName.isEmpty())
                .map(journalName -> {
                    int intYear = Integer.parseInt(year);
                    int intNumber = Integer.parseInt(number);
                    List<Journal> foundJournals = journalRepository.findByNameAndPublicationYearAndNumber(journalName, intYear, intNumber);
                    return foundJournals;
                })
                .orElseGet(() -> journalRepository.findAll());

        return journals;

    }
}
