package com.youcode.aftasclub.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CollectionIdMutability;

import java.sql.Date;

@Entity
@Table(name = "competitions")
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


}
