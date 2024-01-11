package pl.adrianix2000.Engboost.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.adrianix2000.Engboost.Entities.*;
import pl.adrianix2000.Engboost.exceptions.AppException;
import pl.adrianix2000.Engboost.repositories.InMemeryUserRepository;
import pl.adrianix2000.Engboost.repositories.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    public User registration(UserRegistrationEntity registrationEntity) {
        if(repository.findByUserName(registrationEntity.getUserName()).isEmpty()) {
            User newUser = User.builder()
                    .userName(registrationEntity.getUserName())
                    .firstName(registrationEntity.getFirstName())
                    .lastName(registrationEntity.getLastName())
                    .email(registrationEntity.getEmail())
                    .password(passwordEncoder.encode(registrationEntity.getPassword()))
                    .role(UserRole.USER)
                    .build();

            repository.save(newUser);

            return newUser;
        } else {
            throw new AppException("użytkownik o podanej nazwie już istnieje !", HttpStatus.BAD_REQUEST);
        }

    }

    public UserDto login(UserLoginRequest loginRequest) {
        Optional<User> foundedUser = repository.findByUserName(loginRequest.getUsername());
        if(foundedUser.isPresent()) {
            User user = foundedUser.get();
            if(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
               return UserDto.builder()
                       .username(user.getUserName())
                       .lastname(user.getLastName())
                       .firstname(user.getFirstName())
                       .email(user.getEmail())
                       .build();
            }
            throw new AppException("Podano nie prawidłowe hasło", HttpStatus.BAD_REQUEST);
        } else {
            throw new AppException("Podano nie prawidłową nazwę użytkownika", HttpStatus.NOT_FOUND);
        }
    }
}
