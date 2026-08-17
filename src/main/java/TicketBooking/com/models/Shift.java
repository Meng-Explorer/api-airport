package TicketBooking.com.models;

import TicketBooking.com.enums.ShiftName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Shift")
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="shift_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ShiftName shiftName;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    // For Set Time
    @PrePersist
    protected void onCreate() {
        if (this.startTime == null) {
            this.startTime = LocalDateTime.now();
        }
        if (this.endTime == null) {
            this.endTime = LocalDateTime.now();
        }
    }
}
