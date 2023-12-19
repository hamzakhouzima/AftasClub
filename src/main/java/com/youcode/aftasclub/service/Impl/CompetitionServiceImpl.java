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

import java.sql.Date;
import java.time.*;
//import java.sql.Date;
import java.util.ArrayList;
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
        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate competitionDate = competition.getDate(); // Assuming competition.getDate() returns a java.sql.Date

            LocalDate localCompetitionDate = competitionDate;

            if (competitionRepository.existsByCode(competition.getCode())) {
                throw new CompetitionNotFoundException("Competition already exists");
            } else if (localCompetitionDate.isBefore(currentDate)) {
                throw new IllegalArgumentException("Competition date must be in the future or present");
            } else {
                competition.setCode(generateRandomString(7));
                competitionRepository.save(EDC.convertToEntity(competition, Competition.class));
            }
        } catch (Exception e) {
            throw new CompetitionNotFoundException("Error occurred while registering a competition " + e);
        }
    }

    public Competition findCompetitionByCode(String code) {
        try {
            return competitionRepository.findByCode(code);
        }catch(Exception e){
            throw new CompetitionNotFoundException("Competition Not Found");
        }

    }


//    @Override
//    public Competition findCompetitionById(Long id) {
//
//            return null;
//    }



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
        ranking.setCompetition(competition);
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
                .filter(competition -> competition.getDate().compareTo(Date.valueOf(currentDate).toLocalDate()) >= 0)
                .collect(Collectors.toList());
    }


    @Override
    public Competition getCompetitionById(Long id) {
        return competitionRepository.findById(id)
                .orElseThrow(() -> new CompetitionNotFoundException("Competition not found"));
    }


    @Override
    public List<CompetitionDTO> getAllCompetitions() {
        try {
            List<Competition> competitions = competitionRepository.findAll();
            List<CompetitionDTO> competitionDTOs = new ArrayList<>();

            for (Competition competition : competitions) {
                CompetitionDTO competitionDTO = mapToCompetitionDTO(competition);
                competitionDTOs.add(competitionDTO);
            }
            return competitionDTOs;
        } catch (Exception e) {
            throw new CompetitionNotFoundException("There are no competitions");
        }
    }
    @Override
    public List<CompetitionDTO> getOngoingCompetitions() {
        LocalDateTime now = LocalDateTime.now();
        List<Competition> ongoingCompetitions = competitionRepository.findAll()
                .stream()
                .filter(competition -> competition.getStartTime().isBefore(LocalTime.from(now))
                        && competition.getEndTime().isAfter(LocalTime.from(now)))
                .collect(Collectors.toList());

        // Map Competition entities to CompetitionDTOs
//        return (List<CompetitionDTO>) EntityDtoConverter.convertToDto(ongoingCompetitions,Competition.class);
        return ongoingCompetitions.stream()
                .map(this::mapToCompetitionDTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<CompetitionDTO> getDoneCompetitions() {
        LocalDateTime now = LocalDateTime.now();
        List<Competition> doneCompetitions = competitionRepository.findAll()
                .stream()
                .filter(competition -> competition.getEndTime().isBefore(LocalTime.from(now)))
                .collect(Collectors.toList());

        // Map Competition entities to CompetitionDTOs
        return doneCompetitions.stream()
                .map(this::mapToCompetitionDTO)
                .collect(Collectors.toList());
//        return (List<CompetitionDTO>) EntityDtoConverter.convertToDto(doneCompetitions,Competition.class);
    }


    private CompetitionDTO mapToCompetitionDTO(Competition competition) {
        CompetitionDTO competitionDTO = new CompetitionDTO();
        competitionDTO.setId(competition.getId());
        competitionDTO.setCode(competition.getCode());
        competitionDTO.setDate(competition.getDate());
        competitionDTO.setStartTime(competition.getStartTime());
        competitionDTO.setEndTime(competition.getEndTime());
        competitionDTO.setNumberOfParticipants(competition.getNumberOfParticipants());
        competitionDTO.setLocation(competition.getLocation());
        // Map other fields as needed

        return competitionDTO;
    }


}
