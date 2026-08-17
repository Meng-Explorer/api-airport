package TicketBooking.com.service.implement;

import TicketBooking.com.dto.request.ShiftRequest;
import TicketBooking.com.dto.response.ShiftResponse;
import TicketBooking.com.exception.NotFoundException;
import TicketBooking.com.models.Shift;
import TicketBooking.com.repository.ShiftRepository;
import TicketBooking.com.service.ShiftService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShiftServiceImp implements ShiftService {

    private final ShiftRepository  shiftRepository;
    public ShiftServiceImp(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    @Override
    public ShiftResponse createShift(ShiftRequest request){
        Shift shift = Shift.builder()
                .shiftName(request.getShiftName())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime().plusHours(8))
                .build();
        Shift savedShift = shiftRepository.save(shift);
        return mapToResponse(savedShift);
    }

    @Override
    public List<ShiftResponse> getAllShifts(){
        return shiftRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ShiftResponse updateShift(Long id,ShiftRequest request){
        Shift exisitsShift = shiftRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Shift not Found with ID:" +id));
        exisitsShift.setShiftName(request.getShiftName());
        exisitsShift.setEndTime(request.getEndTime());
        exisitsShift.setStartTime(request.getStartTime());
        return  mapToResponse(shiftRepository.save(exisitsShift));
    }
    @Override
    public void deleteShift(Long id){
        if(!shiftRepository.existsById(id)){
            throw new NotFoundException("Shift not Found with ID:" + id);
        }
        shiftRepository.deleteById(id);
    }

    private ShiftResponse mapToResponse(Shift shift){
        return ShiftResponse.builder()
                .shiftId(shift.getId())
                .shiftName(shift.getShiftName())
                .startTime(shift.getStartTime())
                .endTime(shift.getEndTime())
                .build();
    }
}
