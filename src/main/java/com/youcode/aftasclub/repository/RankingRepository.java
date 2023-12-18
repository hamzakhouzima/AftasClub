package com.youcode.aftasclub.repository;

import com.youcode.aftasclub.model.Competition;
import com.youcode.aftasclub.model.Member;
import com.youcode.aftasclub.model.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {

    Member findByIdAndMemberId(Long id, Long MemberId);

//    Ranking findByCompetitionAndMember(Competition competitionId, Member member_id);

    Member findRankingByCompetitionAndMemberId(Competition code,Long participantId);

    boolean existsByCompetitionAndMember(Competition competition, Member participant);

//    Ranking findByCompetitionAndMember(Competition competition, Member participant);

    @Query("SELECT r FROM Ranking r WHERE r.competition = :competition AND r.member = :participant")
    Ranking findByCompetitionAndMember(Competition competition, Member participant);

}