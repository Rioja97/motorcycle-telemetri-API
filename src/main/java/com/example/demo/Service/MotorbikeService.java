package com.example.demo.Service;

import com.example.demo.Model.Motorbike;
import com.example.demo.Repository.MotorbikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MotorbikeService {

    private final MotorbikeRepository motorbikeRepository;

    public List<Motorbike> getAllMotorbikes(){
        return motorbikeRepository.findAll();
    }

    public Motorbike save(Motorbike motorbike){
        return motorbikeRepository.save(motorbike);
    }
}
