package TicketBooking.com.models;

import TicketBooking.com.enums.StaffRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Staff")
public class Staff implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Long id;

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String profile;
    private String staffCode;

    @Enumerated(EnumType.STRING)
    private StaffRole primaryRole;

    // For JwtAuthentication
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Tell spring Security staff have Assignment
        return List.of(new SimpleGrantedAuthority(primaryRole.name()));
    }
    @Override
    // with userDetailService that use findByEmail
    // using email for login
    public String getUsername() {
        // Tell Spring Security that take email it's a username for login
        return email;
    }
    // For determine accounting
    // Take it to boolean cause easy using
    // meaning all staffs can use didn't locked or expire
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

}
