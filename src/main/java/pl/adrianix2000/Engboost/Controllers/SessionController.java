package pl.adrianix2000.Engboost.Controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.adrianix2000.Engboost.Entities.Session;
import pl.adrianix2000.Engboost.Entities.SessionCreateRequest;
import pl.adrianix2000.Engboost.Entities.SessionDto;
import pl.adrianix2000.Engboost.Mappers.SessionMapper;
import pl.adrianix2000.Engboost.repositories.SessionRepository;
import pl.adrianix2000.Engboost.services.SessionService;

import java.util.Collections;
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

    @Autowired
    private final SessionMapper mapper;

    @RequestMapping(path = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<Session>> getAllSessions() {
        return ResponseEntity.ok(repository.findAll());
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseEntity<SessionDto> createSession(@RequestBody SessionCreateRequest request) {
        SessionDto response = service.createSession(request);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "/getByUserName", method = RequestMethod.GET)
    public ResponseEntity<List<SessionDto>> getSessionsByUserName(@RequestParam String username) {

        List<SessionDto> list = repository.findSessionsByUserName(username)
                .stream().map(s -> mapper.map(s)).toList();

        log.info("" + list);
        return ResponseEntity.ok(list);
    }
}
