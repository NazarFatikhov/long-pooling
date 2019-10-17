package ru.nazarfatichov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @GetMapping("/chat")
    public String getChatPage(){
        return "messages";
    }

    @GetMapping("/signIn")
    public String getSignInPage(){
        return "sign-in";
    }

}
