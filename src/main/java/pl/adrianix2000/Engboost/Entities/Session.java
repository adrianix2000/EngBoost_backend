package pl.adrianix2000.Engboost.Entities;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CREATEDATE")
    private Date createdate;

    @Column(name = "ISSHARED")
    private boolean isshared;

    @Column(name = "VIEWNUMBER")
    private long viewnumber;

    @ManyToOne
    @JoinColumn(name = "AUTHOR")
    private User author;
}
