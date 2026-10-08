package com.spring_AI.help_desk_backend.dto;

import com.spring_AI.help_desk_backend.entity.Priority;
import com.spring_AI.help_desk_backend.entity.Status;

public record UpdateTicketRequest(
        Long id,
        String summary,
        Priority priority,
        String category,
        String description,
        String email,
        Status status
) {
}
