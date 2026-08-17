package TicketBooking.com.dto.request;

import TicketBooking.com.enums.LogLevel;
import TicketBooking.com.enums.LogType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogAndAlertRequest {
    private Long flightId; // It's  Empty
    private LogType logType;
    private LogLevel logLevel;
    private String message;

}
