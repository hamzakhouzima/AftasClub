package com.youcode.aftasclub.repository;

import com.youcode.aftasclub.model.Hunting;
import com.youcode.aftasclub.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HuntingRepository extends JpaRepository<Hunting, Long> {

    @Query("SELECT h FROM Hunting h WHERE h.member.id = :memberId")
    List<Hunting> findHuntedFishByMemberId(Long memberId);


    public Hunting findByCompetitionIdAndMemberIdAndFishId(Long competitionId, Long memberId , Long fishId);
}
