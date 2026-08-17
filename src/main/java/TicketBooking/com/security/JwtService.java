package TicketBooking.com.security;

import TicketBooking.com.models.Staff;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private long expired;

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(Staff staff) {
        return Jwts.builder()
                .subject(staff.getEmail())
                .claim("role", staff.getPrimaryRole().name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expired))
                .signWith(getKey())
                .compact();
    }



    // Function For Spring Security Filter

    // Take Email from Token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Compare Token for Staff and Expire or Not
    // final decide
    public boolean isTokenValid(String token, Staff staff) {
        final String username = extractUsername(token);
        return (username.equals(staff.getEmail())) && !isTokenExpired(token);
    }

    // Check Token Expire or Not
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // take expire from Token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

      // take data one by one (Claims)
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // break Token to take Payload to reading
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey()) // for JJWT version 0.12.x
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


}
