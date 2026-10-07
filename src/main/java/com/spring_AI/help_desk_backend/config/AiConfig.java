package com.spring_AI.help_desk_backend.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    private Logger logger = LoggerFactory.getLogger(AiConfig.class);
//    public JdbcChatMemoryRepository jdbcChatMemoryRepository(){
//        return JdbcChatMemoryRepository.builder()
//                .jdbcTemplate()
//                .jdbcTemplate()
//                .build();
//    }

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder, JdbcChatMemoryRepository jdbcChatMemoryRepository){
        //chat memory create

        var chatMemory = MessageWindowChatMemory.builder()
                .chatMemoryRepository(jdbcChatMemoryRepository)
                .maxMessages(15)
                .build();

        logger.info("ChatClient been created.");
        logger.info("chat memory been created. {}", chatMemory.getClass().getName());
        return builder
                .defaultSystem("Summerize the response withing 400 words.")
                .defaultAdvisors(new SimpleLoggerAdvisor(),MessageChatMemoryAdvisor.builder(chatMemory).build()).build();


    }
}
