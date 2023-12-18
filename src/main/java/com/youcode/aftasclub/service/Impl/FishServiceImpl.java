package com.youcode.aftasclub.service.Impl;

import com.youcode.aftasclub.model.Fish;
import com.youcode.aftasclub.repository.FishRepository;
import com.youcode.aftasclub.service.FishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class FishServiceImpl implements FishService {
//    public Fish saveFish(Fish fish) {
//
//    }
    @Autowired
    private FishRepository fishRepository;



    @Override
    public Fish getFishById(Long id) {
        Fish fish = fishRepository.findById(id).orElse(null);
        return fish;
    }
}
