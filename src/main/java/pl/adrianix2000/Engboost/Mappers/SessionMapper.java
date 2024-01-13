package pl.adrianix2000.Engboost.Mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import pl.adrianix2000.Engboost.Entities.Session;
import pl.adrianix2000.Engboost.Entities.SessionDto;
import pl.adrianix2000.Engboost.Entities.User;
import pl.adrianix2000.Engboost.exceptions.AppException;
import pl.adrianix2000.Engboost.repositories.UserRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SessionMapper {

    @Autowired
    private final UserRepository userRepository;

    public SessionDto map(Session session) {
        return SessionDto.builder()
                .id(session.getId())
                .titie(session.getTitle())
                .createdate(session.getCreatedate())
                .isshared(session.isIsshared())
                .viewnumber(session.getViewnumber())
                .username(session.getAuthor().getUserName())
                .build();
    }

    public Session map(SessionDto sessionDto) {
        Optional<User> author = userRepository.findByUserName(sessionDto.getUsername());
        if(author.isPresent()) {
            return Session.builder()
                    .title(sessionDto.getTitie())
                    .author(author.get())
                    .viewnumber(sessionDto.getViewnumber())
                    .isshared(sessionDto.isIsshared())
                    .createdate(sessionDto.getCreatedate())
                    .build();
        } else {
            throw new AppException("Session map error: no user with username: " + sessionDto.getUsername() + " !",
                    HttpStatus.BAD_REQUEST);
        }
    }
}
