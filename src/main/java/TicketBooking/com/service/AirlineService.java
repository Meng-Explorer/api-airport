package TicketBooking.com.service;

import TicketBooking.com.dto.request.AirlineRequest;
import TicketBooking.com.dto.response.AirlineResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AirlineService  {
    AirlineResponse createAirline(AirlineRequest request);
    List<AirlineResponse> getAllAirlines();
    AirlineResponse getAirlineById(Long id);
    AirlineResponse updateAirline(Long id,AirlineRequest request);
    void deleteAirline(Long id);
}
