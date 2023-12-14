package com.youcode.aftasclub.dto.Converter;

import com.youcode.aftasclub.dto.RankingDTO;
import com.youcode.aftasclub.model.Ranking;

public class ConvertToDTO {
    public static RankingDTO convertToDTO(Ranking ranking) {
        RankingDTO dto = new RankingDTO();
        dto.setId(ranking.getId());
        dto.setRank(ranking.getRank());
        dto.setScore(ranking.getScore());
        dto.setMemberId(ranking.getMember().getId());
        dto.setCompetitionId(ranking.getCompetition().getId());
        return dto;
    }

}
