package pl.adrianix2000.Engboost.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@RestController
@RequestMapping("/words")
@Slf4j
public class WordController {

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public ResponseEntity<String> handleUploadedFile(@RequestParam MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
                StringBuilder content = new StringBuilder();
                String line;

                while ((line = br.readLine()) != null) {
                    // Tutaj możesz przetwarzać każdą linię pliku
                    // Na przykład, można ją dodać do StringBuilder
                    content.append(line).append("\n");
                }

                // Tutaj możesz przetwarzać całą zawartość pliku (content) lub zapisywać ją do bazy danych
                log.info(" " + content.toString());

                return ResponseEntity.ok("File uploaded successfully!");
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to upload the file: " + e.getMessage());
            }
        } else {
            return ResponseEntity.badRequest().body("File is empty!");
        }
    }
}
