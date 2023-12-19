package com.youcode.aftasclub.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;



@Getter
@Setter
public class CompetitionDTO {
    private Long id;

    @NotBlank(message = "Code is required")
    private String code;

    @NotNull(message = "Date is required")
    @FutureOrPresent(message = "Date should be in the present or future")
    private LocalDate date;

    @NotNull(message = "Start time is required")
    @FutureOrPresent(message = "Start time should be in the present or future")
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    @FutureOrPresent(message = "End time should be in the present or future")
    private LocalTime endTime;

    @Min(value = 2, message = "Number of participants should be at least 2")
    private Integer numberOfParticipants;

    @NotBlank(message = "Location is required")
    private String location;

    // Other attributes and constructors

//    public CompetitionDTO(Long id, String code, LocalDate date, LocalTime startTime, LocalTime endTime, Integer numberOfParticipants, String location, ...) {
//        this.id = id;
//        this.code = code;
//        this.date = date;
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.numberOfParticipants = numberOfParticipants;
//        this.location = location;
//        // Initialize other attributes
//    }

    // Getters and setters for attributes
}
