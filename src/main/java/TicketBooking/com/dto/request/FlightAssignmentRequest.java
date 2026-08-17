package TicketBooking.com.dto.request;

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
public class FlightAssignmentRequest {
    private Long flightId;
    private Long StaffId;
    private Long shiftId;
    private String staffName;
    private AssignedRole assignedRole; // Using Enum
    private AssignmentStatus assignmentStatus;
}
