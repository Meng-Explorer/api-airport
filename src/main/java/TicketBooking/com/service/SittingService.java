package TicketBooking.com.service;

import TicketBooking.com.dto.request.ChangePasswordRequest;
import TicketBooking.com.dto.request.ProfileUpdateRequest;
import TicketBooking.com.dto.response.StaffProfileResponse;

public interface SittingService {
    StaffProfileResponse getProfiles(Long staffId);
    StaffProfileResponse updateProfile(Long staffId, ProfileUpdateRequest request);
    void changePassword(Long staffId, ChangePasswordRequest request);
}
