package TicketBooking.com.repository;

import TicketBooking.com.enums.FlightStatus;
import TicketBooking.com.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {
    // This Function ta take air plan follow situation(Enums)
    List<Flight> findByStatus(FlightStatus status);
    @Query("SELECT f.status, COUNT(f) FROM Flight f GROUP BY f.status")
    List<Object[]> countFlightByStatus();
    @Query("SELECT f FROM Flight f WHERE f.scheduledDeparture BETWEEN :startDate AND :endDate ORDER BY f.scheduledDeparture ASC")
    List<Flight> findFlightForReport(@Param("startDate")LocalDateTime startDate, @Param("endDate")LocalDateTime endDate);
}
