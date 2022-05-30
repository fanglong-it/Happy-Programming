package com.example.happyprogramming.service.implement;

import com.example.happyprogramming.entity.CVEntity;
import com.example.happyprogramming.entity.Pagination;
import com.example.happyprogramming.entity.RequestEntity;
import com.example.happyprogramming.entity.SkillEntity;
import com.example.happyprogramming.repository.CVRepository;
import com.example.happyprogramming.repository.RequestRepository;
import com.example.happyprogramming.repository.SkillRepository;
import com.example.happyprogramming.repository.UserRepository;
import com.example.happyprogramming.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class MentorServiceImpl implements MentorService {

    @Autowired
    private CVRepository cvRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public ArrayList<CVEntity> getAllMentor() {
        return cvRepository.findAll();
    }

    @Override
    public CVEntity findMentorById(long id) {
        return cvRepository.findByUser(userRepository.findById(id));
    }

    @Override
    public Pagination<CVEntity> getPaginatedMentors(int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber-1,10);
        Page<CVEntity> page = cvRepository.findAll(pageRequest);
        int totalPages = page.getTotalPages();
        List<CVEntity> cvList = page.getContent();
        List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
        Pagination<CVEntity> result = new Pagination<>(cvList,pageNumbers);
        return result;
    }

    @Override
    public Pagination<CVEntity> findMentorBySkill(Long skillId, int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber-1,15);
        SkillEntity skill = skillRepository.getSkillEntityById(skillId);
        Page<CVEntity> page = cvRepository.findCVEntityBySkills(pageRequest,skill);
        int totalPages = page.getTotalPages();
        List<CVEntity> cvList = page.getContent();
        List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
        Pagination<CVEntity> result = new Pagination<>(cvList,pageNumbers);
        return result;
    }

    @Override
    public void hireMentor(long mentorId, long requestId) {
        RequestEntity request = requestRepository.findById(requestId);
        request.setMentorId(userRepository.findById(mentorId));
        request.setStatus(1);
        requestRepository.save(request);
    }

}
