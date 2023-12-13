package com.youcode.aftasclub.model;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "competitions")
@Getter
@Setter
//@AllArgsConstructor
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String code;

    @Column
    private Date date;

    @Column
    private Time startTime;
    @Column
    private Time endTime;

    @Column
    private Integer numberOfParticipants;

    @Column
    private String Location;



    @Column
    private double amount;
//
//    @OneToMany(mappedBy = "memberCompetitionDetails")
//    private List<Member> members = new ArrayList<>();


}
