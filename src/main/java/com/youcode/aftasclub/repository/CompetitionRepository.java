package com.youcode.aftasclub.repository;

import com.youcode.aftasclub.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.swing.text.StyledEditorKit;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {

    List<Competition> findByEndTimeAfter(LocalTime time);
    Competition findByCode(String code);

    Boolean existsByCode(String code);

}
