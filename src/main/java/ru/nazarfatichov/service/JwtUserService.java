package ru.nazarfatichov.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nazarfatichov.model.Token;
import ru.nazarfatichov.model.User;
import ru.nazarfatichov.repositories.TokenRepository;
import ru.nazarfatichov.repositories.UserRepository;

import java.util.Optional;

@Service
public class JwtUserService extends TokenUserService {

    @Value("${key}")
    private String key;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    public Token signIn(String login, String password){
        User user = getUserByLogin(login);

        if(passwordEncoder.matches(password, user.getHashPassword())){
            Token token = Token.builder()
                    .user(user)
                    .value(jwtService.getJwt(login))
                    .build();

            tokenRepository.save(token);
            return token;
        } throw new IllegalArgumentException("Incorrect password");

    }

    @Override
    public User getUserByToken(String token) {
        Claims claims = (Claims) Jwts.parser().setSigningKey(key).parse(token).getBody();

        Optional<User> userOptional = userRepository.findOneByLogin(claims.get("login", String.class));

        if(userOptional.isPresent()){
            return userOptional.get();
        } throw new IllegalArgumentException("Bad token");
    }
}
