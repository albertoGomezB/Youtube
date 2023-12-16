package com.agb.securityjwt2023.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/youtube")
public class YoutubeController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> helloSecurity () {

        return ResponseEntity.ok("Hello from secured enpoint");
    }
}
