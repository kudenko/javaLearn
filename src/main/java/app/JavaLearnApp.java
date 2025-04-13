package app;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class JavaLearnApp {

    public static void main(String[] args) {
        String rawPassword = "password";
        String encoded = new BCryptPasswordEncoder().encode(rawPassword);
        System.out.println(encoded);
    }
}
