package com.youcode.aftasclub.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CollectionIdMutability;

import java.util.Date;
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

    @Column(name ="first_name")
    private String firstName;

    @Column(name ="last_name")
    private String lastName;


    @Column(name="identity_number")
    private String identityNumber;

    @Column
    private Date accessDate;

    @Column
    private String nationality;


    private enum IdentityType {
        CIN, PASSPORT , CART_RESIDENCE
    }



    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
//    @JsonManagedReference
    private List<Ranking> memberCompetitionDetails = new ArrayList<>();



    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
//    @JsonManagedReference
    private Set<Hunting> hunting = new HashSet<>(); // Use Set for collections to avoid duplicates



}
