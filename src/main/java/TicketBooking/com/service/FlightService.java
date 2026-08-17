package TicketBooking.com.service;

import TicketBooking.com.dto.request.FlightRequest;
import TicketBooking.com.dto.response.FlightResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FlightService {
    FlightResponse createFlight(FlightRequest request);
    List<FlightResponse>getAllFlights();
    FlightResponse getFlightById(Long id);
    FlightResponse updateFlight(Long id,FlightRequest request);
    void deleteFlight(Long id);
}
