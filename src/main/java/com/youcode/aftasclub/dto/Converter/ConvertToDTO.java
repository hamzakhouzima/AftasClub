package com.youcode.aftasclub.dto.Converter;

import com.youcode.aftasclub.dto.CompetitionDTO;
import com.youcode.aftasclub.dto.RankingDTO;
import com.youcode.aftasclub.model.Competition;
import com.youcode.aftasclub.model.Ranking;

import java.util.List;
import java.util.stream.Collectors;

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
//    public static CompetitionDTO convertToDTO(Competition competition) {
//        CompetitionDTO dto = new CompetitionDTO();
//        dto.setId(competition.getId());
//        dto.setCode(competition.getCode());
//        dto.setName(competition.getName());
//        // Map other attributes as needed
//        return dto;
//    }
//
//    public static List<CompetitionDTO> convertToDTOList(List<Competition> competitions) {
//        return competitions.stream()
//                .map(CompetitionConverter::convertToDTO)
//                .collect(Collectors.toList());
//    }
//


}
