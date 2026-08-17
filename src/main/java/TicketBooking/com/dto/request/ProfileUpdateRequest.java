package TicketBooking.com.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileUpdateRequest {
    private String firstName;
    private String lastName;
    private String email;
    private MultipartFile profile;
}
