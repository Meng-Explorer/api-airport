package TicketBooking.com.service.implement;

import TicketBooking.com.dto.request.FlightRequest;
import TicketBooking.com.dto.response.FlightResponse;
import TicketBooking.com.enums.FlightStatus;
import TicketBooking.com.exception.NotFoundException;
import TicketBooking.com.models.Airport;
import TicketBooking.com.models.Flight;
import TicketBooking.com.models.Gate;
import TicketBooking.com.repository.AirlineRepository;
import TicketBooking.com.repository.AirportRepository;
import TicketBooking.com.repository.FlightRepository;
import TicketBooking.com.repository.GateRepository;
import TicketBooking.com.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImp implements FlightService {
    private final FlightRepository flightRepository;
    private final GateRepository gateRepository;
    private final AirlineRepository airlineRepository;
    private final AirportRepository airportRepository;

    public FlightServiceImp(FlightRepository flightRepository , GateRepository gateRepository, AirlineRepository airlineRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.gateRepository = gateRepository;
        this.airlineRepository = airlineRepository;
        this.airportRepository = airportRepository;
    }
    @Override
    public FlightResponse createFlight( FlightRequest request){
        // Find Gate if provided
        Gate gate = null;
        if(request.getGateId()!=null){
            gate = gateRepository.findById(request.getGateId())
                    .orElseThrow(()->new NotFoundException("Gate ID not found"));
        }
        // Find Origin Airport
        if(request.getOriginAirportId() == null || request.getAirlineId() ==null || request.getDepartureAirportId()==null){
            throw new NotFoundException("Airline ID, Origin Airport ID, and Destination Airport ID must not be null!");
        }
        // Find Origin Airport
        Airport origin = airportRepository.findById(request.getOriginAirportId())
                .orElseThrow(() -> new NotFoundException("Origin Airport Not Found"));

        Airport destination = airportRepository.findById(request.getDepartureAirportId())
                .orElseThrow(()-> new NotFoundException("Departure Airport Not Found"));

        FlightStatus flightStatus = (request.getStatus() != null) ?
        request.getStatus() : FlightStatus.ON_TIME;

        // Build Flight entity
        Flight flight = Flight.builder()
                .aircraftType(request.getAircraftType())
                .tailNumber(request.getTailNumber())
                .scheduledDeparture(request.getScheduledDeparture())
                .status(flightStatus)
                .airline(airlineRepository.findById(request.getAirlineId())
                        .orElseThrow(()-> new NotFoundException("Airline not Found")))
                .gate(gate)
                .actualDeparture(request.getActualDeparture())
                .eatEtd(request.getEtaEtd())
                .originAirport(origin)
                .destinationAirport(destination)
                .build();
        Flight savedFlight = flightRepository.save(flight);
        return mapToResponse(savedFlight);
    }
    @Override
    public List<FlightResponse> getAllFlights(){
        return flightRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    @Override
    public FlightResponse getFlightById(Long id){
        Flight flight = flightRepository.findById(id).orElseThrow(()-> new NotFoundException("Flight not Found with ID:" + id));
        return mapToResponse(flight);
    }
    @Override
    public FlightResponse updateFlight(Long id,  FlightRequest request){
        if(id == null){
            throw new NotFoundException("Flight ID Not Found");
        }
        if (request.getAirlineId() == null || request.getOriginAirportId()==null || request.getDepartureAirportId() == null) {
            throw new NotFoundException("Airline ID, Origin Airport ID, and Departure Airport ID must not be null! Please check your Postman JSON keys.");
        }
        Flight existingFlight = flightRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Flight not Found with ID:" +id));
        //Update Fields
        existingFlight.setAirline(airlineRepository.findById(request.getAirlineId())
                .orElseThrow(()-> new NotFoundException("Airline not Found")));
        existingFlight.setOriginAirport(airportRepository.findById(request.getOriginAirportId())
                .orElseThrow(()-> new NotFoundException("Origin Airport Not Found")));
        existingFlight.setDestinationAirport(airportRepository.findById(request.getDepartureAirportId())
                .orElseThrow(()-> new NotFoundException("Departure Airport Not Found")));

        // Update field
        existingFlight.setAircraftType(request.getAircraftType());
        existingFlight.setTailNumber(request.getTailNumber());
        existingFlight.setScheduledDeparture(request.getScheduledDeparture());
        existingFlight.setActualDeparture(request.getActualDeparture());
        existingFlight.setEatEtd(request.getEtaEtd());
        if(request.getStatus() != null){
            existingFlight.setStatus(request.getStatus());
        }
        // Update gate
        if(request.getGateId() != null){
            existingFlight.setGate(gateRepository.findById(request.getGateId())
                    .orElseThrow(()-> new NotFoundException("Gate not Found")));
        }else{
            existingFlight.setGate(null);
        }
        return mapToResponse(flightRepository.save(existingFlight));
    }
    @Override
    public void deleteFlight(Long id){
        if(!flightRepository.existsById(id)){
            throw new NotFoundException("Flight not Found with ID:" + id);
        }
        flightRepository.deleteById(id);
    }

    private FlightResponse mapToResponse(Flight flight){
        return FlightResponse.builder()
                .flightId(flight.getFlightId())
                .aircraftType(flight.getAircraftType())
                .tailNumber(flight.getTailNumber())
                .scheduledDeparture(flight.getScheduledDeparture())
                .status(flight.getStatus())
                // Using Null safe check for communicate (Relation)
                .originAirportCode(flight.getOriginAirport() != null ? flight.getOriginAirport().getIataCode() : null)
                .destinationAirportCode(flight.getDestinationAirport() != null ? flight.getDestinationAirport().getIataCode() : null)
                .gateNumber(flight.getGate() != null ? flight.getGate().getGateNumber() : null)
                .airlineName(flight.getAirline() != null ? flight.getAirline().getAirlineName() : "Unknow")
                .actualDeparture(flight.getActualDeparture())
                .etaEtd(flight.getEatEtd())
                .build();
    }



}
