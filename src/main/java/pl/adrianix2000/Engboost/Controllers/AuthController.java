package pl.adrianix2000.Engboost.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.adrianix2000.Engboost.Entities.OperationRespone;
import pl.adrianix2000.Engboost.Entities.User;
import pl.adrianix2000.Engboost.Entities.UserLoginRequest;
import pl.adrianix2000.Engboost.Entities.UserRegistrationEntity;
import pl.adrianix2000.Engboost.services.UserService;

@Controller
@RestController
@Slf4j
public class AuthController {

    private UserService service;

    @Autowired
    public AuthController(UserService service) {
        this.service = service;
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody UserRegistrationEntity user) {
        OperationRespone operationRespone = service.registration(user);
        return ResponseEntity.status(operationRespone.getStatus())
                .body(operationRespone.getInformation());
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<User> login(@RequestBody UserLoginRequest request) {
        OperationRespone operationRespone = service.login(request);
        return ResponseEntity.status(operationRespone.getStatus())
                .body((User)operationRespone.getObj());
    }

    @RequestMapping(path = "/hello", method = RequestMethod.POST)
    public String hello() {
        return "hello world";
    }
}
