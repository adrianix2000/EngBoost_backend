package pl.adrianix2000.Engboost.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.adrianix2000.Engboost.Entities.Word;
import pl.adrianix2000.Engboost.exceptions.AppException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
@Slf4j
public class WordService {

    public boolean processFile(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));

                br.lines().map(String::trim).map(this::extractWordFromLine)
                        .forEach(s -> System.out.println(s.toString()));


                return true;
            } catch (IOException e) {
                throw new AppException("błąd w prztwarzaniu pliku", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            throw new AppException("Podany plik jest pusty", HttpStatus.INTERNAL_SERVER_ERROR);
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
}
