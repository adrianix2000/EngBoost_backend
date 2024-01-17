package pl.adrianix2000.Engboost.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.adrianix2000.Engboost.Entities.Session;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    @Query("SELECT s FROM Session s INNER JOIN s.author u WHERE u.userName = :username")
    List<Session> findSessionsByUserName(@Param("username") String username);


}
