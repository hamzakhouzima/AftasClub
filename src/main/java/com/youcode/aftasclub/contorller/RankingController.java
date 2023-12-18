package com.youcode.aftasclub.contorller;

//import com.youcode.aftasclub.exception.RegisterationClosedException;
import com.youcode.aftasclub.dto.RankingDTO;
import com.youcode.aftasclub.exception.RegistrationClosedException;
import com.youcode.aftasclub.model.Competition;
import com.youcode.aftasclub.model.Member;
import com.youcode.aftasclub.model.Ranking;
import com.youcode.aftasclub.repository.RankingRepository;
import com.youcode.aftasclub.service.CompetitionService;
import com.youcode.aftasclub.service.RankingService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class RankingController {


    @Autowired
    RankingService rankingService;
//    @Autowired
//    CompetitionService competitionService;

    @PostMapping("/addParticipant")
    public ResponseEntity<String> addParticipantToCompetition(
            @RequestBody AddParticipantRequest request) {

        try {
            rankingService.addParticipantToCompetition(request.getCompetition(), request.getMember());
            return ResponseEntity.ok("Participant added to the competition successfully.");
        } catch (RegistrationClosedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Participant is already registered in this competition: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update competition: " + e.getMessage());
        }
    }

    @GetMapping("/participant/rank")
    public ResponseEntity<RankingDTO> getParticipantRank(
            @RequestBody AddParticipantRequest request) {
        try {
            RankingDTO ranking = rankingService.getParticipantRank(request.getCompetition(), request.getMember());
            return ResponseEntity.ok(ranking);
        } catch (Exception e) {
            System.out.println("Error caused by " + e.toString());
            throw new RegistrationClosedException("Failed to update competition => Caused by " + e);
        }
    }


//    @DeleteMapping("delete/{competitionId}/participants/{participantId}")
    @DeleteMapping("delete/participants")
    public ResponseEntity<String> removeParticipantFromCompetition(
            @RequestBody AddParticipantRequest request
    ) {

        try {
//            Long competitionId = (Long) requestBody.get("competitionId");
//            Long participantId = (Long) requestBody.get("participantId");

            rankingService.removeParticipantFromCompetition(request.getCompetition(), request.getMember());
            return ResponseEntity.ok("Participant removed from competition successfully");
        } catch (RegistrationClosedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to remove participant from competition: " + e.getMessage());
        }
    }



}
