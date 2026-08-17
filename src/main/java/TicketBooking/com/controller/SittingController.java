package TicketBooking.com.controller;

import TicketBooking.com.dto.request.ChangePasswordRequest;
import TicketBooking.com.dto.request.ProfileUpdateRequest;
import TicketBooking.com.dto.response.ApiResponse;
import TicketBooking.com.dto.response.StaffProfileResponse;
import TicketBooking.com.service.SittingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/staff/api/settings")
public class SittingController {

    private final SittingService sittingService;
    public SittingController(SittingService sittingService) {
        this.sittingService = sittingService;
    }

    @GetMapping("/profiles/{id}")
    public ResponseEntity<ApiResponse<StaffProfileResponse>> getProfile(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.<StaffProfileResponse>builder()
                        .message("Profile successfully retrieved")
                        .status(200)
                        .data(sittingService.getProfiles(id))
                        .build()
        );
    }
    @PutMapping("/profiles/{id}")
    public ResponseEntity<ApiResponse<StaffProfileResponse>> updateProfile(@PathVariable Long id, @Valid @ModelAttribute ProfileUpdateRequest request){
        return ResponseEntity.ok(
                ApiResponse.<StaffProfileResponse>builder()
                        .message("Profiles update Successfully")
                        .status(202)
                        .data(sittingService.updateProfile(id, request))
                        .build()
        );

    }
    @PutMapping("/change/{id}")
    public ResponseEntity<ApiResponse<String>> changePassword(@PathVariable Long id, @Valid @RequestBody ChangePasswordRequest request){
        sittingService.changePassword(id, request);
        return ResponseEntity.ok(
                   ApiResponse.<String>builder()
                           .message("Password Change Success")
                           .status(202)
                           .data("Password :" + id + request)
                           .build()
        );
    }


}
