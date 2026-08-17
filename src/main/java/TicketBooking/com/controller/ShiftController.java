package TicketBooking.com.controller;

import TicketBooking.com.dto.request.ShiftRequest;
import TicketBooking.com.dto.response.ApiResponse;
import TicketBooking.com.dto.response.ShiftResponse;
import TicketBooking.com.service.ShiftService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff/api/shifts")
public class ShiftController {

    private final ShiftService shiftService;
    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<ShiftResponse>> createShift(@Valid @RequestBody ShiftRequest request){
        return ResponseEntity.ok(
                ApiResponse.<ShiftResponse>builder()
                        .message("Shift created Successfully")
                        .status(201)
                        .data(shiftService.createShift(request))
                        .build()
        );
    }
    //GET ALL
    @GetMapping
    public ResponseEntity<ApiResponse<List<ShiftResponse>>> getAllShifts(){
        return ResponseEntity.ok(
                ApiResponse.<List<ShiftResponse>>builder()
                        .message("Get All Shift")
                        .status(200)
                        .data(shiftService.getAllShifts())
                        .build()

        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ShiftResponse>> updateShift(@Valid @PathVariable Long id, @Valid @RequestBody ShiftRequest request ){
        return ResponseEntity.ok(
            ApiResponse.<ShiftResponse>builder()
                    .message("Updated Shift Successfully")
                    .status(202)
                    .data(shiftService.updateShift(id,request))
                    .build()
        );
    }
    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteShift(@Valid @PathVariable Long id){
        shiftService.deleteShift(id);
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .message("Delete Shift Successfully")
                        .status(204)
                        .data("Delete ID:"+id)
                        .build()

        );
    }


}
