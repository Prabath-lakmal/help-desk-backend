package com.spring_AI.help_desk_backend.service;

import com.spring_AI.help_desk_backend.dto.CreateTicketRequest;
import com.spring_AI.help_desk_backend.dto.UpdateTicketRequest;
import com.spring_AI.help_desk_backend.entity.Status;
import com.spring_AI.help_desk_backend.entity.Ticket;
import com.spring_AI.help_desk_backend.repository.TicketRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Getter
@Setter

public class TicketService {

    private final TicketRepository ticketRepository;

    //create ticket
    @Transactional
    public Ticket createTicket(CreateTicketRequest request){
        Ticket ticket = Ticket.builder()
                .summary(request.summary())
                .priority(request.priority())
                .category(request.category())
                .description(request.description())
                .email(request.email())
                .status(request.status() != null ? request.status() : Status.OPEN)
                .build();
        try {
            return ticketRepository.save(ticket);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException(
                    "A ticket already exists for email: " + request.email(), e);
        }
    }

    //update ticket
    @Transactional
    public Ticket updateTicket(UpdateTicketRequest request){
        Ticket ticket = ticketRepository.findById(request.id())
                .orElseThrow(() -> new IllegalStateException(
                        "No ticket found with id: " + request.id()));
        if (request.summary() != null) {
            ticket.setSummary(request.summary());
        }
        if (request.priority() != null) {
            ticket.setPriority(request.priority());
        }
        if (request.category() != null) {
            ticket.setCategory(request.category());
        }
        if (request.description() != null) {
            ticket.setDescription(request.description());
        }
        if (request.email() != null) {
            ticket.setEmail(request.email());
        }
        if (request.status() != null) {
            ticket.setStatus(request.status());
        }
        return ticketRepository.save(ticket);
    }

    //get ticket logic
    public Ticket getTicket(Long ticketId){

        return ticketRepository.findById(ticketId).orElse(null);
    }

    //get ticket by username
    public Ticket getTicketByEmailId(String username){

        return ticketRepository.findByEmail(username).orElse(null);
    }
}
