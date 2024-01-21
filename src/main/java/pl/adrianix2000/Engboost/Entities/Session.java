package pl.adrianix2000.Engboost.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jdk.jfr.Name;
import lombok.*;

import java.util.Date;
import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "WORDSESSION")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Nonnull
    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "TITLE")
    private String title;

    @NonNull
    @Column(name = "CREATEDATE")
    private Date createdate;

    @Nonnull
    @Column(name = "ISSHARED")
    private boolean isshared;

    @Column(name = "VIEWNUMBER")
    private long viewnumber;

    @ManyToOne
    @JoinColumn(name = "AUTHOR", nullable = false)
    private User author;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Word> words;
}
