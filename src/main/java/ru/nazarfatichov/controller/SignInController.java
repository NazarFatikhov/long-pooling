package ru.nazarfatichov.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.nazarfatichov.form.UserForm;
import ru.nazarfatichov.model.Token;
import ru.nazarfatichov.model.User;
import ru.nazarfatichov.service.UserService;

@RestController
public class SignInController {


    @Qualifier("jwtUserService")
    @Autowired
    private UserService userService;

    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(@RequestBody UserForm userForm) {
        Token token = userService.signIn(userForm.getLogin(), userForm.getPassword());
        return ResponseEntity.ok(token.getValue());
    }
}
