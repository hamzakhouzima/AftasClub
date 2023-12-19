package com.youcode.aftasclub.service;

import com.youcode.aftasclub.dto.CompetitionDTO;
import com.youcode.aftasclub.model.Competition;
import com.youcode.aftasclub.model.Member;

import java.util.List;
import java.util.Optional;

public interface CompetitionService {
     void registerCompetition(CompetitionDTO competition);

     Competition getCompetitionById(Long id);

     Competition updateCompetition(Competition updatedCompetition, String competitionCode);


     void deleteCompetition(Competition competition);

     void addParticipantToCompetition(Long id, Long participantId);

     void removeParticipantFromCompetition(Long id, Long participantId);

     Optional<Member> getParticipantById(Long id, Long participantId);

     List<Competition> getOnGoingCompetition();

     List<CompetitionDTO> getAllCompetitions();
     Competition findCompetitionByCode(String code);

     public List<CompetitionDTO> getOngoingCompetitions();
     public List<CompetitionDTO> getDoneCompetitions();
}
