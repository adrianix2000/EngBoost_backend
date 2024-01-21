package pl.adrianix2000.Engboost.Entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "WORD")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Nonnull
    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "ENGLISHMEAN")
    private String englishmean;

    @Nonnull
    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "POLISHMEAN")
    private String polishmean;

    @ManyToOne
    @JoinColumn(name = "SESSIONID")
    private Session session;
}
