package ru.nazarfatichov.service;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nazarfatichov.model.Token;
import ru.nazarfatichov.model.User;
import ru.nazarfatichov.repositories.TokenRepository;
import ru.nazarfatichov.repositories.UserRepository;

import java.util.Optional;

@Service
public class TokenUserService implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void signUp(String login, String password){
        String hashPassword = passwordEncoder.encode(password);

        User user = User.builder()
                .login(login)
                .hashPassword(hashPassword)
                .build();

        userRepository.save(user);
    }

    public User getUserByLogin(String login){
        Optional<User> userOptional = userRepository.findOneByLogin(login);

        if(userOptional.isPresent()){
            return userOptional.get();
        } throw new UsernameNotFoundException("User with login " + login + " not found");

    }

    public Token signIn(String login, String password){
        User user = getUserByLogin(login);

        if(passwordEncoder.matches(password, user.getHashPassword())){
            Token token = Token.builder()
                    .user(user)
                    .value(RandomStringUtils.random(10, true, true))
                    .build();

            tokenRepository.save(token);
            return token;
        } throw new IllegalArgumentException("Incorrect password");

    }

    public User getUserByToken(String token){
        Optional<Token> tokenOptional = tokenRepository.findOneByValue(token);

        if(tokenOptional.isPresent()){
            return tokenOptional.get().getUser();
        } throw new IllegalArgumentException("Bad token");
    }

}
