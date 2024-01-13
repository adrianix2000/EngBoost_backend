package pl.adrianix2000.Engboost.Controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.adrianix2000.Engboost.Entities.Session;
import pl.adrianix2000.Engboost.Entities.SessionCreateRequest;
import pl.adrianix2000.Engboost.Entities.SessionDto;
import pl.adrianix2000.Engboost.repositories.SessionRepository;
import pl.adrianix2000.Engboost.services.SessionService;

import java.util.List;

@RestController
@RequestMapping("/sessions")
@Slf4j
@RequiredArgsConstructor
public class SessionController {

    @Autowired
    private final SessionRepository repository;

    @Autowired
    private final SessionService service;

    @RequestMapping(path = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<Session>> getAllSessions() {
        return ResponseEntity.ok(repository.findAll());
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseEntity<SessionDto> createSession(@RequestBody SessionCreateRequest request) {
        SessionDto response = service.createSession(request);
        return ResponseEntity.ok(response);
    }
}
