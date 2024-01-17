package pl.adrianix2000.Engboost.Controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.adrianix2000.Engboost.services.WordService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@RestController
@RequestMapping("/words")
@Slf4j
@RequiredArgsConstructor
public class WordController {

    private final WordService service;

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public ResponseEntity<String> handleUploadedFile(@RequestParam("file") MultipartFile file, @RequestParam("sessionId") String sessionId) {

//        log.info("Session id to: " + sessionId);
        log.info("dsfdsfsdf333333333333333333333333333333333333333 " + sessionId);
        service.processFile(file, sessionId);
        return ResponseEntity.ok("przeczytano plik");
    }
}
