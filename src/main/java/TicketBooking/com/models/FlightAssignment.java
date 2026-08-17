package TicketBooking.com.models;

import TicketBooking.com.enums.AssignedRole;
import TicketBooking.com.enums.AssignmentStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// Staff Assignment
@Table(name = "Flight_Staff_Assignments")
public class FlightAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="assignment_Id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private AssignedRole assignedRole;

    @Enumerated(EnumType.STRING)
    private AssignmentStatus assignmentStatus;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shift_id", nullable = false)
    private Shift shift;
}
