package com.youcode.aftasclub.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

//import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;


@Getter
@Setter
@Builder
public class CompetitionDTO {



    private Long id = 0L;


//    @NotBlank(message = "Name is required")
    @NotBlank(message = "Code is required")
    private String code;

    @NotNull(message = "Date is required")
    @FutureOrPresent(message = "Date should be in the present or future")
    private LocalDate  date;
//    private Date date;

    @NotNull(message = "Start time is required")
    @FutureOrPresent(message = "Date should be in the present or future")
    private LocalTime  startTime;

    @NotNull(message = "End time is required")
    @FutureOrPresent(message = "Date should be in the present or future")
//    @GreaterThan(value = "startTime", message = "End time should be after start time")
    private LocalTime  endTime;

    @Min(value = 2, message = "Number of participants should be at least 2")
    private Integer numberOfParticipants;

    @NotBlank(message = "Location is required")
    private String location;

    @Min(value = 0, message = "Amount should not be negative")
    private double amount;

    // Constructors, getters, setters...
}