package com.youcode.aftasclub.service;

import com.youcode.aftasclub.model.Ranking;

public interface RankingService {

    void addParticipantToCompetition(Long competitionId, Long participantId);

    void removeParticipantFromCompetition(Long competitionId, Long participantId);


    Ranking getParticipantRank(Long competitionId, Long participantId);

    Ranking updateParticipantRank(Ranking ranking);


}
