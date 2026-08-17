package TicketBooking.com.service.implement;
import TicketBooking.com.dto.request.StaffLoginRequest;
import TicketBooking.com.dto.request.StaffRegisterRequest;
import TicketBooking.com.dto.response.StaffLoginResponse;
import TicketBooking.com.dto.response.StaffRegisterResponse;
import TicketBooking.com.exception.EmailAlreadyExist;
import TicketBooking.com.exception.FileUploadException;
import TicketBooking.com.exception.NotFoundException;
import TicketBooking.com.exception.PasswordAndEmailNotMatch;
import TicketBooking.com.models.Staff;
import TicketBooking.com.repository.StaffRepository;
import TicketBooking.com.security.JwtService;
import TicketBooking.com.service.StaffAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StaffAuthServiceImp implements StaffAuthService {

    private final PasswordEncoder passwordEncoder;
    private final StaffRepository staffRepository;
    private final JwtService jwtService;

    // Define the upload directory matching your WebConfig
    private final String UPLOAD_DIR = "upload/";
    @Override
    public StaffRegisterResponse register(StaffRegisterRequest request){
        if(staffRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExist("Email Already Exist");
        }
        // Handle files Upload
        String profileImage = null;
        MultipartFile file = request.getProfile();
        if(file != null && !file.isEmpty()){
            try {
                // Ensure the upload directory exist
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if(!Files.exists(uploadPath)){
                    Files.createDirectory(uploadPath);
                }
                // Generate a unique filename Using UUID to Prevent Overwriting
                String originalFilename = file.getOriginalFilename();
                String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
                String uniqueFileName = UUID.randomUUID().toString()  + fileExtension;

                // Save the file to the local directory
                Path filePath = uploadPath.resolve(uniqueFileName);
                Files.copy(file.getInputStream(),filePath, StandardCopyOption.REPLACE_EXISTING);

                // Set the URL path that the frontend will use to request the image
                // This matches the /upload/** resource handler in WebConfig
                profileImage = "/upload/" + uniqueFileName;

            }catch (RuntimeException | IOException e){
                throw new FileUploadException("File Upload Failed" + e.getMessage());
            }
        }
        // Build and Save the staff entity
        Staff staff = Staff.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // Hash Password
                .profile(profileImage)
                .staffCode(request.getStaffCode())
                .primaryRole(request.getPrimaryRole())
                .build();
        Staff saveStaff = staffRepository.save(staff);

        // Generate Token for auto-login
        String token = jwtService.generateToken(saveStaff);
        // Return Response
        return  StaffRegisterResponse.builder()
                .token(token)
                .id(saveStaff.getId())
                .firstName(saveStaff.getFirstName())
                .lastName(saveStaff.getLastName())
                .email(saveStaff.getEmail())
                .profile(saveStaff.getProfile())
                .primaryRole(saveStaff.getPrimaryRole())
                .build();
    }
    @Override
    public StaffLoginResponse login(StaffLoginRequest request){
        Staff staff = staffRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new NotFoundException("Not Found"));
        if(!passwordEncoder.matches(request.getPassword(), staff.getPassword())){
            throw  new PasswordAndEmailNotMatch("Password Do Not Match");
        }
        String token = jwtService.generateToken(staff);
        return StaffLoginResponse.builder()
                .message("Login Successful")
                .token(token)
                .id(staff.getId())
                .email(staff.getEmail())
                .profile(staff.getProfile())
                .staffCode(staff.getStaffCode())
                .primaryRole(staff.getPrimaryRole())
                .build();

    }
}
