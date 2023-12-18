package com.youcode.aftasclub.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HuntingDTO {
    private Long id;
    private Integer fishNumber;
    private Long fishId;
    private Long memberId;

    // Constructors, getters, and setters
    // Constructor to map from Hunting entity to HuntingDTO
    public HuntingDTO(Long id, Integer fishNumber, Long fishId, Long memberId) {
        this.id = id;
        this.fishNumber = fishNumber;
        this.fishId = fishId;
        this.memberId = memberId;
    }


}
