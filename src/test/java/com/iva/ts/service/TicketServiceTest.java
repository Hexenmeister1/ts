package com.iva.ts.service;

import com.iva.ts.dto.TicketRequest;
import com.iva.ts.dto.TicketResponse;
import com.iva.ts.entity.Ticket;
import com.iva.ts.repository.TicketRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.iva.ts.entity.Ticket.Priority.HIGH;
import static com.iva.ts.entity.Ticket.Priority.MEDIUM;
import static com.iva.ts.entity.Ticket.Status.OPEN;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    @Test
    void createTicket_ShouldReturnTicketResponse() {
        // Arrange
        TicketRequest request = new TicketRequest();
        request.setTitle("Test Ticket");
        request.setDescription("Test Description");
        request.setPriority(HIGH);

        Ticket savedTicket = new Ticket();
        savedTicket.setId(UUID.randomUUID());
        savedTicket.setTitle(request.getTitle());
        savedTicket.setDescription(request.getDescription());
        savedTicket.setPriority(request.getPriority());
        savedTicket.setStatus(OPEN);

        when(ticketRepository.save(any(Ticket.class))).thenReturn(savedTicket);

        // Act
        TicketResponse response = ticketService.createTicket(request);

        // Assert
        assertNotNull(response);
        assertEquals(savedTicket.getId(), response.getId());
        assertEquals(savedTicket.getTitle(), response.getTitle());
        assertEquals(savedTicket.getDescription(), response.getDescription());
        assertEquals(savedTicket.getPriority(), response.getPriority());
        assertEquals(savedTicket.getStatus(), response.getStatus());

        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }

    @Test
    void getAllTickets_ShouldReturnListOfTicketResponses() {
        // Arrange
        Ticket ticket1 = new Ticket();
        ticket1.setId(UUID.randomUUID());
        ticket1.setTitle("Ticket 1");
        ticket1.setDescription("Description 1");
        ticket1.setPriority(MEDIUM);
        ticket1.setStatus(OPEN);

        Ticket ticket2 = new Ticket();
        ticket2.setId(UUID.randomUUID());
        ticket2.setTitle("Ticket 2");
        ticket2.setDescription("Description 2");
        ticket2.setPriority(HIGH);
        ticket2.setStatus(OPEN);

        when(ticketRepository.findAll()).thenReturn(Arrays.asList(ticket1, ticket2));

        // Act
        List<TicketResponse> responses = ticketService.getAllTickets();

        // Assert
        assertNotNull(responses);
        assertEquals(2, responses.size());

        verify(ticketRepository, times(1)).findAll();
    }
}
