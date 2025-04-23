package app.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "First name is required")
    @Size(max = 1000, message = "Max size is 1000")
    @Column(name = "first_name", length = 1000)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 1000, message = "Max size is 1000")
    @Column(name = "last_name", length = 1000)
    private String lastName;

    @NotBlank(message = "Username is required")
    @Size(max = 1000, message = "Max size is 1000")
    @Column(name = "user_name", length = 1000)
    private String userName;

    @NotBlank(message = "User role is required")
    @Size(max = 1000, message = "Max size is 1000")
    @Column(name = "user_role", length = 1000)
    private String userRole;

    @NotBlank(message = "User status is required")
    @Size(max = 1000, message = "Max size is 1000")
    @Column(name = "user_status", length = 1000)
    private String userStatus;

    @NotBlank(message = "User password is required")
    @Size(max = 1000, message = "Max size is 1000")
    @Column(name = "password", length = 1000)
    private String password;

}
