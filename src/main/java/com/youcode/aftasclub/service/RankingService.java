package com.youcode.aftasclub.service;

import com.youcode.aftasclub.dto.RankingDTO;
import com.youcode.aftasclub.model.Competition;
import com.youcode.aftasclub.model.Member;
import com.youcode.aftasclub.model.Ranking;

import java.util.List;

public interface RankingService {

    void addParticipantToCompetition(Competition competitionId, Member participantID);

    void removeParticipantFromCompetition(Competition competitionId, Member participantId);


    RankingDTO getParticipantRank(Competition code, Member participantId);

    Ranking updateParticipantRank(Ranking ranking);
    List<Ranking> getTop3RankingsForCompetition(Long competitionId);

     Ranking getRankingByMemberAndCompetition(Long memberId, Long competitionId);
//    Ranking getRankingByMemberAndCompetition(Long m , Long c);

     void updateRanking(Ranking ranking) ;
}
