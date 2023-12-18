package com.youcode.aftasclub.service.Impl;


//import com.youcode.aftasclub.exception.RegisterationClosedException;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

@Service
public class RankingServiceImpl implements RankingService {

    @Autowired
    RankingRepository rankingRepository;

    @Autowired
    CompetitionRepository competitionRepository;

//    EntityDtoConverter.convertToDto(Competition competition, Competition.class);
    ConvertToDTO convertToDTO;

        //TODO : this should be done
//    public Boolean beforeTwentyFourHours() {
//        Date now = new Date();
//        LocalTime competitionStartDate = Competition.getStartTime();// get the competition start date here
//        long difference = competitionStartDate.getTime() - now.getTime();
//        long twentyFourHoursInMillis = 24 * 60 * 60 * 1000;
//        return difference < twentyFourHoursInMillis;
//    }



        @Override
        public void addParticipantToCompetition(Competition competition, Member participant) {


            try {
                if (isParticipantInCompetition(competition, participant).size() > 0 && competition.getEndTime().isBefore(LocalTime.now())) {

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
        List<Ranking> rankings = (List<Ranking>) rankingRepository.findByCompetitionAndMember(competition, participant);

        // Check if the returned list is null
        return rankings != null ? rankings : Collections.emptyList();
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


    @Override //this get the competition and participant that plays in a competition
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

    @Override
    public List<Ranking> getTop3RankingsForCompetition(Long competitionId) {
        // Fetch top 3 rankings for the specified competition ID
        return rankingRepository.findTop3ByCompetitionIdOrderByScoreDesc(competitionId,  PageRequest.of(0, 3));
    }
}
