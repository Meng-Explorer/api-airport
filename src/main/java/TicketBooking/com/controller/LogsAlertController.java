package TicketBooking.com.controller;

import TicketBooking.com.dto.request.LogAndAlertRequest;
import TicketBooking.com.dto.response.ApiResponse;
import TicketBooking.com.dto.response.LogAndAlertResponse;
import TicketBooking.com.enums.LogLevel;
import TicketBooking.com.service.AlertLogsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff/api/alerts")
public class LogsAlertController {
    private final AlertLogsService alertLogsService;
    public LogsAlertController(AlertLogsService alertLogsService) {
        this.alertLogsService = alertLogsService;
    }
    @PostMapping
    public ResponseEntity<ApiResponse<LogAndAlertResponse>> create(@RequestBody LogAndAlertRequest request){
        return ResponseEntity.ok(
                ApiResponse.<LogAndAlertResponse>builder()
                        .message("Alert created")
                        .status(201)
                        .data(alertLogsService.addLogAndAlert(request))
                        .build()
        );
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<LogAndAlertResponse>>>getAllLogs(){
        return ResponseEntity.ok(
                ApiResponse.<List<LogAndAlertResponse>>builder()
                        .message("Find success")
                        .status(200)
                        .data(alertLogsService.getAllLogs())
                        .build()
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LogAndAlertResponse>> getLog(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.<LogAndAlertResponse>builder()
                        .message("find by id")
                        .status(200)
                        .data(alertLogsService.getLogById(id))
                        .build()
        );
    }
    @GetMapping("/level/{level}")
    public ResponseEntity<ApiResponse<List<LogAndAlertResponse>>> getLogsByLevel(@PathVariable LogLevel level){
        return ResponseEntity.ok(
                ApiResponse.<List<LogAndAlertResponse>>builder()
                        .message("Find Success")
                        .status(200)
                        .data(alertLogsService.getLogByLevel(level))
                        .build()
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteLog(@PathVariable Long id){
        alertLogsService.deleteLog(id);
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .message("delete success")
                        .status(200)
                        .data("delete success"+id)
                        .build());
    }



}
