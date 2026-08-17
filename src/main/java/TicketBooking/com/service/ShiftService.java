package TicketBooking.com.service;

import TicketBooking.com.dto.request.ShiftRequest;
import TicketBooking.com.dto.response.ShiftResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ShiftService {
    ShiftResponse createShift(ShiftRequest request);
    List<ShiftResponse> getAllShifts();
    ShiftResponse updateShift(Long id, ShiftRequest request);
    void deleteShift(Long id);

}
