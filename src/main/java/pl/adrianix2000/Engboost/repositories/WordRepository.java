package pl.adrianix2000.Engboost.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.adrianix2000.Engboost.Entities.Session;
import pl.adrianix2000.Engboost.Entities.Word;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {

    @Query("SELECT w FROM Word w WHERE w.session.id = :sessionId")
    List<Word> findWordBySessionId(@Param("sessionId") long sessionId);
}
