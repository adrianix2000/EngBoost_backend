package pl.adrianix2000.Engboost.Entities;

import jakarta.persistence.*;
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

    @Column(name = "ENGLISHMEAN")
    private String englishmean;

    @Column(name = "POLISHMEAN")
    private String polishmean;

    @ManyToOne
    @JoinColumn(name = "SESSIONID")
    private Session session;
}
