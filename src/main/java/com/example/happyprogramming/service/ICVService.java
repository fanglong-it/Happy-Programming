package com.example.happyprogramming.service;


import com.example.happyprogramming.entity.CVEntity;
import com.example.happyprogramming.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface ICVService {
    CVEntity findByUser(UserEntity user);

    void saveCV(CVEntity cv);
}
