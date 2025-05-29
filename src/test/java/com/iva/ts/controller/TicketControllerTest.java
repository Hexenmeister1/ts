package com.iva.ts.controller;

import com.iva.ts.dto.TicketRequest;
import com.iva.ts.dto.TicketResponse;
import com.iva.ts.service.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.UUID;

import static com.iva.ts.entity.Ticket.Priority.HIGH;
import static com.iva.ts.entity.Ticket.Status.OPEN;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TicketService ticketService;

    @Test
    void createTicket_ShouldReturnCreatedStatus() throws Exception {
        TicketRequest request = new TicketRequest();
        request.setTitle("Test Ticket");
        request.setDescription("Test Description");
        request.setPriority(HIGH);

        TicketResponse response = new TicketResponse();
        response.setId(UUID.randomUUID());
        response.setTitle(request.getTitle());
        response.setDescription(request.getDescription());
        response.setPriority(request.getPriority());
        response.setStatus(OPEN);

        when(ticketService.createTicket(any(TicketRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Test Ticket\",\"description\":\"Test Description\",\"priority\":\"HIGH\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("Test Ticket"))
                .andExpect(jsonPath("$.description").value("Test Description"))
                .andExpect(jsonPath("$.priority").value("HIGH"))
                .andExpect(jsonPath("$.status").value("OPEN"));
    }

    @Test
    void getAllTickets_ShouldReturnOkStatus() throws Exception {
        TicketResponse response1 = new TicketResponse();
        response1.setId(UUID.randomUUID());
        response1.setTitle("Ticket 1");
        response1.setDescription("Description 1");
        response1.setPriority(HIGH);
        response1.setStatus(OPEN);

        TicketResponse response2 = new TicketResponse();
        response2.setId(UUID.randomUUID());
        response2.setTitle("Ticket 2");
        response2.setDescription("Description 2");
        response2.setPriority(HIGH);
        response2.setStatus(OPEN);

        when(ticketService.getAllTickets()).thenReturn(Arrays.asList(response1, response2));

        mockMvc.perform(get("/api/tickets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Ticket 1"))
                .andExpect(jsonPath("$[1].title").value("Ticket 2"));
    }

    @Test
    void deleteTicket_ShouldReturnNoContentStatus() throws Exception {
        UUID ticketId = UUID.randomUUID();
        
        mockMvc.perform(delete("/api/tickets/" + ticketId))
                .andExpect(status().isNoContent());
    }
}