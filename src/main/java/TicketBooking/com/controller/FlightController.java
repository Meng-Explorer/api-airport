package TicketBooking.com.controller;

import TicketBooking.com.dto.request.FlightRequest;
import TicketBooking.com.dto.response.ApiResponse;
import TicketBooking.com.dto.response.FlightResponse;
import TicketBooking.com.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff/api/flights")
public class FlightController {
    private final FlightService flightService;
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }
    //CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<FlightResponse>> createFlight(@Valid @RequestBody FlightRequest request){
        return ResponseEntity.ok(
                ApiResponse.<FlightResponse>builder()
                        .message("Flight Created successfully")
                        .status(201)
                        .data(flightService.createFlight(request))
                        .build()
        );
    }
    //READ ALL
    @GetMapping
    public ResponseEntity<ApiResponse<List<FlightResponse>>> getAllFlights(){
        return ResponseEntity.ok(
                ApiResponse.<List<FlightResponse>>builder()
                        .message("Flight retrieved successfully")
                        .status(200)
                        .data(flightService.getAllFlights())
                        .build()
        );

    }
    //GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FlightResponse>> getFlight( @PathVariable Long id ){
        return ResponseEntity.ok(
                ApiResponse.<FlightResponse>builder()
                        .message("Flight details retrieved")
                        .status(200)
                        .data(flightService.getFlightById(id))
                        .build()
        );
    }
    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<FlightResponse>> updateFlight( @PathVariable Long id,@Valid @RequestBody FlightRequest request){
        return ResponseEntity.ok(
                ApiResponse.<FlightResponse>builder()
                        .message("Flight Update Successfully")
                        .status(202)
                        .data(flightService.updateFlight(id, request))
                        .build()
        );
    }
    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteFlight(@Valid @PathVariable Long id){
        flightService.deleteFlight(id);
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .message("Flight Deleted Successfully")
                        .status(204)
                        .data("Deleted ID:" + id)
                        .build()
        );
    }


}
