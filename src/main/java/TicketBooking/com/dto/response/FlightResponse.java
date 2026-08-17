package TicketBooking.com.dto.response;

import TicketBooking.com.enums.FlightStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightResponse {
    private Long flightId;

    // Show Name or Code to easy check on User Interface
    private String airlineName;
    private String originAirportCode;
    private String destinationAirportCode;
    private String gateNumber;

    private String aircraftType;
    private String tailNumber;

    private LocalDateTime scheduledDeparture;
    private LocalDateTime actualDeparture;
    private LocalDateTime etaEtd;
    private FlightStatus status; // Using Enum
}
