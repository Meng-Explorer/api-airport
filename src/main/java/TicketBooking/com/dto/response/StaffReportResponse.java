package TicketBooking.com.dto.response;

import TicketBooking.com.enums.AssignedRole;
import TicketBooking.com.enums.AssignmentStatus;
import TicketBooking.com.enums.StaffRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffReportResponse {
    private Long assignmentId;
    private String staffCode;
    private String staffName;
    private StaffRole primaryRole;
    private AssignedRole assignedRole;
    private String shiftName;
    private String tailNumber;
    private AssignmentStatus assignmentStatus;
}
