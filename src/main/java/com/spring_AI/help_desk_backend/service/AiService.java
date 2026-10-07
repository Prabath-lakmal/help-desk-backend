package com.spring_AI.help_desk_backend.service;

import com.spring_AI.help_desk_backend.tool.EmailTool;
import com.spring_AI.help_desk_backend.tool.TicketDatabaseTool;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
@Getter
@Setter

public class AiService {

    private final ChatClient chatClient;

    private final TicketDatabaseTool ticketDatabaseTool;

    private final EmailTool emailTool;

    @Value("classpath:/helpdesk-system.st")
    private Resource systemPromptResource;

    //basic call to LLM
    public String getResponseFromAssistant(String query,String conversationId){
        return  this.chatClient
                .prompt()
                .advisors(advisorSpec->advisorSpec.param(ChatMemory.CONVERSATION_ID,conversationId))
                //tool information
                .tools(ticketDatabaseTool, emailTool)
                .system(systemPromptResource)
                .user(query)
                .call()
                .content();
    }

    public Flux<String> streamResponseFromAssistant(String query, String conversationId){

        return this.chatClient
                .prompt()
                .advisors(advisorSpec-> advisorSpec.param(ChatMemory.CONVERSATION_ID,conversationId))
                .tools(ticketDatabaseTool,emailTool)
                .system(systemPromptResource)
                .user(query)
                .stream()
                .content();
    }

}
