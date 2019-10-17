package ru.nazarfatichov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.nazarfatichov.service.UserService;

@Controller
public class SignUpController {

    @Qualifier("jwtUserService")
    @Autowired
    private UserService userService;

    @GetMapping("/signUp")
    public String getSignUpPage(){
        return "sign-up";
    }

    @PostMapping("/signUp")
    public String signUp(@RequestParam String login, @RequestParam String password){
        userService.signUp(login, password);
        return "redirect:" + MvcUriComponentsBuilder.fromMethodName(SignUpController.class, "getSignUpPage").build();
    }


}
