package com.youcode.aftasclub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Hunting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;




    @Column(name = "fish_number")
    private Integer fishNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fish_id")
    private Fish fish;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "competition_id")
    private Competition competition;
}
