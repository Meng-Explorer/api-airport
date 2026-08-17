package TicketBooking.com.service;

import TicketBooking.com.dto.request.AirportRequest;
import TicketBooking.com.dto.response.AirportResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AirportService {
    AirportResponse createAirport(AirportRequest request);
    List<AirportResponse> getAirports();
    AirportResponse getAirportById(Long id);
    AirportResponse updateAirport(Long id,AirportRequest request);
    void deleteAirport(Long id);
}
