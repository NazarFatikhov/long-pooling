package ru.nazarfatichov.service;

import ru.nazarfatichov.model.Token;
import ru.nazarfatichov.model.User;

public interface UserService {

    void signUp(String login, String password);

    User getUserByLogin(String login);

    Token signIn(String login, String password);

    User getUserByToken(String token);

}
