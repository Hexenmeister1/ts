package com.iva.ts.controller;

import com.iva.ts.dto.TicketRequest;
import com.iva.ts.dto.TicketResponse;
import com.iva.ts.entity.Ticket;
import com.iva.ts.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller to manage Ticket entity
 */
@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
@Tag(name = "Ticket Management System", description = "API for managing tickets")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new ticket")
    public TicketResponse createTicket(@RequestBody TicketRequest ticketRequest) {
        return ticketService.createTicket(ticketRequest);
    }

    @GetMapping
    @Operation(summary = "Get all tickets")
    public List<TicketResponse> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get tickets by status")
    public List<TicketResponse> getTicketsByStatus(@PathVariable Ticket.Status status) {
        return ticketService.getTicketsByStatus(status);
    }

    @GetMapping("/priority/{priority}")
    @Operation(summary = "Get tickets by priority")
    public List<TicketResponse> getTicketsByPriority(@PathVariable Ticket.Priority priority) {
        return ticketService.getTicketsByPriority(priority);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a ticket")
    public TicketResponse updateTicket(@PathVariable UUID id, @RequestBody TicketRequest ticketRequest) {
        return ticketService.updateTicket(id, ticketRequest);
    }

    @PutMapping("/{id}/close")
    @Operation(summary = "Close a ticket")
    public TicketResponse closeTicket(@PathVariable UUID id) {
        return ticketService.closeTicket(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a ticket")
    public void deleteTicket(@PathVariable UUID id) {
        ticketService.deleteTicket(id);
    }
}