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
public class StaffLoginResponse {
    private String message; // show message represent u login successfully
    private String token;
    private Long id;
    private String email;
    private String profile;
    private String staffCode;
    private StaffRole primaryRole;
}
