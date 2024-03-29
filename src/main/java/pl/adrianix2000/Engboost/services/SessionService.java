package pl.adrianix2000.Engboost.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.adrianix2000.Engboost.Entities.*;
import pl.adrianix2000.Engboost.Mappers.SessionMapper;
import pl.adrianix2000.Engboost.exceptions.AppException;
import pl.adrianix2000.Engboost.repositories.SessionRepository;
import pl.adrianix2000.Engboost.repositories.UserRepository;

import javax.swing.text.html.Option;
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

    public void deleteSession(long id) {
        Optional<Session> session = sessionRepository.findById(id);
        if(session.isPresent()) {
            Session foundedSession = session.get();
            sessionRepository.deleteById(foundedSession.getId());
        } else {
            throw new AppException("Nie odnaleziono sesji", HttpStatus.NOT_FOUND);
        }
    }

    public void modifySessionData(SessionModifyRequest request, long id) {
        Optional<Session> session = sessionRepository.findById(id);
        if(session.isPresent()) {
            Session foundedSession = session.get();

            if(request.getTitle() != null) {
                foundedSession.setTitle(request.getTitle());
                foundedSession.setIsshared(request.isIsshared());

                sessionRepository.save(foundedSession);
            } else {
                throw new AppException("Nie pełne dane", HttpStatus.BAD_REQUEST);
            }
        }
        else {
            throw new AppException("Nie odnaleziono sesji", HttpStatus.NOT_FOUND);
        }
    }

    public void incrementNumberOfViews(long id) {
        Optional<Session> session = sessionRepository.findById(id);
        if(session.isPresent()) {
            Session foundedSession = session.get();

            foundedSession.setViewnumber(
                    foundedSession.getViewnumber()+1
            );

            sessionRepository.save(foundedSession);
        } else {
            throw new AppException("Nie odnaleziono sesji", HttpStatus.NOT_FOUND);
        }
    }
}
