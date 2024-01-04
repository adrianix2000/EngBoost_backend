package pl.adrianix2000.Engboost.Controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.adrianix2000.Engboost.Entities.User;
import pl.adrianix2000.Engboost.repositories.InMemeryUserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private final InMemeryUserRepository repository;

    public UserController(InMemeryUserRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(path = "/getAll", method = RequestMethod.GET)
    public List<User> getAll() {
       return repository.getAllUsers();
    }

    @RequestMapping(path = "findByUserName/{userName}", method = RequestMethod.GET)
    public ResponseEntity<String> findUserByUserName(@PathVariable("userName") String username) {
        Optional<User> user = repository.findByUserName(username);
        if(user.isPresent()) {
            log.info(user.toString());
            return ResponseEntity.ok("user with username: " + username + " was found");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No user with username like: " + username);
    }


}
