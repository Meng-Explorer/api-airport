package TicketBooking.com.controller;

import TicketBooking.com.dto.request.StaffLoginRequest;
import TicketBooking.com.dto.request.StaffRegisterRequest;
import TicketBooking.com.dto.response.ApiResponse;
import TicketBooking.com.dto.response.StaffLoginResponse;
import TicketBooking.com.dto.response.StaffRegisterResponse;
import TicketBooking.com.service.implement.StaffAuthServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/staff/api/auth")
public class StaffController {
    private final StaffAuthServiceImp staffAuthServiceImp;

    @PostMapping(value = "/register",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<StaffRegisterResponse>> register(@Valid @ModelAttribute StaffRegisterRequest request) {
        return ResponseEntity.ok(
                ApiResponse.<StaffRegisterResponse>builder()
                        .message("Staff register successfully!")
                        .status(201)
                        .data(staffAuthServiceImp.register(request))
                        .build()

        );
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<StaffLoginResponse>> login(@Valid @RequestBody StaffLoginRequest request) {
        return ResponseEntity.ok(
                ApiResponse.<StaffLoginResponse>builder()
                        .message("Login Successfully")
                        .status(200)
                        .data(staffAuthServiceImp.login(request))
                        .build()
        );
    }
}
