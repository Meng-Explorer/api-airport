package TicketBooking.com.service.implement;

import TicketBooking.com.dto.request.FlightAssignmentRequest;
import TicketBooking.com.dto.response.FlightAssignmentResponse;
import TicketBooking.com.exception.NotFoundException;
import TicketBooking.com.models.Flight;
import TicketBooking.com.models.FlightAssignment;
import TicketBooking.com.models.Shift;
import TicketBooking.com.models.Staff;
import TicketBooking.com.repository.FlightAssignmentRepository;
import TicketBooking.com.repository.FlightRepository;
import TicketBooking.com.repository.ShiftRepository;
import TicketBooking.com.repository.StaffRepository;
import TicketBooking.com.service.FlightAssignService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightAssignServiceImp implements FlightAssignService {
    private final FlightAssignmentRepository flightAssignmentRepository;
    private final FlightRepository flightRepository;
    private final StaffRepository staffRepository;
    private final ShiftRepository shiftRepository;

    @Override
    public FlightAssignmentResponse createAssignment(FlightAssignmentRequest request){
        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(()->new NotFoundException("Flight not found"));
        Staff staff = staffRepository.findById(request.getStaffId())
                .orElseThrow(()->new NotFoundException("Staff not found"));
        Shift shift = shiftRepository.findById(request.getShiftId())
                .orElseThrow(()->new NotFoundException("Shift not found"));
        FlightAssignment savedFlight = FlightAssignment.builder()
                .flight(flight)
                .staff(staff)
                .shift(shift)
                .assignedRole(request.getAssignedRole())
                .assignmentStatus(request.getAssignmentStatus())
                .build();
        return mapToResponse(flightAssignmentRepository.save(savedFlight));
    }
    @Override
    public List<FlightAssignmentResponse> getAllAssignments(){
        return flightAssignmentRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FlightAssignmentResponse getAssignedById(Long id){
        FlightAssignment flightAssignment = flightAssignmentRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Assignment not found"));
        return mapToResponse(flightAssignment);
    }

    @Override
    public List<FlightAssignmentResponse> getAssignedFlightById(Long flightId){
        return flightAssignmentRepository.findByFlight_FlightId(flightId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<FlightAssignmentResponse> getAssignedByStaffId(Long staffId){
        return flightAssignmentRepository.findByShift_Id(staffId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FlightAssignmentResponse updateAssignment(Long id, FlightAssignmentRequest request){
        FlightAssignment existing = flightAssignmentRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Assignment not found"));
        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(()->new NotFoundException("Flight not found"));
        Staff staff = staffRepository.findById(request.getStaffId())
                .orElseThrow(()->new NotFoundException("Staff not found"));
        Shift shift = shiftRepository.findById(request.getShiftId())
                .orElseThrow(()->new NotFoundException("Shift not found"));
        existing.setAssignedRole(request.getAssignedRole());
        existing.setAssignmentStatus(request.getAssignmentStatus());
        existing.setFlight(flight);
        existing.setStaff(staff);
        existing.setShift(shift);
        return mapToResponse(flightAssignmentRepository.save(existing));
    }
    @Override
    public void deleteAssignment(Long id){
        if(!flightAssignmentRepository.existsById(id)){
            throw new NotFoundException("Assignment not found");
        }
        flightAssignmentRepository.deleteById(id);
    }
    private FlightAssignmentResponse mapToResponse(FlightAssignment flightAssignment){
        Long flightId = null;
        String tailNumber ="N/A";
        if(flightAssignment.getFlight() != null){
            flightId = flightAssignment.getFlight().getFlightId();
            tailNumber = flightAssignment.getFlight().getTailNumber();
        }

        String staffName ="N/A";
        String staffCode ="N/A";
        if(flightAssignment.getStaff() != null){
            staffName = flightAssignment.getStaff().getFirstName() + " " + flightAssignment.getStaff().getLastName();
            staffCode = flightAssignment.getStaff().getStaffCode();
        }
        String shiftName ="N/A";
        if(flightAssignment.getShift() != null){
            shiftName = flightAssignment.getShift().getShiftName() != null ?
                    flightAssignment.getShift().getShiftName().name() : "N/A";

        }
        return FlightAssignmentResponse.builder()
                .assignedRole(flightAssignment.getAssignedRole())
                .assignmentStatus(flightAssignment.getAssignmentStatus())

                .staffName(staffName)
                .staffCode(staffCode)

                .shiftName(shiftName)
                .flightId(flightId)
                .tailNumber(tailNumber)
                .build();
    }

}
