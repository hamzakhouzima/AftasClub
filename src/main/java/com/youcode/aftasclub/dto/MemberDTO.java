package com.youcode.aftasclub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;


@Getter
@Setter
@ToString
public class MemberDTO {

    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Identification document is required")
    private String identificationDocument;

    @NotBlank(message = "Nationality is required")
    private String nationality;

    @NotBlank(message = "Membership number is required")
    private String membershipNumber;

    @NotNull(message = "Date of joining is required")
    private LocalDate dateOfJoining;


}
