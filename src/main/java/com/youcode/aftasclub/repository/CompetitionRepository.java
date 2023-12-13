package com.youcode.aftasclub.repository;

import com.youcode.aftasclub.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {

    List<Competition> findByEndTimeAfter(LocalTime time);


}
