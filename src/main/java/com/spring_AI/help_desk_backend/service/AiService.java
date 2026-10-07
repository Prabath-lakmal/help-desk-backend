package com.spring_AI.help_desk_backend.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
@Setter

public class AiService {

    private final ChatClient chatClient;

    //basic call to LLM
    public String getResponseFromAssistant(String query){
        return  this.chatClient
                .prompt()
                .user(query)
                .call()
                .content();
    }

}
