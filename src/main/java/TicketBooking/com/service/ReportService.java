package TicketBooking.com.service;

import TicketBooking.com.dto.response.FlightReportResponse;
import TicketBooking.com.dto.response.StaffReportResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportService {
    List<FlightReportResponse> getFlightOperationReport(LocalDateTime startDate, LocalDateTime endDate);
    List<StaffReportResponse> getStaffAssignmentReports();

}
