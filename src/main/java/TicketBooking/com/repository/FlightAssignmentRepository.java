package TicketBooking.com.repository;

import TicketBooking.com.models.FlightAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightAssignmentRepository extends JpaRepository<FlightAssignment,Long> {
    List<FlightAssignment> findByFlight_FlightId(Long flightId);
    List<FlightAssignment> findByStaff_Id(Long staffId);
    List<FlightAssignment> findByShift_Id(Long shiftId);

    @Query("SELECT COUNT(fa) FROM FlightAssignment fa WHERE fa.assignmentStatus = 'ACTIVE'")
    long countActiveAssignment();
}
