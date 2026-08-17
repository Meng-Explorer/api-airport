package TicketBooking.com.dto.request;

import TicketBooking.com.enums.ShiftName;
import TicketBooking.com.models.Shift;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShiftRequest {
    private ShiftName shiftName; // using Enum
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
