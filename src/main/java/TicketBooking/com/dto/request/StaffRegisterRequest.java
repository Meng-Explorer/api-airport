package TicketBooking.com.dto.request;

import TicketBooking.com.enums.StaffRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffRegisterRequest {
    @NotBlank(message = "first name is require")
    private String firstName;
    @NotBlank(message = "lastname is require")
    private String lastName;
    @NotBlank(message = "Email is required")
    @Email(message = "Must be a valid email format")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    private MultipartFile profile;
    @NotBlank(message = "staff code is require")
    private String staffCode;
    @NotNull(message = "Primary role is require")
    private StaffRole primaryRole;
}
