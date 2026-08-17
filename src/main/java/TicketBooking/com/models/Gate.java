package TicketBooking.com.models;

import TicketBooking.com.enums.GateStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// Door Open or Close
@Table(name = "Gate")
public class Gate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gateId")
    private Long id;

    private String gateNumber;
    @Enumerated(EnumType.STRING)
    private GateStatus gateStatus;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terminal_id", nullable = false)
    private Terminal terminal;
}
