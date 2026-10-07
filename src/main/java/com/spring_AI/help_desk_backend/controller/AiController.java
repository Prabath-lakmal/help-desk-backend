package com.spring_AI.help_desk_backend.controller;

import com.spring_AI.help_desk_backend.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/helpdesk")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")

public class AiController {

    private final AiService service;

    @PostMapping
    public ResponseEntity<String> getResponse(@RequestBody String query){
        return null;
    }

    @PostMapping(value = "/stream")
    public Flux<String> streamResponse(@RequestBody String query){
        return null;
    }

}
