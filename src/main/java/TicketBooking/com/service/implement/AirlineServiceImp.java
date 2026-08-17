package TicketBooking.com.service.implement;

import TicketBooking.com.dto.request.AirlineRequest;
import TicketBooking.com.dto.response.AirlineResponse;
import TicketBooking.com.exception.NotFoundException;
import TicketBooking.com.models.Airline;
import TicketBooking.com.repository.AirlineRepository;
import TicketBooking.com.service.AirlineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirlineServiceImp implements AirlineService {

    private final AirlineRepository airlineRepository;
    public AirlineServiceImp(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }
    @Override
    public AirlineResponse createAirline(AirlineRequest request){
        Airline airline = Airline.builder()
                .airlineName(request.getAirlineName())
                .airlineCode(request.getAirlineCode())
                .build();
        Airline savedAirline = airlineRepository.save(airline);
        return mapToResponse(savedAirline);
    }
    @Override
    public List<AirlineResponse> getAllAirlines(){
        return airlineRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    @Override
    public AirlineResponse getAirlineById(Long id){
        Airline airline = airlineRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Airline not found"));
        return mapToResponse(airline);
    }
    @Override
    public AirlineResponse updateAirline(Long id,AirlineRequest request){
        Airline existsAirline = airlineRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Airline not found"));
        existsAirline.setAirlineName(request.getAirlineName());
        existsAirline.setAirlineCode(request.getAirlineCode());
        return mapToResponse(airlineRepository.save(existsAirline));
    }
    @Override
    public void deleteAirline(Long id){
        if(!airlineRepository.existsById(id)){
            throw new NotFoundException("Shift not Found with ID:" + id);
        }
        airlineRepository.deleteById(id);
    }

    private AirlineResponse mapToResponse(Airline airline){
        return AirlineResponse.builder()
                .AirlineId(airline.getId())
                .airlineName(airline.getAirlineName())
                .airlineCode(airline.getAirlineCode())
                .build();
    }

}
