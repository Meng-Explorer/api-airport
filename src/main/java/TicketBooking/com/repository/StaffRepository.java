package TicketBooking.com.repository;

import TicketBooking.com.enums.StaffRole;
import TicketBooking.com.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Long> {
    // Find staff by Email
    Optional<Staff> findByEmail(String email);

    // Check Email User using Or Not
    boolean existsByEmail(String email);

    // This function can find Staff follow Assignment(Enum)
    boolean existsByEmail (StaffRole staffRole);

    // This function find staff when he input Username(staffCode)
    Optional<Staff> findByStaffCode(String staffCode);
}
