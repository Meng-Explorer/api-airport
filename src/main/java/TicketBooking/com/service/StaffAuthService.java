package TicketBooking.com.service;

import TicketBooking.com.dto.request.StaffLoginRequest;
import TicketBooking.com.dto.request.StaffRegisterRequest;
import TicketBooking.com.dto.response.StaffLoginResponse;
import TicketBooking.com.dto.response.StaffRegisterResponse;
import org.springframework.stereotype.Service;


public interface StaffAuthService {
    StaffRegisterResponse register(StaffRegisterRequest request);
    StaffLoginResponse login(StaffLoginRequest request);
}
