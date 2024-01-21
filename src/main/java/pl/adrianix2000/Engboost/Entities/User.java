package pl.adrianix2000.Engboost.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.annotation.Nonnull;

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

    @Nonnull
    @NotBlank
    @Size(min = 2, max = 60)
    @Column(name = "FIRSTNAME")
    private String firstName;

    @Nonnull
    @NotBlank
    @Size(min = 2, max = 60)
    @Column(name = "LASTNAME")
    private String lastName;

    @Nonnull
    @NotBlank
    @Size(min = 2, max = 60)
    @Column(name = "USERNAME")
    private String userName;

    @Nonnull
    @NotBlank
    @Column(name = "PASSWORD_HASH")
    private String password;

    @Column(name = "EMAIL")
    @Email
    @Nonnull
    @NotBlank
    private String email;

    @Column(name = "USER_ROLE")
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
