package app.library.service;

import app.library.model.Journal;
import app.library.repository.JournalRepository;
import app.library.service.config.TestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@ContextConfiguration(classes = TestConfig.class)
@ActiveProfiles({"test"})
@Transactional
class JournalServiceTest {

    @Autowired
    JournalService journalService;

    @Autowired
    JournalRepository journalRepository;

    @AfterEach
    public void remove() {
        journalRepository.deleteAll();
    }

    @Test
    void addJournal() {
        Journal journal = new Journal("Test Journal", 50, 2, 2024);
        journalService.addJournal(journal);

        List<Journal> journals = journalRepository.findAll();
        assertEquals(1, journals.size());
        assertEquals("Test Journal", journals.get(0).getName());
        assertEquals(50, journals.get(0).getCountPages());
        assertEquals(2, journals.get(0).getNumber());
        assertEquals(2024, journals.get(0).getPublicationYear());
    }

    @Test
    void getJournalsFiltered() {
        Journal journal = new Journal("Test Journal", 100, 1, 2023);
        journalRepository.save(journal);

        List<Journal> result = journalService.getJournals("Test Journal", "2023", "1");

        assertEquals(1, result.size());
        assertEquals("Test Journal", result.get(0).getName());
    }

    @Test
    void getJournals() {
        List<Journal> journals = new ArrayList<>();
        journals.add(new Journal("Journal1", 10, 3, 2019));
        journals.add(new Journal("Journal2", 20, 4, 2022));
        journalRepository.saveAll(journals);

        List<Journal> result = journalService.getJournals("", "", "");

        assertEquals(2, result.size());
    }
}
