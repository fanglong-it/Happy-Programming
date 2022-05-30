package com.example.happyprogramming.controller;

import com.example.happyprogramming.entity.PopularSkill;
import com.example.happyprogramming.entity.TotalRequestMonthly;
import com.example.happyprogramming.repository.RequestRepository;
import com.example.happyprogramming.service.RequestService;
import com.example.happyprogramming.service.SkillService;
import com.example.happyprogramming.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private SkillService skillService;

    @Autowired
    private UserService userService;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private RequestService requestService;
    @GetMapping("/adminpage")
    public String goToAdmin(Model model){
        List<PopularSkill> li = skillService.getMostSoughtSkills();
        model.addAttribute("sk0", li.get(0));
        model.addAttribute("sk1", li.get(1));
        model.addAttribute("sk2", li.get(2));
        model.addAttribute("otherCount", skillService.totalSought()-li.get(0).getCount()
                -li.get(1).getCount()
                -li.get(2).getCount());
        model.addAttribute("totalUsers", userService.totalUsers());
        model.addAttribute("totalRequests", requestRepository.findAll().size());

        List<TotalRequestMonthly> list =  requestService.totalRequestMonthly();
        model.addAttribute("monthlyRequest", list);
        model.addAttribute("rateOfSuccessful", requestService.rateOfSuccessful());
        model.addAttribute("rateOfSuccessfulFormatted", String.format("%.02f", requestService.rateOfSuccessful()).concat("%") );

        return "admin/index";
    }


}
