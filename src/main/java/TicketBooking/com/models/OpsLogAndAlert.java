package TicketBooking.com.models;

import TicketBooking.com.enums.LogLevel;
import TicketBooking.com.enums.LogType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// commit and warning
@Table(name = "Logs_And_Alerts")
public class OpsLogAndAlert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="logsAndAlert_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private LogType logType;

    @Enumerated(EnumType.STRING)
    private LogLevel logLevel;

    private LocalDateTime timeStamp;

    @Column(columnDefinition = "TEXT")
    private String message;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = true)
    private Flight flight;

    // For Set Time Auto
    @PrePersist
    protected void onCreate() {
        if (this.timeStamp == null) {
            this.timeStamp = LocalDateTime.now();
        }
    }
}
