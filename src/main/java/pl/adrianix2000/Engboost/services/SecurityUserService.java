package pl.adrianix2000.Engboost.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.adrianix2000.Engboost.Configuration.SecurityUser;
import pl.adrianix2000.Engboost.Entities.User;
import pl.adrianix2000.Engboost.repositories.InMemeryUserRepository;

import java.util.Optional;

@Service
public class SecurityUserService implements UserDetailsService {

    private InMemeryUserRepository userRepository;

    public SecurityUserService(InMemeryUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> foundedUser = userRepository.findByUserName(username);
        if(foundedUser.isPresent()) {
            return new SecurityUser(foundedUser.get());
        }

        throw new UsernameNotFoundException("Nie ma uzytkownika o nazwie uzytkownika" + username);
    }
}
