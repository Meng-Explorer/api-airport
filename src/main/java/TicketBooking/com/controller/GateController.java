package TicketBooking.com.controller;

import TicketBooking.com.dto.request.GateRequest;
import TicketBooking.com.dto.response.ApiResponse;
import TicketBooking.com.dto.response.GateResponse;
import TicketBooking.com.service.GateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff/api/gates")
public class GateController {

    private final GateService gateService;
    public GateController(GateService gateService) {
        this.gateService = gateService;
    }
    //CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<GateResponse>> createGate(@RequestBody GateRequest request) {
        return ResponseEntity.ok(
                ApiResponse.<GateResponse>builder()
                        .message("Gate created")
                        .status(201)
                        .data(gateService.createGate(request))
                        .build()
        );
    }
    //GET ALL
    @GetMapping
    public ResponseEntity<ApiResponse<List<GateResponse>>> getAllGates(){
        return ResponseEntity.ok(
                ApiResponse.<List<GateResponse>>builder()
                        .message("Find all gate")
                        .status(200)
                        .data(gateService.getGates())
                        .build()
        );
    }

    //GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GateResponse>> getGateById(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.<GateResponse>builder()
                        .message("Find By Id")
                        .status(200)
                        .data(gateService.getGateById(id))
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<GateResponse>> updateGate(@PathVariable Long id,@RequestBody GateRequest request){
        return ResponseEntity.ok(
                ApiResponse.<GateResponse>builder()
                        .message("updated success")
                        .status(202)
                        .data(gateService.updateGate(id,request))
                        .build()

        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteGate(@PathVariable Long id){
        gateService.deleteGate(id);
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .message("Deleted Success")
                        .status(204)
                        .data("Deleted ID:"+id)
                        .build()
        );

    }
}
