package pl.adrianix2000.Engboost.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.adrianix2000.Engboost.Entities.Session;
import pl.adrianix2000.Engboost.Entities.SessionCreateRequest;
import pl.adrianix2000.Engboost.Entities.SessionDto;
import pl.adrianix2000.Engboost.Entities.User;
import pl.adrianix2000.Engboost.Mappers.SessionMapper;
import pl.adrianix2000.Engboost.exceptions.AppException;
import pl.adrianix2000.Engboost.repositories.SessionRepository;
import pl.adrianix2000.Engboost.repositories.UserRepository;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionService {

    @Autowired
    private final SessionRepository sessionRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final SessionMapper mapper;

    public SessionDto createSession(SessionCreateRequest request) {
        Optional<User> author = userRepository.findByUserName(request.getUsername());
        if(author.isPresent()) {
            User foundedUser = author.get();
            Date currentDate = new Date();

            Session newSession = Session.builder()
                    .title(request.getTitle())
                    .createdate(currentDate)
                    .isshared(request.isIsshared())
                    .viewnumber(0)
                    .author(foundedUser)
                    .build();

            sessionRepository.save(newSession);

            return mapper.map(newSession);
        } else {
            throw new AppException("użytkownik o nazwie: " + request.getUsername() + " nie został odnaleziony !",
                    HttpStatus.BAD_REQUEST);
        }
    }

}
