package TicketBooking.com.repository;

import TicketBooking.com.enums.LogLevel;
import TicketBooking.com.models.OpsLogAndAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogAndAlertRepository extends JpaRepository<OpsLogAndAlert,Long> {
    // This function to take data for warning (alert) follow level (Enums)
    List<OpsLogAndAlert> findByLogLevel(LogLevel logLevel);
}
