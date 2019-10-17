package ru.nazarfatichov.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.nazarfatichov.form.MessageForm;
import ru.nazarfatichov.service.UserService;
import ru.nazarfatichov.transfer.MessageDto;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MessageController {

    @Qualifier("jwtUserService")
    @Autowired
    private UserService userService;

    private final Map<String, List<MessageDto>> messages = new HashMap<>();

    @PostMapping("/messages")
    public ResponseEntity<Object> receiveMessage(@RequestBody MessageForm message, @RequestParam String token) {
        String login;
        if(token.length() == 10){
            login = userService.getUserByToken(token).getLogin();
        } else {
            Claims claims = (Claims) Jwts.parser().setSigningKey("bla bla").parse(token).getBody();
            login = claims.get("login", String.class);
        }
        if (!messages.containsKey(login)) {
            messages.put(login, new ArrayList<>());
        }

        MessageDto messageDto = MessageDto.builder()
                .text(message.getText())
                .login(login)
                .build();

        for (List<MessageDto> pageMessages : messages.values()) {
            synchronized (pageMessages) {
                pageMessages.add(messageDto);
                pageMessages.notifyAll();
            }
        }
        return ResponseEntity.ok().build();
    }

    @SneakyThrows
    @GetMapping("/messages")
    public ResponseEntity<List<MessageDto>> getMessagesForPage(@RequestParam String token) {
        String login = userService.getUserByToken(token).getLogin();
        synchronized (messages.get(login)) {
            if (messages.get(login).isEmpty()) {
                messages.get(login).wait();
            }
            List<MessageDto> response = new ArrayList<>(messages.get(login));
            messages.get(login).clear();
            return ResponseEntity.ok(response);
        }
    }


}
