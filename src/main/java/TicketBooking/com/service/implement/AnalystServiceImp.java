package TicketBooking.com.service.implement;

import TicketBooking.com.dto.response.AnalystResponse;
import TicketBooking.com.repository.*;
import TicketBooking.com.service.AnalystService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AnalystServiceImp implements AnalystService {
    private final FlightRepository  flightRepository;
    private final AirlineRepository airlineRepository;
    private final AirportRepository airportRepository;
    private final StaffRepository staffRepository;
    private final FlightAssignmentRepository flightAssignmentRepository;
    private final LogAndAlertRepository logAndAlertRepository;

    @Override
    public AnalystResponse getDashboardSummary() {
        List<Object[]> statusCounts = flightRepository.countFlightByStatus();
        // For React,Vue,Angular need JSON Object to easy for Graph or pie chart
        Map<String,Long> flightByStatusMap = new HashMap<>();
        for (Object[] row : statusCounts){
            String status = row[0] != null ? row[0].toString() : "UNKNOWN";
            Long count = (Long) row[1];
            flightByStatusMap.put(status,count);
        }
        return AnalystResponse.builder()
                .totalFlight(flightRepository.count())
                .totalAirline(airlineRepository.count())
                .totalAirport(airportRepository.count())
                .totalStaff(staffRepository.count())
                .totalAlertLogs(logAndAlertRepository.count())
                .totalActiveAssignment(flightAssignmentRepository.countActiveAssignment())
                .flightByStatus(flightByStatusMap)
                .build();
        }
}
