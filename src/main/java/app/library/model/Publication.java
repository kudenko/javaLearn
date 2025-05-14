package app.library.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@MappedSuperclass
public class Publication {

    @NotBlank(message = "Journal name is required")
    @Size(max = 500, message = "Name should be less 500 symbols")
    @Column(name = "name", length = 500)
    private String name;

    @NotNull(message = "Count Pages is required")
    @Min(value = 1, message = "Page count should be more 1")
    @Column(name = "count_pages")
    private Integer countPages;

    public Publication(String name, int countPages) {
        this.name = name;
        this.countPages = countPages;
    }

    public String print() {
        return String.format("name=%s, countPages=%d", name, countPages);
    }
}
