package com.youcode.aftasclub.repository;

import com.youcode.aftasclub.model.Member;
import com.youcode.aftasclub.model.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {

    Member findByIdAndParticipantId(Long id, Long participantId);
}
