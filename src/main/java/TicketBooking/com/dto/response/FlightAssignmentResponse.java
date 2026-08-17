package TicketBooking.com.dto.response;

import TicketBooking.com.enums.AssignedRole;
import TicketBooking.com.enums.AssignmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightAssignmentResponse {


    // Show Information Easy Understand
    private Long flightId;
    private String tailNumber;
    private String staffName;
    private String staffCode;
    private String shiftName;

    // Using Enum
    private AssignedRole assignedRole;
    private AssignmentStatus assignmentStatus;
}
