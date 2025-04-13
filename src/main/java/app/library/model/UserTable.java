package app.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 1000)
    private String firstName;

    @Column(name = "last_name", length = 1000)
    private String lastName;

    @Column(name = "user_name", length = 1000)
    private String userName;

    @Column(name = "user_role", length = 1000)
    private String userRole;

    @Column(name = "user_status", length = 1000)
    private String userStatus;

    @Column(name = "password", length = 1000)
    private String password;

}
