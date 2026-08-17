package TicketBooking.com.service;

import TicketBooking.com.dto.request.LogAndAlertRequest;
import TicketBooking.com.dto.response.LogAndAlertResponse;
import TicketBooking.com.enums.LogLevel;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface AlertLogsService {
    LogAndAlertResponse addLogAndAlert(LogAndAlertRequest request);
    List<LogAndAlertResponse> getAllLogs();
    List<LogAndAlertResponse> getLogByLevel(LogLevel level);
    LogAndAlertResponse getLogById(Long id);
    void deleteLog(Long id);

}
