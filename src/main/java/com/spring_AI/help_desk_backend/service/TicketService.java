package com.spring_AI.help_desk_backend.service;

import com.spring_AI.help_desk_backend.entity.Ticket;
import com.spring_AI.help_desk_backend.repository.TicketRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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
    public Ticket createTicket(Ticket ticket){
//        ticket.setId(null);
        return ticketRepository.save(ticket);
    }

    //update ticket
    @Transactional
    public Ticket updateTicket(Ticket ticket){

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
