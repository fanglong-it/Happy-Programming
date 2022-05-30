package com.example.happyprogramming.service;


import com.example.happyprogramming.entity.PopularSkill;
import com.example.happyprogramming.entity.SkillEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface SkillService {
    SkillEntity getSkillEntityById(Long id);
    ArrayList<SkillEntity> getAllSkill();
    List<PopularSkill> getPopularSkill();
    List<PopularSkill> getMostSoughtSkills();
    int totalSought();//tong tat ca so skills trong tat ca cac requests
}
