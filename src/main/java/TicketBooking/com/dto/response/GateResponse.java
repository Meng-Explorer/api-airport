package TicketBooking.com.dto.response;

import TicketBooking.com.enums.GateStatus;
import TicketBooking.com.models.Terminal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GateResponse {
    private Long gateId;
    private String terminalName; // show name cause easy to watching
    private String gateNumber;
    private GateStatus gateStatus;
}
