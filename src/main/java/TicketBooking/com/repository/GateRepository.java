package TicketBooking.com.repository;

import TicketBooking.com.models.Gate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GateRepository extends JpaRepository<Gate,Long> {
    // This Function find Gate that in Terminal
    List<Gate> findByTerminal_TerminalId(Long terminalId);
}
