package app.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_seq")
    @SequenceGenerator(name = "author_seq", sequenceName = "author_id_seq", allocationSize = 1)
    private Long id;

    @NotBlank(message = "First name is required")
    @Size(max = 500, message = "Author's first name should be less 500 symbols")
    @Column(name = "first_name", length = 500)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 500, message = "Author's last name should be less 500 symbols")
    @Column(name = "last_name", length = 500)
    private String lastName;

    @NotBlank(message = "email is required")
    @Size(max = 500, message = "Email is required")
    @Email(message = "email must be a valid address")
    @Column(name = "email", length = 500)
    private String email;

    public Author(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return this.id == author.id && Objects.equals(email, author.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

}
