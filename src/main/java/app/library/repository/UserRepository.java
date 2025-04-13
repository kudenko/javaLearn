package app.library.repository;

import app.library.model.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserTable, Long> {
    Optional<UserTable> findByUserName(String userName);
}
