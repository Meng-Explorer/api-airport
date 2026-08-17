package TicketBooking.com.service.implement;

import TicketBooking.com.dto.response.FlightReportResponse;
import TicketBooking.com.dto.response.StaffReportResponse;
import TicketBooking.com.models.Flight;
import TicketBooking.com.models.FlightAssignment;
import TicketBooking.com.repository.FlightAssignmentRepository;
import TicketBooking.com.repository.FlightRepository;
import TicketBooking.com.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImp  implements ReportService {
    private final FlightRepository flightRepository;
    private final FlightAssignmentRepository assignmentRepository;

    @Override
    public List<FlightReportResponse> getFlightOperationReport(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date!");
        }
        List<Flight> flights = flightRepository.findFlightForReport(startDate, endDate);
        return flights.stream().map(flight -> FlightReportResponse.builder()
                        .flightId(flight.getFlightId())
                        .airlineName(flight.getAirline() != null ? flight.getAirline().getAirlineName() : "UNKNOWN")
                        .tailNumber(flight.getTailNumber())
                        .aircraftType(flight.getAircraftType())
                        .originCode(flight.getOriginAirport() != null ? flight.getOriginAirport().getIataCode() : "N/A")
                        .destinationCode(flight.getDestinationAirport() != null ? flight.getDestinationAirport().getIataCode() : "N/A")
                        .gateNumber(flight.getGate() != null ? flight.getGate().getGateNumber() : "Unassigned" )
                        .scheduledDeparture(flight.getScheduledDeparture())
                        .actualDeparture(flight.getActualDeparture())
                        .status(flight.getStatus())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public List<StaffReportResponse> getStaffAssignmentReports(){
        List<FlightAssignment> assignments = assignmentRepository.findAll();
        return assignments.stream().map(assign -> StaffReportResponse.builder()
                .assignmentId(assign.getId())
                .staffCode(assign.getStaff() != null ? assign.getStaff().getStaffCode() : "N/A")
                .staffName(assign.getStaff() != null ? assign.getStaff().getFirstName() + " " + assign.getStaff().getLastName() : "N/A")
                .primaryRole(assign.getStaff() != null ? assign.getStaff().getPrimaryRole() : null)
                .assignedRole(assign.getAssignedRole())
                .shiftName(assign.getShift() != null && assign.getShift().getShiftName() != null ? assign.getShift().getShiftName().name() : "N/A")
                .tailNumber(assign.getFlight() != null ? assign.getFlight().getTailNumber() : null)
                .assignmentStatus(assign.getAssignmentStatus())
                .build()).collect(Collectors.toList());
    }

}
