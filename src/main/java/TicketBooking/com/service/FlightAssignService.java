package TicketBooking.com.service;

import TicketBooking.com.dto.request.FlightAssignmentRequest;
import TicketBooking.com.dto.response.FlightAssignmentResponse;

import java.util.List;

public interface FlightAssignService {
    FlightAssignmentResponse createAssignment(FlightAssignmentRequest request);
    List<FlightAssignmentResponse> getAllAssignments();
    FlightAssignmentResponse getAssignedById(Long id);
    List<FlightAssignmentResponse> getAssignedFlightById(Long flightId);
    List<FlightAssignmentResponse> getAssignedByStaffId(Long staffId);
    FlightAssignmentResponse updateAssignment(Long id,FlightAssignmentRequest request);
    void deleteAssignment(Long id);
}
