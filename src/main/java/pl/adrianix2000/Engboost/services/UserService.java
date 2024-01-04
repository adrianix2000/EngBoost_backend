package pl.adrianix2000.Engboost.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.adrianix2000.Engboost.Entities.*;
import pl.adrianix2000.Engboost.repositories.InMemeryUserRepository;

import java.util.Optional;

@Service
public class UserService {
    private InMemeryUserRepository repository;

    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(InMemeryUserRepository repository, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.passwordEncoder = encoder;
    }

    public OperationRespone registration(UserRegistrationEntity registrationEntity) {
        if(repository.findByUserName(registrationEntity.getUserName()).isEmpty()) {
            User newUser = User.builder()
                    .id(10L)
                    .userName(registrationEntity.getUserName())
                    .firstName(registrationEntity.getFirstName())
                    .lastName(registrationEntity.getLastName())
                    .email(registrationEntity.getEmail())
                    .password(passwordEncoder.encode(registrationEntity.getPassword()))
                    .role(UserRole.USER)
                    .build();

            repository.addUser(newUser);

            return OperationRespone.builder()
                    .information("Utworzono unowego użytkownika")
                    .status(HttpStatus.CREATED)
                    .obj(null)
                    .build();
        }

        return OperationRespone.builder()
                .information("użytkownik o podanej nazwie już istnieje !")
                .status(HttpStatus.CONFLICT)
                .obj(null)
                .build();
    }

    public OperationRespone login(UserLoginRequest loginRequest) {
        Optional<User> foundedUser = repository.findByUserName(loginRequest.getUsername());
        if(foundedUser.isPresent()) {
            User user = foundedUser.get();
            if(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return OperationRespone.builder()
                        .information("user was founded")
                        .status(HttpStatus.FOUND)
                        .obj(user)
                        .build();
            }
            return OperationRespone.builder()
                    .information("bad password")
                    .status(HttpStatus.OK)
                    .obj(null)
                    .build();
        }

        return OperationRespone.builder()
                .information("login failed")
                .status(HttpStatus.NOT_FOUND)
                .obj(null)
                .build();
    }
}
