package TicketBooking.com.service.implement;

import TicketBooking.com.dto.request.ChangePasswordRequest;
import TicketBooking.com.dto.request.ProfileUpdateRequest;
import TicketBooking.com.dto.response.StaffProfileResponse;
import TicketBooking.com.exception.ForbiddenException;
import TicketBooking.com.exception.NotFoundException;
import TicketBooking.com.models.Staff;
import TicketBooking.com.repository.StaffRepository;
import TicketBooking.com.service.SittingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SittingServiceImp implements SittingService {

    private final StaffRepository staffRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public StaffProfileResponse getProfiles(Long staffId) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(()-> new NotFoundException("Staff not found"));
        return mapToResponse(staff);
    }
    @Override
    public StaffProfileResponse updateProfile(Long staffId, ProfileUpdateRequest request){
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(()-> new NotFoundException("Staff not found"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        boolean isOwner = staff.getEmail() != null && staff.getEmail().equalsIgnoreCase(email);
        boolean isManager = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("MANAGER") || a.getAuthority().equals("ROLE_MANAGER"));

        if (!isOwner && !isManager) {
            throw new ForbiddenException("You do not have permission to update this profile!");
        }

        staff.setFirstName(request.getFirstName());
        staff.setLastName(request.getLastName());
        staff.setEmail(request.getEmail());

        MultipartFile file = request.getProfile();
        if(file != null && !file.isEmpty()){
            try{
                Path uploadPath = Paths.get("upload/");
                if(!Files.exists(uploadPath)){
                    Files.createDirectory(uploadPath);
                }
                String originalFilename = file.getOriginalFilename();
                String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
                String uniqueFileName = UUID.randomUUID().toString()  + fileExtension;

                Path filePath = uploadPath.resolve(uniqueFileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                staff.setProfile("/upload/" + uniqueFileName);
            }catch (Exception e){
                throw new ForbiddenException("Invalid profile");
            }
        }


        return mapToResponse(staffRepository.save(staff));
    }
    @Override
    public void changePassword(Long staffId, ChangePasswordRequest request){
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(()-> new NotFoundException("Staff not found"));
        if(!passwordEncoder.matches(request.getOldPassword(),staff.getPassword())){
            throw new ForbiddenException("Passwords don't match");
        }
        staff.setPassword(passwordEncoder.encode(request.getNewPassword()));
        staffRepository.save(staff);
    }

    private StaffProfileResponse mapToResponse(Staff staff) {
        return StaffProfileResponse.builder()
                .staffId(staff.getId())
                .staffCode(staff.getStaffCode())
                .firstName(staff.getFirstName())
                .lastName(staff.getLastName())
                .email(staff.getEmail())
                .primaryRole(staff.getPrimaryRole())
                .profile(staff.getProfile())
                .build();
    }
}
