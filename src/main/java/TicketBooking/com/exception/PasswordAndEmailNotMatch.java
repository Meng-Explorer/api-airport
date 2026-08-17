package TicketBooking.com.exception;

public class PasswordAndEmailNotMatch extends RuntimeException {
    public PasswordAndEmailNotMatch(String message) {
        super(message);
    }
}
