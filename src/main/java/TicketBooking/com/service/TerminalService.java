package TicketBooking.com.service;

import TicketBooking.com.dto.request.TerminalRequest;
import TicketBooking.com.dto.response.TerminalResponse;
import org.springframework.stereotype.Service;

import java.util.List;
public interface TerminalService {
    TerminalResponse createTerminal(TerminalRequest request);
    List<TerminalResponse> getAllTerminalses();
    TerminalResponse updateTerminal(Long id,TerminalRequest request);
    void deleteTerminal(Long id);
}
