package com.spring_AI.help_desk_backend.tool;

import com.spring_AI.help_desk_backend.dto.CreateTicketRequest;
import com.spring_AI.help_desk_backend.dto.UpdateTicketRequest;
import com.spring_AI.help_desk_backend.entity.Priority;
import com.spring_AI.help_desk_backend.entity.Status;
import com.spring_AI.help_desk_backend.entity.Ticket;
import com.spring_AI.help_desk_backend.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketDatabaseTool {

    private final TicketService ticketService;

    //create ticket tool
    @Tool(description = "This tool helps to create new ticket in database. The ticket id is generated automatically and must NOT be provided.")
    public Ticket createTicketTool(
            @ToolParam(description = "Short summary of the issue.") String summary,
            @ToolParam(description = "Detailed description of the issue.") String description,
            @ToolParam(description = "Priority of the ticket: LOW, MEDIUM, HIGH or URGENT.") Priority priority,
            @ToolParam(description = "Category of the issue, e.g. technical, account, hardware or software.") String category,
            @ToolParam(description = "Email id of the user reporting the issue.") String email,
            @ToolParam(description = "Status of the ticket: OPEN, CLOSED or RESOLVED. Use OPEN for new tickets.") Status status){
        CreateTicketRequest request = new CreateTicketRequest(summary, priority, category, description, email, status);
        return ticketService.createTicket(request);
    }

    //get ticket using username
    @Tool(description = "This tool helps to get ticket by username.")
    public Ticket getTicketByUserName(@ToolParam(description = "email id whose ticket is required") String emailid){
        return ticketService.getTicketByEmailId(emailid);
    }

    @Tool(description = "This tool helps to update an existing ticket. The ticket id is required to identify which ticket to update. Only provide the fields that need to change.")
    public Ticket updateTicket(
            @ToolParam(description = "Id of the ticket to update.") Long id,
            @ToolParam(description = "New summary, or null to keep unchanged.", required = false) String summary,
            @ToolParam(description = "New priority: LOW, MEDIUM, HIGH or URGENT, or null to keep unchanged.", required = false) Priority priority,
            @ToolParam(description = "New category, or null to keep unchanged.", required = false) String category,
            @ToolParam(description = "New description, or null to keep unchanged.", required = false) String description,
            @ToolParam(description = "New email, or null to keep unchanged.", required = false) String email,
            @ToolParam(description = "New status: OPEN, CLOSED or RESOLVED, or null to keep unchanged.", required = false) Status status){
        UpdateTicketRequest request = new UpdateTicketRequest(id, summary, priority, category, description, email, status);
        return ticketService.updateTicket(request);
    }

    //get current system time
    @Tool(description = "This tool helps to get current system time.")
    public String getCurrentTime(){
        return String.valueOf(System.currentTimeMillis());
    }
}
