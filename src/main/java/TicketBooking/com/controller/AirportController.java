package TicketBooking.com.controller;

import TicketBooking.com.dto.request.AirportRequest;
import TicketBooking.com.dto.response.AirportResponse;
import TicketBooking.com.dto.response.ApiResponse;
import TicketBooking.com.service.AirportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff/api/airports")
public class AirportController {
    private final AirportService airportService;
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }
    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<AirportResponse>> createAirport(@RequestBody AirportRequest request){
        return ResponseEntity.ok(
          ApiResponse.<AirportResponse>builder()
                  .message("Created Success")
                  .status(201)
                  .data(airportService.createAirport(request))
                  .build()
        );
    }
    // GET ALL
    @GetMapping
    public ResponseEntity<ApiResponse<List<AirportResponse>>> getAirports(){
        return ResponseEntity.ok(
                ApiResponse.<List<AirportResponse>>builder()
                        .message("Find All")
                        .status(200)
                        .data(airportService.getAirports())
                        .build()
        );

    }
    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AirportResponse>> getAirportById(@PathVariable Long id){
        return ResponseEntity.ok(
          ApiResponse.<AirportResponse>builder()
                  .message("Find Success")
                  .status(200)
                  .data(airportService.getAirportById(id))
                  .build()
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AirportResponse>> updateAirport(@PathVariable Long id ,@RequestBody AirportRequest request){
        return ResponseEntity.ok(
                ApiResponse.<AirportResponse>builder()
                        .message("updated success")
                        .status(202)
                        .data(airportService.updateAirport(id,request))
                        .build()
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteAirport(@PathVariable Long id){
        airportService.deleteAirport(id);
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .message("Delete Success")
                        .status(204)
                        .data("Delete ID:" + id)
                        .build()
        );
    }

}
