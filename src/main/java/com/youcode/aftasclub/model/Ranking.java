package com.youcode.aftasclub.model;


import jakarta.persistence.*;

@Entity
@Table(name = "ranking")


public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "rank")
    private Integer rank;


    @Column(name = "score")
    private Integer score;


    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competitionId;


}
