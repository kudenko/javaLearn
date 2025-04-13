package app.library.security;

import app.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserTableDetailsService implements UserDetailsService {

private UserRepository userRepository;

@Autowired
public UserTableDetailsService(UserRepository repository) {
    this.userRepository = repository;
}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username)
                .map(UserTablePrincipal::new)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username: %s not found", username)));
    }
}
