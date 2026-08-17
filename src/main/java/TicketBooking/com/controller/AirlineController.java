package TicketBooking.com.controller;

import TicketBooking.com.dto.request.AirlineRequest;
import TicketBooking.com.dto.response.AirlineResponse;
import TicketBooking.com.dto.response.ApiResponse;
import TicketBooking.com.service.AirlineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff/api/airlines")
public class AirlineController {

    private final AirlineService airlineService;
    public AirlineController(AirlineService airlineService){
        this.airlineService = airlineService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<AirlineResponse>> createAirline(@RequestBody AirlineRequest request){
        return ResponseEntity.ok(
                ApiResponse.<AirlineResponse>builder()
                        .message("Airline Created Successfully")
                        .status(201)
                        .data(airlineService.createAirline(request))
                        .build()
        );
    }
    //GET ALL
    @GetMapping
    public ResponseEntity<ApiResponse<List<AirlineResponse>>> getAllAirlines(){
        return ResponseEntity.ok(
                ApiResponse.<List<AirlineResponse>>builder()
                        .message("Get All Airline")
                        .status(200)
                        .data(airlineService.getAllAirlines())
                        .build()
        );
    }
    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AirlineResponse>> getAirline(@PathVariable Long id){
        return ResponseEntity.ok(
          ApiResponse.<AirlineResponse>builder()
                  .message("Find success")
                  .status(200)
                  .data(airlineService.getAirlineById(id))
                  .build()
        );
    }
    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AirlineResponse>> updateAirline(@PathVariable Long id,@RequestBody AirlineRequest request){
        return ResponseEntity.ok(
                ApiResponse.<AirlineResponse>builder()
                        .message("Updated Success")
                        .status(202)
                        .data(airlineService.updateAirline(id,request))
                        .build()
        );
    }
    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteAirline(@PathVariable Long id){
        airlineService.deleteAirline(id);
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .message("Delete Success")
                        .status(204)
                        .data("Delete ID" + id)
                        .build()
        );
    }



}
