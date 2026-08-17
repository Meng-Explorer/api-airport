package TicketBooking.com.dto.response;

import TicketBooking.com.enums.StaffRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffProfileResponse {
    private Long staffId;
    private String staffCode;
    private String firstName;
    private String lastName;
    private String email;
    private StaffRole primaryRole;
    private String profile;
}
