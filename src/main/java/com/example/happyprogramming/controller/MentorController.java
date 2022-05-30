package com.example.happyprogramming.controller;


import com.example.happyprogramming.entity.CVEntity;
import com.example.happyprogramming.entity.Pagination;
import com.example.happyprogramming.entity.RequestEntity;
import com.example.happyprogramming.entity.SkillEntity;
import com.example.happyprogramming.repository.RequestRepository;
import com.example.happyprogramming.service.MentorService;
import com.example.happyprogramming.service.RequestService;
import com.example.happyprogramming.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class MentorController {

    @Autowired
    private MentorService mentorService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private RequestService requestService;

    @GetMapping("/mentor-detail")
    public String mentorDetail(@RequestParam("id") long mentorId,@RequestParam(value = "recommend",required = false) boolean recommend,
                               @RequestParam(value = "requestId",required = false) Long requestId,Model model){
        model.addAttribute("recommend", recommend);
        model.addAttribute("mentor",mentorService.findMentorById(mentorId));
        ArrayList<SkillEntity> listSkill = skillService.getAllSkill();
        model.addAttribute("listSkill",listSkill);
        model.addAttribute("requestForm",new RequestEntity());
        model.addAttribute("mentorId",mentorId);
        if(requestId!=null){
            model.addAttribute("requestId",requestId);
        }
        return "client/mentor-detail";
    }

    @GetMapping("/mentor/search-by-skill")
    public String searchMentorBySkill(@RequestParam(value = "id",required = false) Long skillId,Model model,@RequestParam(value = "pageNumber",required = false,defaultValue = "1")int pageNumber){
        Pagination<CVEntity> page = mentorService.findMentorBySkill(skillId,pageNumber);
        model.addAttribute("listMentors",page.getPaginatedList());
        model.addAttribute("pageNumbers", page.getPageNumbers());
        model.addAttribute("currentPage",pageNumber);
        model.addAttribute("listSkills",skillService.getPopularSkill());
        model.addAttribute("skillId",skillId);
        return "client/search-by-skill";
    }

    @GetMapping("/mentor/suggestion")
    public String mentorSuggestion(@RequestParam(value = "id",required = false) Long skillId,Model model,@RequestParam(value = "pageNumber",required = false,defaultValue = "1")int pageNumber,
                                   @RequestParam("requestId")long requestId){
        if(skillId==null){
            RequestEntity request = requestRepository.findById(requestId);
            skillId = requestService.getSkillIdFromRequest(request);
        }
        Pagination<CVEntity> page = mentorService.findMentorBySkill(skillId,pageNumber);
        model.addAttribute("listMentors",page.getPaginatedList());
        model.addAttribute("pageNumbers", page.getPageNumbers());
        model.addAttribute("currentPage",pageNumber);
        model.addAttribute("skillId",skillId);
        model.addAttribute("requestId",requestId);
        return "client/mentor-suggestion";
    }

    @GetMapping("/mentor/hire-mentor")
    public String hireMentor(@RequestParam("mentorId") long mentorId,@RequestParam("requestId") long requestId){
        mentorService.hireMentor(mentorId,requestId);
        return "redirect:/home";
    }


}
