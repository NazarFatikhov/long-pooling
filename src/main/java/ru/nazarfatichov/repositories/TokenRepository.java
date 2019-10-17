package ru.nazarfatichov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nazarfatichov.model.Token;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findOneByValue(String value);
}
