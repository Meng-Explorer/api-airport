package TicketBooking.com.models;

import TicketBooking.com.enums.FlightStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="flight_id")
    private Long flightId;

    private  String aircraftType;
    private  String tailNumber;

    private LocalDateTime scheduledDeparture;
    private LocalDateTime actualDeparture;
    private LocalDateTime eatEtd;

    @Enumerated(EnumType.STRING)
    private FlightStatus status;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airline_id", nullable = false)
    private Airline airline;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_airport_id", nullable = false)
    private Airport originAirport;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_airport_id", nullable = false)
    private Airport destinationAirport;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gate_id")
    private Gate gate;

    // For Set Time auto
    @PrePersist
    protected void onCreate() {
        if (this.scheduledDeparture == null ) {
            this.scheduledDeparture = LocalDateTime.now();
        }
        if (this.actualDeparture == null ) {
            this.actualDeparture = LocalDateTime.now();
        }
        if (this.eatEtd == null ) {
            this.eatEtd = LocalDateTime.now();
        }
    }

}
