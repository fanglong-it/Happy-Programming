package com.example.happyprogramming.service;


import com.example.happyprogramming.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface RateCommentService {

    String getRateComment(int mentorId, UserEntity mentee);

    void saveRateComment(int id, String comment, int starNumber);

    void enableRateAndComment(UserEntity mentor, UserEntity mentee);
}
