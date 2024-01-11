package pl.adrianix2000.Engboost.Entities;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "APP_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "FIRSTNAME")
    private String firstName;
    @Column(name = "LASTNAME")
    private String lastName;
    @Column(name = "USERNAME")
    private String userName;
    @Column(name = "PASSWORD_HASH")
    private String password;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "USER_ROLE")
    private UserRole role;
}
