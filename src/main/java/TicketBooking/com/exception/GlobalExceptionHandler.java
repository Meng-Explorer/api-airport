package TicketBooking.com.exception;

import TicketBooking.com.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // Catch our user Resource Not Found Exception
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFound(NotFoundException ex){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                        new ApiResponse<>(ex.getMessage(),404,null)
                    );
    }

    // Catch general bad requests (like user already exist)
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<?>handleBadRequest(DuplicateResourceException ex){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        new ApiResponse<>(ex.getMessage(),409,null)
                );
    }
    // Catch general Unauthorized
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<?> handleUnauthorized(ForbiddenException ex){
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(
                        new ApiResponse<>(ex.getMessage(),403,null)
                );
    }
    @ExceptionHandler(EmailAlreadyExist.class)
    public ResponseEntity<?> handleEmailAlreadyExist(EmailAlreadyExist ex){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        new ApiResponse<>(ex.getMessage(),409,null)
                );
    }
    @ExceptionHandler(PasswordAndEmailNotMatch.class)
    public ResponseEntity<?> handlePasswordAndEmailNotMatch(PasswordAndEmailNotMatch ex){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                        new ApiResponse<>(ex.getMessage(),401,null)
                );
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleUsernameNotFound(UsernameNotFoundException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ApiResponse<>(ex.getMessage(),404,null)
                );
    }
    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<?> handleFileUpload(FileUploadException ex){
        return ResponseEntity
                .status(HttpStatus.FAILED_DEPENDENCY)
                .body(
                        new ApiResponse<>(ex.getMessage(),500,null)
                );
    }


}
