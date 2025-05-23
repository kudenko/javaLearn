package app.library.security;

import app.library.exception.UserRepositoryException;
import app.library.model.UserTable;
import app.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserTableDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserTableDetailsService(UserRepository repository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username)
                .map(UserTablePrincipal::new)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username: %s not found", username)));
    }

    public void addUser(UserTable user) {
        if (userRepository.existsByUserName(user.getUserName())) {
            throw new UserRepositoryException(String.format("User %s already exists.", user.getUserName()));
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
    }

    public void edit(UserTable user) {
        UserTable existingUser = userRepository.getReferenceById(user.getId());
        if(existingUser.getPassword().equals(user.getPassword())) {
            user.setPassword(existingUser.getPassword());
        } else if(user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    public List<UserTable> getAllUsers() {
        return userRepository.findAll();
    }

    public List<UserTable> getAllUsers(String username) {
        return Optional.ofNullable(username)
                .flatMap(userRepository::findByUserName)
                .map(List::of)
                .orElseGet(userRepository::findAll);
    }

    public UserTable getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserRepositoryException("No user with id " + id));
    }
}
