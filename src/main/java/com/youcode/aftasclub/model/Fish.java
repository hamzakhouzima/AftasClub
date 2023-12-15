package com.youcode.aftasclub.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fish")
    private String fish;

    @Column(name = "average_weight")
    private double averageWeight;

    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;

}
