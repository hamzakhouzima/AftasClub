package com.youcode.aftasclub.contorller;


import com.youcode.aftasclub.exception.CompetitionNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompetitionController {
    @GetMapping("/hello")
    public void hello() {

    }
}