package com.youcode.aftasclub.contorller;


import com.youcode.aftasclub.dto.CompetitionDTO;
import com.youcode.aftasclub.exception.CompetitionNotFoundException;
import com.youcode.aftasclub.model.Competition;
import com.youcode.aftasclub.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.web.servlet.function.ServerResponse.ok;

@RestController
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





}