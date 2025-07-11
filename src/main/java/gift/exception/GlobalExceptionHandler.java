package gift.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
<<<<<<< HEAD
<<<<<<< HEAD
import org.springframework.web.bind.annotation.ExceptionHandler;
=======
>>>>>>> 55360c0 (충남대 BE 김재혁 1단계 - 유효성 검사 및 예외 처리 (#177))
=======
import org.springframework.web.bind.annotation.ExceptionHandler;
>>>>>>> ade5d96 (충남대학교 BE 김재혁 위시리스트 2단계 (#246))
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status,
        WebRequest request) {
        Map<String, Object> body = new HashMap<>();

        body.put("statusCode", HttpStatus.BAD_REQUEST);
        body.put("timestamp", LocalDateTime.now());

        List<String> errors = ex.getBindingResult().getFieldErrors()
            .stream()
            .map(e -> e.getDefaultMessage())
            .collect(Collectors.toList());
        body.put("messages", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> ade5d96 (충남대학교 BE 김재혁 위시리스트 2단계 (#246))

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException e) {
        return createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<?> handleEmailExists(EmailAlreadyExistsException e) {
        return createErrorResponse(HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<?> handleMemberNotFound(MemberNotFoundException e) {
        return createErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<?> handleInvalidPassword(InvalidPasswordException e) {
        return createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

<<<<<<< HEAD
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handleIllegalState(IllegalStateException e) {
        return createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

=======
>>>>>>> ade5d96 (충남대학교 BE 김재혁 위시리스트 2단계 (#246))
    public ResponseEntity<?> createErrorResponse(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("statusCode", status);
        body.put("message", message);
        body.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(status).body(body);
    }
<<<<<<< HEAD
=======
>>>>>>> 55360c0 (충남대 BE 김재혁 1단계 - 유효성 검사 및 예외 처리 (#177))
=======
>>>>>>> ade5d96 (충남대학교 BE 김재혁 위시리스트 2단계 (#246))
}