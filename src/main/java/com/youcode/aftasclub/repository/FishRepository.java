package com.youcode.aftasclub.repository;

import com.youcode.aftasclub.model.Fish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;


@Repository
public interface FishRepository extends JpaRepository<Fish, Long> {

}
