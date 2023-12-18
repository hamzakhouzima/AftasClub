package com.youcode.aftasclub.contorller;


import com.youcode.aftasclub.dto.CompetitionDTO;
import com.youcode.aftasclub.exception.CompetitionNotFoundException;
import com.youcode.aftasclub.exception.MemberNotFoundException;
import com.youcode.aftasclub.model.Competition;
import com.youcode.aftasclub.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.web.servlet.function.ServerResponse.ok;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    @PostMapping("/RegisterCompetition")
    public ResponseEntity<CompetitionDTO> RegisterCompetition(@RequestBody CompetitionDTO competition) {
        try{
            competitionService.registerCompetition(competition);
            return  ResponseEntity.ok(competition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

        @PutMapping("/updateCompetition/{code}")
    public ResponseEntity<Competition> UpdateCompetition(@RequestBody Competition competition, @PathVariable String code) {
        try{
//            Competition Oldcompetition =   competitionService.findCompetitionByCode(code);
            competitionService.updateCompetition(competition , code);
            return  ResponseEntity.ok(competition);

        }catch(Exception e){
             e.printStackTrace();

        }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

        @DeleteMapping("/deleteCompetition/{code}")
            public ResponseEntity<Competition> DeleteCompetition(@PathVariable String code) {
                try{
                    Competition Oldcompetition =   competitionService.findCompetitionByCode(code);
                    competitionService.deleteCompetition(Oldcompetition);
                    return  ResponseEntity.ok(Oldcompetition);
            }catch(Exception e){
                e.printStackTrace();
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }



    @PostMapping("/{competitionId}/participants/{participantId}")
    public ResponseEntity<String> addParticipantToCompetition(
            @PathVariable("competitionId") Long competitionId,
            @PathVariable("participantId") Long participantId) {

        try {
            competitionService.addParticipantToCompetition(competitionId, participantId);
            return ResponseEntity.ok("Participant added successfully to the competition.");
        } catch (CompetitionNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Competition not found: " + e.getMessage());
        } catch (MemberNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Participant not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update competition: " + e.getMessage());
        }
    }


}