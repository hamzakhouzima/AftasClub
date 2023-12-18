package com.youcode.aftasclub.contorller;


import com.youcode.aftasclub.dto.HuntingDTO;
import com.youcode.aftasclub.model.Competition;
import com.youcode.aftasclub.model.Fish;
import com.youcode.aftasclub.model.Hunting;
import com.youcode.aftasclub.model.Member;
import com.youcode.aftasclub.service.CompetitionService;
import com.youcode.aftasclub.service.FishService;
import com.youcode.aftasclub.service.HuntingService;
import com.youcode.aftasclub.service.MemberService;
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
            Competition competition = competitionService.getCompetitionById(competitionId);


            // make  a new Hunting entity
            Hunting huntingEvent = new Hunting();
            huntingEvent.setFishNumber(fishNumber);
            huntingEvent.setFish(fish);
            huntingEvent.setMember(member);
            huntingEvent.setCompetition(competition);

            // save the hunting
            huntingService.saveHunting(huntingEvent);

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
