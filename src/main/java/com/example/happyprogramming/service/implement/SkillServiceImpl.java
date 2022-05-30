package com.example.happyprogramming.service.implement;

import com.example.happyprogramming.entity.PopularSkill;
import com.example.happyprogramming.entity.SkillEntity;
import com.example.happyprogramming.repository.SkillRepository;
import com.example.happyprogramming.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.*;


@Component
public class SkillServiceImpl implements SkillService {


    @Autowired
    SkillRepository skillRepository;

    @Override
    public ArrayList<SkillEntity> getAllSkill() {
        return skillRepository.getAllSkill();
    }


    @Override
    public SkillEntity getSkillEntityById(Long id) {
        return skillRepository.getSkillEntityById(id);
    }

    @Override
    public List<PopularSkill> getPopularSkill() {
        ArrayList<SkillEntity> listSkill = skillRepository.findAll();
        List<PopularSkill> listPopularSkill = new ArrayList<>();
        for (SkillEntity s: listSkill) {
            listPopularSkill.add(new PopularSkill(s.getId(),s.getSkillName(),s.getImg(),s.getCvEntitySet().size()));
        }
        Collections.sort(listPopularSkill);
        return listPopularSkill;
    }

    @Override
    public List<PopularSkill> getMostSoughtSkills() {
        ArrayList<SkillEntity> listSkill = skillRepository.getAllSkill();
        List<PopularSkill> listSoughtSkill = new ArrayList<>();

        for (SkillEntity s: listSkill) {
            listSoughtSkill.add(new PopularSkill(s.getId(),s.getSkillName(),s.getImg(),s.getRequestEntities().size()));
        }
        Collections.sort(listSoughtSkill);
        for (PopularSkill s: listSoughtSkill) {
            System.out.println(s.getCount()+ " yaaaaa");
        }
        return listSoughtSkill;
    }

    @Override
    public int totalSought() {
        ArrayList<SkillEntity> listSkill = skillRepository.getAllSkill();
        List<PopularSkill> listSoughtSkill = new ArrayList<>();
        int sum=0;
        for (SkillEntity s: listSkill) {
            sum += s.getRequestEntities().size();
        }
        return sum;
    }
}
