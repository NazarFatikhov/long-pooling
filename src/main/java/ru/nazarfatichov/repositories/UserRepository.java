package ru.nazarfatichov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nazarfatichov.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByLogin(String login);

}
