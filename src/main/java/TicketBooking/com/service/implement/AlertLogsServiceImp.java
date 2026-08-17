package TicketBooking.com.service.implement;

import TicketBooking.com.dto.request.LogAndAlertRequest;
import TicketBooking.com.dto.response.LogAndAlertResponse;
import TicketBooking.com.enums.LogLevel;
import TicketBooking.com.exception.NotFoundException;
import TicketBooking.com.models.Flight;
import TicketBooking.com.models.OpsLogAndAlert;
import TicketBooking.com.repository.FlightRepository;
import TicketBooking.com.repository.LogAndAlertRepository;
import TicketBooking.com.service.AlertLogsService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertLogsServiceImp implements AlertLogsService {
    private final LogAndAlertRepository logAndAlertRepository;
    private final FlightRepository flightRepository;
    public AlertLogsServiceImp(LogAndAlertRepository logAndAlertRepository, FlightRepository flightRepository) {
        this.logAndAlertRepository = logAndAlertRepository;
        this.flightRepository = flightRepository;
    }
    @Override
    public LogAndAlertResponse addLogAndAlert(LogAndAlertRequest request){
        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(()->new NotFoundException("Flight not found"));
        OpsLogAndAlert logs = OpsLogAndAlert.builder()
                .logType(request.getLogType())
                .logLevel(request.getLogLevel())
                .message(request.getMessage())
                .flight(flight)
                .build();
        OpsLogAndAlert savedLogs = logAndAlertRepository.save(logs);
        return mapToResponse(savedLogs);
    }
    @Override
    public List<LogAndAlertResponse> getAllLogs(){
        return logAndAlertRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    @Override
    public LogAndAlertResponse getLogById(Long id) {
        OpsLogAndAlert logs = logAndAlertRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Log not found"));
        return mapToResponse(logs);
    }
    @Override
    public List<LogAndAlertResponse> getLogByLevel(LogLevel level){
        return logAndAlertRepository.findByLogLevel(level).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    @Override
    public void deleteLog(Long id){
        if(!logAndAlertRepository.existsById(id)){
            throw new NotFoundException("Log not found");
        }
        logAndAlertRepository.deleteById(id);
    }

    private LogAndAlertResponse mapToResponse(OpsLogAndAlert logs){
        return LogAndAlertResponse.builder()
                .logId(logs.getId())
                .logType(logs.getLogType())
                .logLevel(logs.getLogLevel())
                .timeStamp(logs.getTimeStamp())
                .message(logs.getMessage())
                .flightId(logs.getFlight() != null ? logs.getFlight().getFlightId() : null)
                .build();
    }





}
