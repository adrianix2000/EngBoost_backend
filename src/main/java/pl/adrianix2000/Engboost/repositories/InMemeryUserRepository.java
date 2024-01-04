package pl.adrianix2000.Engboost.repositories;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import pl.adrianix2000.Engboost.Entities.User;
import pl.adrianix2000.Engboost.Entities.UserRole;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemeryUserRepository {
    private List<User> users;

    public InMemeryUserRepository() {
        users = new ArrayList<>();

        users.add(new User(1L, "Jan", "Kowalski", "jan_kowalski", "haslo123", "jan.kowalski@example.com", UserRole.USER));
        users.add(new User(2L, "Anna", "Nowak", "anna_nowak", "tajne567", "anna.nowak@example.com", UserRole.ADMIN));
        users.add(new User(3L, "Piotr", "Wiśniewski", "piotr_wisniewski", "bezpieczne789", "piotr.wisniewski@example.com", UserRole.USER));
        users.add(new User(4L, "Magdalena", "Wójcik", "magdalena_wojcik", "silnehaslo321", "magdalena.wojcik@example.com", UserRole.USER));
        users.add(new User(5L, "Krzysztof", "Kaczmarek", "krzysztof_kaczmarek", "tajemnicze654", "krzysztof.kaczmarek@example.com", UserRole.USER));
        users.add(new User(6L, "Agnieszka", "Lewandowska", "agnieszka_lewandowska", "haslotajne123", "agnieszka.lewandowska@example.com", UserRole.ADMIN));
        users.add(new User(7L, "Michał", "Wójcik", "michal_wojcik", "bezpieczne789", "michal.wojcik@example.com", UserRole.USER));
        users.add(new User(8L, "Karolina", "Jankowska", "karolina_jankowska", "haslo123", "karolina.jankowska@example.com", UserRole.USER));
        users.add(new User(9L, "Grzegorz", "Adamczyk", "grzegorz_adamczyk", "tajne567", "grzegorz.adamczyk@example.com", UserRole.ADMIN));
        users.add(new User(10L, "Ewa", "Nowakowska", "ewa_nowakowska", "silnehaslo321", "ewa.nowakowska@example.com", UserRole.USER));

    }

    public List<User> getAllUsers() {
        return users;
    }

    public Optional<User> findByUserName(String userName) {
        return users.stream()
                .filter(u -> u.getUserName().equals(userName))
                .findFirst();
    }

    public void addUser(User user) {
        users.add(user);
    }
}
