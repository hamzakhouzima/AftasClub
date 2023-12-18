package com.youcode.aftasclub.service.Impl;

import com.youcode.aftasclub.dto.HuntingDTO;
import com.youcode.aftasclub.model.Fish;
import com.youcode.aftasclub.model.Hunting;
import com.youcode.aftasclub.model.Level;
import com.youcode.aftasclub.model.Member;
import com.youcode.aftasclub.repository.FishRepository;
import com.youcode.aftasclub.repository.HuntingRepository;
import com.youcode.aftasclub.repository.LevelRepository;
import com.youcode.aftasclub.service.HuntingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HuntingServiceImpl implements HuntingService {


    @Autowired
    private HuntingRepository huntingRepository;
    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private FishRepository fishRepository;
    public HuntingServiceImpl(HuntingRepository huntingRepository) {

    }

    @Override
    public void saveHunting(Hunting hunting) {
        try {
            huntingRepository.save(hunting);

        }catch(Exception e){
            System.out.println("error caused by "+e);
            throw new RuntimeException("Error caused by "+e);
        }
    }

    @Override
    public Member getMemberById(Long id) {
        return null;
    }


    @Override
    public Hunting getHuntingById(Long id) {
        try{
            Hunting hunting = huntingRepository.findById(id).orElse(null);
            return hunting;
        }catch(Exception e){
            throw new RuntimeException("Error caused by "+e);
        }

    }
    @Override
    public List<HuntingDTO> getAllHuntings() {
        try {
            List<Hunting> huntings = huntingRepository.findAll();
            List<HuntingDTO> huntingDTOs = new ArrayList<>();

            for (Hunting hunting : huntings) {
                HuntingDTO huntingDTO = new HuntingDTO(
                        hunting.getId(),
                        hunting.getFishNumber(),
                        hunting.getFish() != null ? hunting.getFish().getId() : null,
                        hunting.getMember() != null ? hunting.getMember().getId() : null
                );
                huntingDTOs.add(huntingDTO);
            }
            return huntingDTOs;
        } catch (Exception e) {
            throw new RuntimeException("Error caused by " + e);
        }
    }


    @Override
    public int calculatePointsForHuntedFish(Long huntedFishId) {
        Fish huntedFish = fishRepository.findById(huntedFishId).orElse(null);
        if (huntedFish != null) {
            Level fishLevel = huntedFish.getLevel(); // Retrieve the associated level
            if (fishLevel != null) {
                return fishLevel.getPoints(); // Return points associated with the level
            }
        }
        return 0;
    }

    @Override
    public int getScore(Long memberId) {
        int totalScoreForMember = 0;
        List<Hunting> huntingsByMember = getHuntingsByMember(memberId); // Assuming 'getHuntingsByMember()' retrieves the List<Hunting>

        for (Hunting huntedFish : huntingsByMember) {
            Long huntedFishId = huntedFish.getFish().getId(); // Assuming 'getFish()' retrieves the Fish entity
            int pointsForFish = calculatePointsForHuntedFish(huntedFishId);
            totalScoreForMember += pointsForFish * huntedFish.getFishNumber();
        }
        return totalScoreForMember;
    }

    @Override
    public List<Hunting> getHuntingsByMember(Long id) {
        try{
//            Hunting MemberHunting = (Hunting) huntingRepository.findHuntedFishByMemberId(id);
//            return (List<Hunting>) MemberHunting;
            return huntingRepository.findHuntedFishByMemberId(id);

        }catch(Exception e){
            throw new RuntimeException("Error caused by "+e);
        }
    }




}
