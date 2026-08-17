package TicketBooking.com.dto.response;

import TicketBooking.com.enums.ShiftName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShiftResponse {
    private Long shiftId;
    private ShiftName shiftName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
