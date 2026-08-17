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
public class StaffRegisterResponse {
    // Java Web Token
    private String token;
    // new Access Token
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String profile;
    private StaffRole primaryRole;
}
