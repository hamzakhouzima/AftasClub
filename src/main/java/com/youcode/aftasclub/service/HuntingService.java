package com.youcode.aftasclub.service;

import com.youcode.aftasclub.dto.HuntingDTO;
import com.youcode.aftasclub.model.Hunting;
import com.youcode.aftasclub.model.Member;

import java.util.List;

public interface HuntingService {


    public void saveHunting(Hunting hunting) throws Exception;

    Member getMemberById(Long id);
public List<Hunting> getHuntingsByMember(Long id);

    List<HuntingDTO> getAllHuntings();


    Hunting getHuntingById(Long id);

    public int calculatePointsForHuntedFish(Long huntedFishId);

    public int getScore(Long memberId);

    public Hunting findByCompetitionIdAndMemberIdAndFishId(Long competitionId, Long memberId);
//    findByCompetitionIdAndMemberId

//    findByCompetitionIdAndMemberIdAndFishId
//    Boolean isMemberRegisteredInCompetition(Long );
    public Boolean isMemberRegisteredInCompetition(Long memberId, Long competitionId) ;

    }
