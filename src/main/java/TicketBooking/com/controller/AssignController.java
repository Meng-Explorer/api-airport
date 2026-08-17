package TicketBooking.com.controller;

import TicketBooking.com.dto.request.FlightAssignmentRequest;
import TicketBooking.com.dto.response.ApiResponse;
import TicketBooking.com.dto.response.FlightAssignmentResponse;
import TicketBooking.com.service.FlightAssignService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff/api/assigns")
public class AssignController {
    private final FlightAssignService flightAssignService;
    public AssignController(FlightAssignService flightAssignService) {
        this.flightAssignService = flightAssignService;
    }

    //CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<FlightAssignmentResponse>> create(@Valid @RequestBody FlightAssignmentRequest request){
        return ResponseEntity.ok(
                ApiResponse.<FlightAssignmentResponse>builder()
                        .message("Create success")
                        .status(201)
                        .data(flightAssignService.createAssignment(request))
                        .build()
        );

    }
    //GET DATA
    @GetMapping
    public ResponseEntity<ApiResponse<List<FlightAssignmentResponse>>> getAllAssignment(){
        return ResponseEntity.ok(
                ApiResponse.<List<FlightAssignmentResponse>>builder()
                        .message("Find all Data")
                        .status(200)
                        .data(flightAssignService.getAllAssignments())
                        .build()
        );
    }
    //GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FlightAssignmentResponse>> getAssignmentById(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.<FlightAssignmentResponse>builder()
                        .message("Find By Id")
                        .status(200)
                        .data(flightAssignService.getAssignedById(id))
                        .build()
        );
    }
    @GetMapping("/flight/{flightId}")
    public ResponseEntity<ApiResponse<List<FlightAssignmentResponse>>> getFlightById(@PathVariable Long flightId){
        return ResponseEntity.ok(
                ApiResponse.<List<FlightAssignmentResponse>>builder()
                        .message("Find Flight By ID")
                        .status(200)
                        .data(flightAssignService.getAssignedFlightById(flightId))
                        .build()
        );
    }
    @GetMapping("/staff/{staffId}")
    public ResponseEntity<ApiResponse<List<FlightAssignmentResponse>>>  getFlightByStaffId(@PathVariable Long staffId){
        return ResponseEntity.ok(
                ApiResponse.<List<FlightAssignmentResponse>>builder()
                        .message("Find By Staff")
                        .status(200)
                        .data(flightAssignService.getAssignedByStaffId(staffId))
                        .build()
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<FlightAssignmentResponse>>  update(@PathVariable Long id, @Valid @RequestBody FlightAssignmentRequest request){
        return ResponseEntity.ok(
                ApiResponse.<FlightAssignmentResponse>builder()
                        .message("updated success")
                        .status(202)
                        .data(flightAssignService.updateAssignment(id,request))
                        .build()
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>>  deleteById(@PathVariable Long id){
        flightAssignService.deleteAssignment(id);
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .message("Deleted Success")
                        .status(204)
                        .data("Delete Id: " + id)
                        .build()
        );

    }
}
