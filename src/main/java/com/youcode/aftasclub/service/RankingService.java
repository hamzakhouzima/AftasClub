package com.youcode.aftasclub.service;

import com.youcode.aftasclub.dto.RankingDTO;
import com.youcode.aftasclub.model.Competition;
import com.youcode.aftasclub.model.Member;
import com.youcode.aftasclub.model.Ranking;

public interface RankingService {

    void addParticipantToCompetition(Competition competitionId, Member participantID);

    void removeParticipantFromCompetition(Competition competitionId, Member participantId);


    RankingDTO getParticipantRank(Competition code, Member participantId);

    Ranking updateParticipantRank(Ranking ranking);


}
