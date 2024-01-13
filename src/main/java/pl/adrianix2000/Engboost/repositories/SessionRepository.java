package pl.adrianix2000.Engboost.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.adrianix2000.Engboost.Entities.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
}
