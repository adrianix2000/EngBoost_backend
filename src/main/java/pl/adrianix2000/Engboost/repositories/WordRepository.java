package pl.adrianix2000.Engboost.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.adrianix2000.Engboost.Entities.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
}
