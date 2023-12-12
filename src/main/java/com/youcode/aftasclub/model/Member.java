package com.youcode.aftasclub.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CollectionIdMutability;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Members")
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // num : integer

    @Column
    private String FirstName;

    @Column
    private String LastName;

    @Column
    private Date accessDate;

    @Column
    private String nationality;


    private enum IdentityType {
        CIN, PASSPORT , CART_RESIDENCE
    }

    @ManyToMany
    @JoinTable(
            name = "member_competition",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "competition_id")
    )
    private List<Competition> competitions = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private Set<Hunting> hunting = new HashSet<>(); // Use Set for collections to avoid duplicates


}
