package com.youcode.aftasclub.exception;

import org.apache.coyote.Response;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CompetitionNotFoundException.class)
    public ResponseEntity<ApiError> handleCompetitionNotFoundException(CompetitionNotFoundException ex, WebRequest request) {
        ApiError apiError = new ApiError(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Competition Not Found!!",
                "Competition Not Found",
                request.getDescription(false));

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ApiError> handleMemberNotFoundException(MemberNotFoundException ex, WebRequest request) {
        ApiError apiError = new ApiError(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Member Not Found",
                ex.getMessage(),
                request.getDescription(true));
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ApiError> handleInvalidDataException(InvalidDataException ex, WebRequest request) {
        ApiError apiError = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Invalid Data",
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RegisterationClosedException.class)
    public ResponseEntity<ApiError> handleRegistrationClosedException(RegisterationClosedException ex, WebRequest request) {
        ApiError apiError = new ApiError(
                LocalDateTime.now(),
                HttpStatus.FORBIDDEN.value(),
                "Registration Closed",
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(DuplicateRegistrationException.class)
    public ResponseEntity<ApiError> handleDuplicateRegistrationException(DuplicateRegistrationException ex, WebRequest request) {
        ApiError apiError = new ApiError(
                LocalDateTime.now(),
                HttpStatus.FORBIDDEN.value(),
                "Already Registered",
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
    }
    // Add more exception handlers for different types of exceptions here

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGlobalExceptions(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//   /\ ``CompetitionNotFoundException``: When attempting to access a competition that doesn't exist.
//
//  /\  ``MemberNotFoundException``: If a member is not found when trying to retrieve their information or register them for a competition.
//
//  /\  ``InvalidDataException``: For any invalid data input during competition creation or result submission, like incorrect dates, missing fields, etc.
//
//  /\  ``RegistrationClosedException``: When a member tries to register for a competition after the registration deadline.
//
//  /\  ``DuplicateRegistrationException``: If a member tries to register for the same competition more than once.
//
//    ``InternalServerErrorException``: For unexpected errors that aren't handled explicitly.
}
