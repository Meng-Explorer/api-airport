package TicketBooking.com.controller;

import TicketBooking.com.dto.request.TerminalRequest;
import TicketBooking.com.dto.response.ApiResponse;
import TicketBooking.com.dto.response.TerminalResponse;
import TicketBooking.com.service.TerminalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff/api/terminals")
public class TerminalController {

    private final TerminalService terminalService;
    public TerminalController(TerminalService terminalService) {
        this.terminalService = terminalService;
    }
    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<TerminalResponse>> createTerminal(@Valid @RequestBody TerminalRequest request){
        return ResponseEntity.ok(
                ApiResponse.<TerminalResponse>builder()
                        .message("Created Terminals Successfully")
                        .status(201)
                        .data(terminalService.createTerminal(request))
                        .build()
        );
    }
    // GET DATA
    @GetMapping
    public ResponseEntity<ApiResponse<List<TerminalResponse>>> getAllTerminals(){
        return ResponseEntity.ok(
                ApiResponse.<List<TerminalResponse>>builder()
                        .message("Get Terminals Successfully")
                        .status(200)
                        .data(terminalService.getAllTerminalses())
                        .build()
        );
    }
    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TerminalResponse>> updateTerminal(@Valid @PathVariable Long id,@Valid @RequestBody TerminalRequest request){
        return ResponseEntity.ok(
                ApiResponse.<TerminalResponse>builder()
                        .message("Updated terminals successfully")
                        .status(200)
                        .data(terminalService.updateTerminal(id,request))
                        .build()
        );
    }
    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteTerminal(@Valid @PathVariable Long id){
        terminalService.deleteTerminal(id);
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .message("Terminal Deleted successfully")
                        .status(200)
                        .data("Delete ID:" + id)
                        .build()
        );
    }


}
