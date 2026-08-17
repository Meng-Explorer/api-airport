package TicketBooking.com.service.implement;

import TicketBooking.com.dto.request.GateRequest;
import TicketBooking.com.dto.response.GateResponse;
import TicketBooking.com.exception.NotFoundException;
import TicketBooking.com.models.Gate;
import TicketBooking.com.models.Terminal;
import TicketBooking.com.repository.GateRepository;
import TicketBooking.com.repository.TerminalRepository;
import TicketBooking.com.service.GateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GateServiceImp implements GateService {

    private final GateRepository gateRepository;
    private final TerminalRepository terminalRepository;
    public GateServiceImp(GateRepository gateRepository, TerminalRepository terminalRepository) {
        this.gateRepository = gateRepository;
        this.terminalRepository = terminalRepository;
    }

    @Override
    public GateResponse createGate(GateRequest request){
        Terminal terminal = terminalRepository.findById(request.getTerminalId())
                .orElseThrow(()->new NotFoundException("Terminal not found"));
        Gate gate = Gate.builder()
                .gateNumber(request.getGateNumber())
                .gateStatus(request.getGateStatus())
                .terminal(terminal)
                .build();
        Gate savedGate = gateRepository.save(gate);
        return mapToResponse(savedGate);
    }
    @Override
    public List<GateResponse> getGates(){
        return gateRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    @Override
    public GateResponse updateGate(Long id,GateRequest request){
        Gate existsGate = gateRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Gate not found"));
        existsGate.setTerminal(terminalRepository.findById(request.getTerminalId())
                .orElseThrow(()-> new NotFoundException("Terminal not found")));
        existsGate.setGateStatus(request.getGateStatus());
        existsGate.setGateNumber(request.getGateNumber());
        return mapToResponse(gateRepository.save(existsGate));

    }
    @Override
    public void deleteGate(Long id){
        if(!gateRepository.existsById(id)){
            throw new NotFoundException("Gate not Found with ID:" + id);
        }
        gateRepository.deleteById(id);
    }
    @Override
    public GateResponse getGateById(Long id){
        Gate gate = gateRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Gate not found"));
        return mapToResponse(gate);
    }


    private GateResponse mapToResponse(Gate gate){
        return GateResponse.builder()
                .gateId(gate.getId())
                .gateNumber(gate.getGateNumber())
                .gateStatus(gate.getGateStatus())
                .terminalName(gate.getTerminal().getTerminalName())
                .build();
    }

}
