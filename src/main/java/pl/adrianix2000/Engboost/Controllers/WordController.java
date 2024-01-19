package pl.adrianix2000.Engboost.Controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.adrianix2000.Engboost.Entities.*;
import pl.adrianix2000.Engboost.Mappers.WordMapper;
import pl.adrianix2000.Engboost.exceptions.AppException;
import pl.adrianix2000.Engboost.repositories.WordRepository;
import pl.adrianix2000.Engboost.services.WordService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/words")
@Slf4j
@RequiredArgsConstructor
public class WordController {

    @Autowired
    private final WordService service;

    @Autowired
    private final WordRepository repository;

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public ResponseEntity<String> handleUploadedFile(@RequestParam("file") MultipartFile file, @RequestParam("sessionId") String sessionId) {

//        log.info("Session id to: " + sessionId);
        log.info("dsfdsfsdf333333333333333333333333333333333333333 " + sessionId);
        service.processFile(file, sessionId);
        return ResponseEntity.ok("przeczytano plik");
    }

    @RequestMapping(path = "/getBySessionId", method = RequestMethod.GET)
    public ResponseEntity<List<WordDto>> getBySessionId(@RequestParam long sessionId) {
        List<WordDto> wordList = repository.findWordBySessionId(sessionId)
                .stream()
                .map(WordMapper::map)
                .collect(Collectors.toList());
        return ResponseEntity.ok(wordList);
    }

    @RequestMapping(path = "/getBySessionId2", method = RequestMethod.GET)
    public ResponseEntity<List<WordDtoWithId>> getBySessionId2(@RequestParam long sessionId) {
        List<WordDtoWithId> wordList = repository.findWordBySessionId(sessionId)
                .stream()
                .map(WordMapper::mapWithId)
                .collect(Collectors.toList());
        return ResponseEntity.ok(wordList);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteById(@RequestParam long wordId) {
        service.deleteWord(wordId);
        return ResponseEntity.ok("Usunięto słowko");
    }
}
