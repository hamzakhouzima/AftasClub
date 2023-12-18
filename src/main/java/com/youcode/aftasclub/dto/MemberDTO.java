package com.youcode.aftasclub.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
public class MemberDTO {

    private Long id =0L;

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

//    @NotBlank(message = "Date of joining is required")
    private Date dateOfJoining;


}
