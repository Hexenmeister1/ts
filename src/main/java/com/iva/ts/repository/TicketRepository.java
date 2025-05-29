package com.iva.ts.repository;

import com.iva.ts.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Ticket DB repository
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    List<Ticket> findByStatus(Ticket.Status status);
    List<Ticket> findByPriority(Ticket.Priority priority);
    List<Ticket> findByStatusAndPriority(Ticket.Status status, Ticket.Priority priority);
}