package TicketBooking.com.service;

import TicketBooking.com.dto.request.GateRequest;
import TicketBooking.com.dto.response.GateResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface GateService {
    GateResponse createGate(GateRequest request);
    List<GateResponse> getGates();
    GateResponse getGateById(Long id);
    GateResponse updateGate(Long id,GateRequest request);
    void deleteGate(Long id);

}
