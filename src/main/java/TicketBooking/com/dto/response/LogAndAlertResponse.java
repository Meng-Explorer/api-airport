package TicketBooking.com.dto.response;

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
public class LogAndAlertResponse {
    private Long logId;
    private Long flightId;
    private String flightInfo;
    private LogType logType;
    private LogLevel logLevel;
    private LocalDateTime timeStamp;
    private String message;
}
