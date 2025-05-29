package com.iva.ts.dto;

import com.iva.ts.entity.Ticket;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Ticket REST Api response object
 */
@Getter
@Setter
public class TicketResponse {
    private UUID id;
    private String title;
    private String description;
    private Ticket.Priority priority;
    private Ticket.Status status;
}