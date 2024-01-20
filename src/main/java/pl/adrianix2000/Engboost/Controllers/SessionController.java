package pl.adrianix2000.Engboost.Controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.adrianix2000.Engboost.Entities.Session;
import pl.adrianix2000.Engboost.Entities.SessionCreateRequest;
import pl.adrianix2000.Engboost.Entities.SessionDto;
import pl.adrianix2000.Engboost.Entities.SessionModifyRequest;
import pl.adrianix2000.Engboost.Mappers.SessionMapper;
import pl.adrianix2000.Engboost.exceptions.AppException;
import pl.adrianix2000.Engboost.repositories.SessionRepository;
import pl.adrianix2000.Engboost.services.SessionService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @RequestMapping(path = "/getById", method = RequestMethod.GET)
    public ResponseEntity<SessionDto> getById(@RequestParam long id) {

        Optional<Session> session = repository.findById(id);
        if(session.isPresent()) {
            SessionDto sessionDto = mapper.map(session.get());
            return ResponseEntity.ok(sessionDto);
        } else {
            throw new AppException("Sesja nie została znaleziona", HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteById(@RequestParam long sessionId) {
        service.deleteSession(sessionId);
        return ResponseEntity.ok("Usunięto sesje");
    }

    @RequestMapping(path = "/modify", method = RequestMethod.PATCH)
    public ResponseEntity<String> modifySession(@RequestParam long sessionId,
                                                @RequestBody SessionModifyRequest request) {
        service.modifySessionData(request, sessionId);
        return ResponseEntity.ok("Udalo sie zmodyfikowac dane sesji");
    }

    @RequestMapping(path = "/getPublicSessions", method = RequestMethod.GET)
    public ResponseEntity<List<SessionDto>> getPublicSessions() {
        List<Session> sharedSession = repository.findByIssharedTrue();

        return ResponseEntity.ok(sharedSession.stream()
                .map(mapper::map)
                .collect(Collectors.toList()));
    }
}
