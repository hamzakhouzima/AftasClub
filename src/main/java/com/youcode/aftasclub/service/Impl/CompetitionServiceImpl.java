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
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
@Service
public class CompetitionServiceImpl implements CompetitionService {

    EntityDtoConverter EDC = new EntityDtoConverter();

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RankingRepository rankingRepository;



    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        System.out.println("Random String: " + sb.toString());
        return sb.toString();
    }


    @Override
    public void registerCompetition(CompetitionDTO competition) {
        if (competitionRepository.existsByCode(competition.getCode())) {
            throw new CompetitionNotFoundException("Competition Already exist:::");
        } else {
            competition.setCode(generateRandomString(7));


            competitionRepository.save(EDC.convertToEntity(competition , Competition.class));

            //TODO : should set the code with random code using the bellow randomString method \/\/
        }
    }

    public Competition findCompetitionByCode(String code) {
        try {
            return competitionRepository.findByCode(code);
        }catch(Exception e){
            throw new CompetitionNotFoundException("Competition Not Found");
        }

    }


    @Override
    public Competition findCompetitionById(Long id) {

            return null;
    }



    @Override
    @Transactional
    public Competition updateCompetition(Competition updatedCompetition, String competitionCode) {
        Competition existingCompetition = competitionRepository.findByCode(competitionCode);
//existingCompetition is the old competition
        if (existingCompetition != null) {
            existingCompetition.setDate(updatedCompetition.getDate());
            existingCompetition.setAmount(updatedCompetition.getAmount());
            existingCompetition.setLocation(updatedCompetition.getLocation());
            existingCompetition.setStartTime(updatedCompetition.getStartTime());
            existingCompetition.setEndTime(updatedCompetition.getEndTime());
            existingCompetition.setNumberOfParticipants(updatedCompetition.getNumberOfParticipants());

            // Save the updated entity using the repository
            try {
                Competition savedCompetition = competitionRepository.save(existingCompetition);
                return savedCompetition;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to update competition");
            }
        }

        throw new CompetitionNotFoundException("Competition with code " + competitionCode + " not found");
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
        return Optional.ofNullable(Optional.ofNullable(rankingRepository.findByIdAndMemberId(id, participantId))
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



}
