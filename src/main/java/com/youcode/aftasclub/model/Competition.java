package com.youcode.aftasclub.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "competitions")
@Getter
@Setter
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String code;

    @Column
    private String name;

    @Column
    private LocalDate date; // Use LocalDate instead of Date

    @Column
    private LocalTime startTime; // Use LocalTime instead of Time

    @Column
    private LocalTime endTime; // Use LocalTime instead of Time

    @Column
    private Integer numberOfParticipants;

    @Column
    private String location; // Corrected lowercase "location"

    @Column
    private double amount;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
    private List<Hunting> huntedFish;

//    @OneToMany(mappedBy = "memberCompetitionDetails")
//    private List<Member> members = new ArrayList<>();
    }
