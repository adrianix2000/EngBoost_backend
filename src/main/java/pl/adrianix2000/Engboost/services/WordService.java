package pl.adrianix2000.Engboost.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.adrianix2000.Engboost.Entities.Session;
import pl.adrianix2000.Engboost.Entities.Word;
import pl.adrianix2000.Engboost.exceptions.AppException;
import pl.adrianix2000.Engboost.repositories.SessionRepository;
import pl.adrianix2000.Engboost.repositories.WordRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class WordService {

    @Autowired
    private final SessionRepository sessionRepository;

    @Autowired
    private final WordRepository wordRepository;

    public boolean processFile(MultipartFile file, String sessionId) {

        long parsedSessionId = Long.parseLong(sessionId);
        Optional<Session> session = sessionRepository.findById(parsedSessionId);

        if(session.isPresent()) {

            Session foundedSession = session.get();

            if (!file.isEmpty()) {
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));

                    List<Word> words = br.lines().map(String::trim).map(this::extractWordFromLine)
                            .collect(Collectors.toList());

                    for(Word word : words) {
                        word.setSession(foundedSession);
                        wordRepository.save(word);
                    }

                    return true;
                } catch (IOException e) {
                    throw new AppException("błąd w prztwarzaniu pliku", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                throw new AppException("Podany plik jest pusty", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            throw new AppException("Nie można dodać plików, sesja nie istnieje", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Word extractWordFromLine(String line) {
        String[] splitted = line.split(";");
        if(splitted.length != 2) {
            throw new AppException("Zły format pliku", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return Word.builder()
                    .englishmean(splitted[0])
                    .polishmean(splitted[1])
                    .build();
        }
    }

    public void deleteWord(long id) {
        Optional<Word> word = wordRepository.findById(id);
        if(word.isPresent()) {
            Word foundedword = word.get();
            wordRepository.deleteById(foundedword.getId());
        } else {
            throw new AppException("Nie odnaleziono słówka", HttpStatus.NOT_FOUND);
        }
    }
}
