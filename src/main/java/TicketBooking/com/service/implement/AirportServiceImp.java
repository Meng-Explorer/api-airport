package TicketBooking.com.service.implement;

import TicketBooking.com.dto.request.AirportRequest;
import TicketBooking.com.dto.response.AirportResponse;
import TicketBooking.com.exception.NotFoundException;
import TicketBooking.com.models.Airport;
import TicketBooking.com.repository.AirportRepository;
import TicketBooking.com.service.AirlineService;
import TicketBooking.com.service.AirportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirportServiceImp implements AirportService {

    private AirportRepository airportRepository;
    public AirportServiceImp(AirportRepository airportRepository){
        this.airportRepository = airportRepository;
    }
    @Override
    public AirportResponse createAirport(AirportRequest request){
        Airport airport = Airport.builder()
                .airportName(request.getAirportName())
                .iataCode(request.getIataCode())
                .build();
        Airport savedAirport = airportRepository.save(airport);
        return mapToResponse(savedAirport);
    }
    @Override
    public List<AirportResponse> getAirports(){
        return airportRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    @Override
    public AirportResponse getAirportById(Long id){
        Airport airport = airportRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Airport not found"));
        return mapToResponse(airport);
    }
    @Override
    public AirportResponse updateAirport(Long id,AirportRequest request){
        Airport existsAirport = airportRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Airport not found"));
        existsAirport.setAirportName(request.getAirportName());
        existsAirport.setIataCode(request.getIataCode());
        return  mapToResponse(airportRepository.save(existsAirport));
    }
    @Override
    public void deleteAirport(Long id){
        if(!airportRepository.existsById(id)){
            throw new NotFoundException("Shift not Found with ID:" + id);
        }
        airportRepository.deleteById(id);
    }
    private AirportResponse mapToResponse(Airport airport){
        return AirportResponse.builder()
                .airportId(airport.getId())
                .airportName(airport.getAirportName())
                .iataCode(airport.getIataCode())
                .build();
    }
}
