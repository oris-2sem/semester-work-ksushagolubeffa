package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.form.UserForm;
import org.example.exceptions.UserAlreadyExistException;
import org.example.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class SignController {

    private final UserService service;

    // all works correct
    @PreAuthorize("isAnonymous()")
    @PostMapping("/signUp")
    public RedirectView createUser(@Valid @RequestParam("username") String username,
                             @Valid @RequestParam("email") String email,
                             @Valid @RequestParam("password") String password,
                             @Valid @RequestParam("repeat") String repeat) throws IOException, UserAlreadyExistException {
        if(!password.equals(repeat)) {
            return new RedirectView("/error");
        }
        if(!service.saveUser(username, email, password)){
            return new RedirectView("/error");
        }
        return new RedirectView("/signIn");
    }

    // all works correct
    @PreAuthorize("isAnonymous()")
    @GetMapping("/signUp")
    public String signUpUser(){
        return "sign-up";
    }

    // all works correct
    @PreAuthorize("isAnonymous()")
    @PostMapping("/signIn")
    public String loginUser(@Valid UserForm form) {
        service.signInUser(form);
        return "profile";

    }

    // all works correct
    @PreAuthorize("isAnonymous()")
    @GetMapping("/signIn")
    public String signInUser(){
        return "sign-in";
    }

}
