package com.youcode.aftasclub.service.Impl;


//import com.youcode.aftasclub.exception.RegisterationClosedException;
import com.youcode.aftasclub.ToolKit.EntityDtoConverter;
import com.youcode.aftasclub.dto.Converter.ConvertToDTO;
import com.youcode.aftasclub.dto.RankingDTO;
import com.youcode.aftasclub.exception.CompetitionNotFoundException;
import com.youcode.aftasclub.exception.RegistrationClosedException;
import com.youcode.aftasclub.model.Competition;
import com.youcode.aftasclub.model.Member;
import com.youcode.aftasclub.model.Ranking;
import com.youcode.aftasclub.repository.CompetitionRepository;
import com.youcode.aftasclub.repository.RankingRepository;
import com.youcode.aftasclub.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RankingServiceImpl implements RankingService {

    @Autowired
    RankingRepository rankingRepository;

    @Autowired
    CompetitionRepository competitionRepository;

//    EntityDtoConverter.convertToDto(Competition competition, Competition.class);
ConvertToDTO convertToDTO;

    @Override
    public void addParticipantToCompetition(Competition competition, Member participant) {


        try {
            if (isParticipantInCompetition(competition, participant).size()>0) {

                throw new RegistrationClosedException ("Participant is already registered in this competition");
            }
                Ranking ranking = new Ranking();
                ranking.setCompetition(competition);
                ranking.setMember(participant);
                rankingRepository.save(ranking);

        } catch (Exception e) {
            System.out.println("Error caused by " + e);
            throw new RegistrationClosedException("Failed to update competition => Caused by " + e);
        }

    }


    private List<Ranking> isParticipantInCompetition(Competition competition, Member participant) {

         return (List<Ranking>) rankingRepository.findByCompetitionAndMember(competition, participant);
    }


    @Override
    public void removeParticipantFromCompetition(Competition competitionId, Member participantId) {
//Delete method
        try {
            Ranking ranking = rankingRepository.findByCompetitionAndMember(competitionId, participantId);
            if (ranking != null) {
                rankingRepository.delete(ranking);
            } else {
                throw new RegistrationClosedException("Failed to find participant in competition");
            }
        } catch (Exception e) {
            System.out.println("error caused by " + e);
            throw new RegistrationClosedException("Failed to update competition => Caused by" + e);
        }
    }


    @Override
    public RankingDTO getParticipantRank(Competition code, Member participantId) {
        Ranking ranking = rankingRepository.findByCompetitionAndMember(code, participantId);
        if (ranking == null) {
            throw new CompetitionNotFoundException("Competition with code " + code + " not found");
        }
        return convertToDTO.convertToDTO(ranking);
    }

    @Override
    public Ranking updateParticipantRank(Ranking ranking) {

        return null;
    }
}
