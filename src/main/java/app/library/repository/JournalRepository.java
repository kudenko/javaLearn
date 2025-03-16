package app.library.repository;

import app.library.model.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Long> {
    @Query("SELECT j FROM Journal j WHERE j.name = :name AND j.publicationYear = :year AND j.number = :number")
    List<Journal> findByNameYearNumber(@Param("name") String name, @Param("year") int year, @Param("number") int number);
}
