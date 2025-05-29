package com.iva.ts.dto;

import com.iva.ts.entity.Ticket;
import lombok.Getter;
import lombok.Setter;

/**
 * Ticket Rest API Requst objet
 */
@Getter
@Setter
public class TicketRequest {
    private String title;
    private String description;
    private Ticket.Priority priority;
}
