package com.example.happyprogramming.service;

import com.example.happyprogramming.entity.CVEntity;
import com.example.happyprogramming.entity.Pagination;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface MentorService {

    ArrayList<CVEntity> getAllMentor();

    CVEntity findMentorById(long id);

    Pagination<CVEntity> getPaginatedMentors(int pageNumber);

    Pagination<CVEntity> findMentorBySkill(Long skillId, int pageNumber);

    void hireMentor(long mentorId,long requestId);
}
