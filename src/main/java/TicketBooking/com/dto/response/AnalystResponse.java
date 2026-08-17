package TicketBooking.com.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalystResponse {

    private long totalFlight;
    private long totalAirline;
    private long totalStaff;
    private long totalAirport;
    private long totalActiveAssignment;
    private long totalAlertLogs;

    private Map<String,Long> flightByStatus;
}
