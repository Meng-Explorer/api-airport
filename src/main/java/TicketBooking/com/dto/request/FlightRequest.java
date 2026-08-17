package TicketBooking.com.dto.request;

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
public class FlightRequest {
    // accept ID from Staff or User
    private Long airlineId;
    private Long originAirportId;
    private Long departureAirportId;
    private Long gateId; // we can save null if gate doesn't open
    private String aircraftType;
    private String tailNumber;
    private LocalDateTime scheduledDeparture;
    private LocalDateTime actualDeparture;
    private LocalDateTime etaEtd;
    private FlightStatus status;
    // we can not take field cause this here make new ON_TIME Automatic
}
