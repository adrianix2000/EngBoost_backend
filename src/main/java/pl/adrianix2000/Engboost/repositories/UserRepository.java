package pl.adrianix2000.Engboost.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.adrianix2000.Engboost.Entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);
}
