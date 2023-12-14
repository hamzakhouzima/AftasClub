package com.youcode.aftasclub.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RankingDTO {
    private Long id;
    private Integer rank;
    private Integer score;
    private Long memberId;
    private Long competitionId;
}