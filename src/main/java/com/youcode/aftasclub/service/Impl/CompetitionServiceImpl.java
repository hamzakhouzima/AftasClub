package com.youcode.aftasclub.service.Impl;

import com.youcode.aftasclub.ToolKit.EntityDtoConverter;
import com.youcode.aftasclub.dto.CompetitionDTO;
import com.youcode.aftasclub.exception.CompetitionNotFoundException;
import com.youcode.aftasclub.exception.MemberNotFoundException;
import com.youcode.aftasclub.model.Competition;
import com.youcode.aftasclub.model.Member;
import com.youcode.aftasclub.model.Ranking;
import com.youcode.aftasclub.repository.CompetitionRepository;
import com.youcode.aftasclub.repository.MemberRepository;
import com.youcode.aftasclub.repository.RankingRepository;
import com.youcode.aftasclub.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class CompetitionServiceImpl implements CompetitionService {

    EntityDtoConverter EDC = new EntityDtoConverter();

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RankingRepository rankingRepository;



    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";


    @Override
    public void registerCompetition(CompetitionDTO competition) {
        if (!competitionRepository.existsById(competition.getId())) {
            throw new CompetitionNotFoundException("Competition not found");
        } else {
            competitionRepository.save(EDC.convertToEntity(competition , Competition.class));
        }
    }

    @Override
    public Competition findCompetitionById(Long id) {

            return null;
    }

    @Override
    public Competition updateCompetition(CompetitionDTO competition, Competition updatedCompetition) {
        Optional<Competition> optionalCompetition = competitionRepository.findById(competition.getId());
        if (((Optional<?>) optionalCompetition).isPresent()) {
            Competition existingCompetition = optionalCompetition.get();
            existingCompetition.setDate(updatedCompetition.getDate());
            existingCompetition.setAmount(updatedCompetition.getAmount());
            existingCompetition.setLocation(updatedCompetition.getLocation());
            existingCompetition.setStartTime(updatedCompetition.getStartTime());
            existingCompetition.setEndTime(updatedCompetition.getEndTime());
            existingCompetition.setNumberOfParticipants(updatedCompetition.getNumberOfParticipants());

            try {
                competitionRepository.save(existingCompetition);
                return existingCompetition;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        throw new RuntimeException("Failed to update competition");
    }

    @Override
    public void deleteCompetition(Competition competition) {
        try {
            competitionRepository.delete(competition);

        }catch(Exception e){
            System.out.println("error caused by "+e);
            throw new CompetitionNotFoundException("Error caused by "+e);
        }

    }




    @Override
    public void addParticipantToCompetition(Long Competitionid, Long participantId) {

        Competition competition = competitionRepository.findById(Competitionid)
                .orElseThrow(() -> new CompetitionNotFoundException("Competition not found"));

        Member participant = memberRepository.findById(participantId)
                .orElseThrow(() -> new MemberNotFoundException("Participant not found"));

        Ranking ranking = new Ranking();
        ranking.setCompetitionId(competition);
        ranking.setMember(participant);
        try {
            rankingRepository.save(ranking);

        }catch(Exception e){
            System.out.println("error caused by "+e);
            throw new RuntimeException("Failed to update competition => Caused  by" + e);
        }

    }
    @Override
    public void removeParticipantFromCompetition(Long id, Long participantId) {

    }

    @Override
    public Optional<Member> getParticipantById(Long id, Long participantId) {
        return Optional.ofNullable(Optional.ofNullable(rankingRepository.findByIdAndParticipantId(id, participantId))
                .flatMap(MemberRecord -> memberRepository.findById(MemberRecord.getId()))
                .orElseThrow(() -> new RuntimeException("Participant not found")));
    }



    @Override
    public List<Competition> getOnGoingCompetition() {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        return competitionRepository.findByEndTimeAfter(currentTime)
                .stream()
                .filter(competition -> competition.getDate().compareTo( Date.valueOf(currentDate)) >= 0)
                .collect(Collectors.toList());
    }


    public static String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

}
