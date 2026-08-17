package TicketBooking.com.controller;

import TicketBooking.com.dto.response.ApiResponse;
import TicketBooking.com.dto.response.FlightReportResponse;
import TicketBooking.com.dto.response.StaffReportResponse;
import TicketBooking.com.service.ReportService;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/staff/api/reports")
public class ReportController {
    private final ReportService reportService;
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/flights")
    public ResponseEntity<ApiResponse<List<FlightReportResponse>>> getFlights(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate){
        return ResponseEntity.ok(
                ApiResponse.<List<FlightReportResponse>>builder()
                        .message("Flight operation report retrieved success")
                        .status(200)
                        .data(reportService.getFlightOperationReport(startDate,endDate))
                        .build()
        );
    }
    @GetMapping("/staffs")
    public ResponseEntity<ApiResponse<List<StaffReportResponse>>> getStaffs(){
        return ResponseEntity.ok(
                ApiResponse.<List<StaffReportResponse>>builder()
                        .message("Staff assignment report retrieved successfully")
                        .status(200)
                        .data(reportService.getStaffAssignmentReports())
                        .build()
        );
    }


}
