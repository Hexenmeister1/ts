package com.iva.ts.service;

import com.iva.ts.dto.TicketRequest;
import com.iva.ts.dto.TicketResponse;
import com.iva.ts.entity.Ticket;
import com.iva.ts.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service class to manage life of the Ticket Entity in DB
 */
@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketResponse createTicket(TicketRequest ticketRequest) {
        Ticket ticket = new Ticket();
        ticket.setTitle(ticketRequest.getTitle());
        ticket.setDescription(ticketRequest.getDescription());
        ticket.setPriority(ticketRequest.getPriority());
        ticket.setStatus(Ticket.Status.OPEN);

        Ticket savedTicket = ticketRepository.save(ticket);
        return mapToResponse(savedTicket);
    }

    public List<TicketResponse> getAllTickets() {
        return ticketRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<TicketResponse> getTicketsByStatus(Ticket.Status status) {
        return ticketRepository.findByStatus(status)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<TicketResponse> getTicketsByPriority(Ticket.Priority priority) {
        return ticketRepository.findByPriority(priority)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public TicketResponse updateTicket(UUID id, TicketRequest ticketRequest) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        ticket.setTitle(ticketRequest.getTitle());
        ticket.setDescription(ticketRequest.getDescription());
        
        Ticket updatedTicket = ticketRepository.save(ticket);
        return mapToResponse(updatedTicket);
    }

    public TicketResponse closeTicket(UUID id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        ticket.setStatus(Ticket.Status.CLOSED);
        Ticket updatedTicket = ticketRepository.save(ticket);
        return mapToResponse(updatedTicket);
    }

    public void deleteTicket(UUID id) {
        ticketRepository.deleteById(id);
    }

    private TicketResponse mapToResponse(Ticket ticket) {
        TicketResponse response = new TicketResponse();
        response.setId(ticket.getId());
        response.setTitle(ticket.getTitle());
        response.setDescription(ticket.getDescription());
        response.setPriority(ticket.getPriority());
        response.setStatus(ticket.getStatus());
        return response;
    }
}
