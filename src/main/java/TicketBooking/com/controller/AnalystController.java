package TicketBooking.com.controller;

import TicketBooking.com.dto.response.AnalystResponse;
import TicketBooking.com.dto.response.ApiResponse;
import TicketBooking.com.service.AnalystService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff/api/analytics")
public class AnalystController {
    private final AnalystService analystService;
    public AnalystController(AnalystService analystService) {
        this.analystService = analystService;
    }
    @GetMapping
    public ResponseEntity<ApiResponse<AnalystResponse>> getAnalyst(){
        return ResponseEntity.ok(
                ApiResponse.<AnalystResponse>builder()
                        .message("Analyst summary retrieved successfully")
                        .status(200)
                        .data(analystService.getDashboardSummary())
                        .build()
        );
    }

}
