package TicketBooking.com.service.implement;

import TicketBooking.com.dto.request.TerminalRequest;
import TicketBooking.com.dto.response.TerminalResponse;
import TicketBooking.com.exception.NotFoundException;
import TicketBooking.com.models.Terminal;
import TicketBooking.com.repository.TerminalRepository;
import TicketBooking.com.service.TerminalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TerminalServiceImp implements TerminalService {

    private final TerminalRepository terminalRepository;
    public TerminalServiceImp(TerminalRepository terminalRepository) {
        this.terminalRepository = terminalRepository;
    }
    @Override
    public TerminalResponse createTerminal(TerminalRequest request){
        Terminal terminal = Terminal.builder()
                .terminalName(request.getTerminalName())
                .build();
        return mapToResponse(terminalRepository.save(terminal));
    }
    @Override
    public List<TerminalResponse> getAllTerminalses(){
         return terminalRepository.findAll().stream()
                 .map(this::mapToResponse)
                 .collect(Collectors.toList());
    }
    @Override
    public TerminalResponse updateTerminal(Long id,TerminalRequest request){
        Terminal existsTerminal = terminalRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Terminal Not Found"));
        existsTerminal.setTerminalName(request.getTerminalName());
        return mapToResponse(terminalRepository.save(existsTerminal));
    }
    @Override
    public void deleteTerminal(Long id){
        if(!terminalRepository.existsById(id)){
            throw  new NotFoundException("Terminal Not Found");
        }
        terminalRepository.deleteById(id);

    }

    private TerminalResponse mapToResponse(Terminal terminal){
        return TerminalResponse.builder()
                .terminalId(terminal.getTerminalId())
                .terminalName(terminal.getTerminalName())
                .build();
    }

}
