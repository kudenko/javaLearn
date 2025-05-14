package app.library.service;

import app.library.model.UserTable;
import app.library.repository.UserRepository;
import app.library.security.UserTableDetailsService;
import app.library.service.config.TestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@ContextConfiguration(classes = TestConfig.class)
@ActiveProfiles({"test"})
@Transactional
class UserServiceTest {

    @Autowired
    UserTableDetailsService userService;

    @Autowired
    UserRepository userRepository;

    @AfterEach
    void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    void addUser() {
        UserTable user = new UserTable();
        user.setUserName("testuser");
        user.setPassword("password");

        userService.addUser(user);

        List<UserTable> users = userService.getAllUsers();
        assertEquals(1, users.size());
        assertEquals("testuser", users.get(0).getUserName());

        assertNotEquals("password", users.get(0).getPassword());
    }

    @Test
    void editUser() {
        UserTable user = new UserTable();
        user.setUserName("editUser");
        user.setPassword("editedPassword");

        userService.addUser(user);

        UserTable saved = userService.getAllUsers("editUser").get(0);

        saved.setUserName("editUserRenamed");

        userService.edit(saved);

        UserTable edited = userService.getById(saved.getId());
        assertEquals("editUserRenamed", edited.getUserName());
    }


    @Test
    void getAllUsers() {
        UserTable user1 = new UserTable();
        user1.setUserName("user1");
        user1.setPassword("pass1");
        userService.addUser(user1);

        UserTable user2 = new UserTable();
        user2.setUserName("user2");
        user2.setPassword("pass2");
        userService.addUser(user2);

        List<UserTable> allUsers = userService.getAllUsers(null);
        assertEquals(2, allUsers.size());

        List<UserTable> singleUser = userService.getAllUsers("user1");
        assertEquals(1, singleUser.size());
        assertEquals("user1", singleUser.get(0).getUserName());
    }
}
