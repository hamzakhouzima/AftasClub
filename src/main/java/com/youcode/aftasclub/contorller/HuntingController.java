package com.youcode.aftasclub.contorller;


import com.youcode.aftasclub.dto.HuntingDTO;
import com.youcode.aftasclub.dto.RankingDTO;
import com.youcode.aftasclub.model.*;
import com.youcode.aftasclub.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController


@CrossOrigin(origins = "http://localhost:4200/")

public class HuntingController {

    @Autowired
    private HuntingService huntingService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private FishService fishService;
    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private RankingService rankingService;


    @GetMapping("/hunting")
    public ResponseEntity<List<HuntingDTO>> getHunting() {
        try{
            if (huntingService.getAllHuntings() != null) {
                return ResponseEntity.ok(huntingService.getAllHuntings());
            }
        }catch(Exception e){
            throw new RuntimeException("Error caused by "+e);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/saveHuntingEvent")
    public ResponseEntity<String> saveHuntingEvent(@RequestBody AddHuntingRequest request) {
        try {
            Long fishId = request.getFish_id();
            Long memberId = request.getMember_id();
            int fishNumber = request.getFishNumber();
            Long competitionId = request.getCompetition_id();

            Member member = memberService.getMemberById(memberId);
            Fish fish = fishService.getFishById(fishId);

            // Access level information directly from Fish
            Level level = fish.getLevel();

            // If level exists, get the points
            int fishPoints = (level != null) ? level.getPoints() : 0;

            Competition competition = competitionService.getCompetitionById(competitionId);

            // Make a new Hunting entity
            Hunting huntingEvent = new Hunting();
            huntingEvent.setFishNumber(fishNumber);
            huntingEvent.setFish(fish);
            huntingEvent.setMember(member);
            huntingEvent.setCompetition(competition);

            // Save the hunting event
            huntingService.saveHunting(huntingEvent);

            // Update the ranking
            Ranking ranking = rankingService.getRankingByMemberAndCompetition(memberId, competitionId);
            if (ranking != null) {
                // Increment the score by fish points
                ranking.setScore(ranking.getScore() + fishPoints);

                // Update the ranking
                rankingService.updateRanking(ranking);
            }

            return ResponseEntity.ok("Hunting event saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save hunting event: " + e.getMessage());
        }
    }


    @GetMapping("/score/{memberId}")
    public ResponseEntity<Integer> getScoreForMember(@PathVariable Long memberId) {
        try {
            int score = huntingService.getScore(memberId);
            return ResponseEntity.ok(score);
        } catch (Exception e) {
            throw new RuntimeException("Error caused by "+e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(-1); // Return an error code or default value
        }
    }



}
