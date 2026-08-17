package TicketBooking.com.dto.request;

import TicketBooking.com.enums.GateStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GateRequest {
    private Long terminalId; // config with Terminal
    private String gateNumber;
    private GateStatus gateStatus; // using Enum
}
