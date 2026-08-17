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
public class FlightReportResponse {
    private long flightId;
    private String airlineName;
    private String tailNumber;
    private String originCode;
    private String aircraftType;
    private String destinationCode;
    private String gateNumber;
    private LocalDateTime scheduledDeparture;
    private LocalDateTime actualDeparture;
    private FlightStatus status;
}
