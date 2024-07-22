package io.justina.h106javareact.adapters.controllers;

import io.justina.h106javareact.application.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    //public final EmailService emailService;
/*
    @GetMapping("/emailValidation/{token}/{email}")
        // /emailValidation/token=askjdajs/email=lalala@gmail.com
    String EmailValidation(@PathVariable("token") String token, @PathVariable("email") String email) throws Exception {
        emailService.validateToken(token, email);
        return emailService.validateToken(token, email);
    }*/

}