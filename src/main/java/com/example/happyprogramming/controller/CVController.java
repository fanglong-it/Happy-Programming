package com.example.happyprogramming.controller;

import com.example.happyprogramming.entity.CVEntity;
import com.example.happyprogramming.entity.SkillEntity;
import com.example.happyprogramming.entity.UserEntity;
import com.example.happyprogramming.repository.SkillRepository;
import com.example.happyprogramming.service.ICVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CVController {

    private HttpSession session;
    private SkillRepository skillRepository;
    private ICVService ICVService;

    @Autowired
    public CVController(HttpSession session, SkillRepository skillRepository, com.example.happyprogramming.service.ICVService ICVService) {
        this.session = session;
        this.skillRepository = skillRepository;
        this.ICVService = ICVService;
    }

    @GetMapping("/createCV")
    public String goToCreateCV(Model model){
        List<SkillEntity> list  = skillRepository.findAll();
        model.addAttribute("listSkills", list);
        CVEntity cv = ICVService.findByUser((UserEntity) session.getAttribute("userInformation"));
        model.addAttribute("cv",cv);
        model.addAttribute("newCV",new CVEntity());
        return "client/create-CV";
    }

    @PostMapping("/createCV")
    public  String createCV(@ModelAttribute("newCV") CVEntity cvEntity) {
        ICVService.saveCV(cvEntity);
        CVEntity cv = ICVService.findByUser((UserEntity) session.getAttribute("userInformation"));
        if(cv != null){
            session.setAttribute("userInformation",cv.getUser());
            session.setAttribute("role","mentorAndMentee");
            session.setAttribute("skills",cv.getSkills());
        }
        return "redirect:/home";
    }

    @GetMapping("/403")
    public String forbiden(){
        return "client/my-account";
    }
}
