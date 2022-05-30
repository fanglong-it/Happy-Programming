package com.example.happyprogramming.controller;


import com.example.happyprogramming.entity.*;
import com.example.happyprogramming.repository.*;
import com.example.happyprogramming.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Controller
public class RequestController {

    @Autowired
    private SkillService skillService;

    @Autowired
    HttpSession session;

    @Autowired
    RequestService requestService;

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RateCommentService rateCommentService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ConversationService conversationService;


    @GetMapping("/create-request")
    public String createRequestPage(Model model){
        ArrayList<SkillEntity> listSkill = skillService.getAllSkill();
        model.addAttribute("listSkill",listSkill);
        model.addAttribute("requestForm",new RequestEntity());
        model.addAttribute("recommend",true);
        return "client/create-request";
    }

    @PostMapping("/create-request")
    public String createRequest(@ModelAttribute("requestForm") RequestEntity requestEntity,
                                @RequestParam("recommend") boolean recommend,HttpServletRequest request, Model model,
                                @RequestParam(value = "pageNumber",required = false,defaultValue = "1")int pageNumber) {
        UserEntity user =(UserEntity) session.getAttribute("userInformation");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        if(recommend){
            requestEntity.setMenteeId(user);
            requestEntity.setCreatedDate(dtf.format(now));
            Pagination<CVEntity> page = requestService.createRequestWithPagination(requestEntity,0,pageNumber);
            model.addAttribute("skillId",requestService.getSkillIdFromRequest(requestEntity));
            model.addAttribute("requestId",requestEntity.getId());
            model.addAttribute("listMentors",page.getPaginatedList());
            model.addAttribute("pageNumbers", page.getPageNumbers());
            model.addAttribute("currentPage",pageNumber);
            return "client/mentor-suggestion";
        }else{
            int mentorId= Integer.parseInt(request.getParameter("mentorId"));
            UserEntity mentor = userRepository.findById(mentorId);
            requestEntity.setMentorId(mentor);
            requestEntity.setMentorName(mentor.getFullName());
            requestEntity.setMenteeId(user);
            requestEntity.setCreatedDate(dtf.format(now));
            requestEntity.setReceived(true);
            requestService.createRequest(requestEntity,1);
            notificationService.menteeSendRequestNotification(mentor,user);
            notificationService.receivedNotification(mentor,user);
            return "redirect:/home";
        }
    }

    @GetMapping("/invited-request-wait")
    public String listWaitingRequestPage(Model model){
        UserEntity user =(UserEntity) session.getAttribute("userInformation");
        List<RequestEntity> list = requestService.findRequestEntitiesByMentorIdAndStatus(user, 1);
        List<RequestEntity> listRejected = requestService.findRequestEntitiesByMentorIdAndStatus(user, 2);
        List<RequestEntity> listApproved = requestService.findRequestEntitiesByMentorIdAndStatus(user, 3);
        int k = list.size()+listApproved.size()+listRejected.size();
        model.addAttribute("l1",(float)list.size()*100/k);
        model.addAttribute("l2", (float)listRejected.size()*100/k);
        model.addAttribute("l3", (float)listApproved.size()*100/k);
        model.addAttribute("listWaitingRequest",list);

        return "/client/waiting-requests";
    }
    @GetMapping("/invited-request-rejected")
    public String listRejectedRequestPage(Model model){
        UserEntity user =(UserEntity) session.getAttribute("userInformation");
        List<RequestEntity> list = requestService.findRequestEntitiesByMentorIdAndStatus(user, 2);
        model.addAttribute("listRejectedRequest",list);
        return "client/rejected-requests";
    }
    @GetMapping("/invited-request-approved")
    public String listApprovedRequestPage(Model model){
        UserEntity user =(UserEntity) session.getAttribute("userInformation");
        List<RequestEntity> list = requestService.findRequestEntitiesByMentorIdAndStatus(user, 3);
        model.addAttribute("listApprovedRequest",list);
        return "client/approved-requests";
    }
    @PostMapping("/reject")
    public String rejectRequest(HttpServletRequest request){
        Long id = Long.parseLong(request.getParameter("id"));
        String response = request.getParameter("response");
        Optional<RequestEntity> re = requestService.findById(id);
        if (re.isPresent()){
            RequestEntity req = re.get();
            req.setStatus(2);
            req.setResponseMess(response);
            requestRepository.save(req);
            notificationService.rejectedNotification(req.getMentorId(),req.getMenteeId());
        }
        return "redirect:/invited-request-wait";
    }
    @PostMapping("/approve")
    public String approveRequest(HttpServletRequest request){
        Long id = Long.parseLong(request.getParameter("id"));
        String response = request.getParameter("response");
        Optional<RequestEntity> re = requestService.findById(id);
        if (re.isPresent()){
            RequestEntity req = re.get();
            req.setResponseMess(response);
            req.setStatus(3);
            conversationService.createConversation(req.getMentorId(),req.getMenteeId());
            rateCommentService.enableRateAndComment(req.getMentorId(),req.getMenteeId());
            requestRepository.save(req);
            notificationService.acceptedNotification(req.getMentorId(),req.getMenteeId());
        }
        return "redirect:/invited-request-wait";
    }
    @GetMapping("/request-detail")
    public  String goToDetail(Model model, @RequestParam("id") long id){
       RequestEntity re= requestRepository.findById(id);
        model.addAttribute("requestDetail", re);
        return "client/request-detail";
    }
    @GetMapping("/list-requests")
    public String listRequest(Model model, @RequestParam ("status") int status,
                              @RequestParam(value = "pageNumber",required = false,defaultValue = "1")int pageNumber){
        UserEntity user = (UserEntity) session.getAttribute("userInformation");
        Pagination<RequestEntity> listRequest = requestService.findByStatus(user,status,pageNumber);
        model.addAttribute("listRequests", listRequest.getPaginatedList());
        model.addAttribute("pageNumbers",listRequest.getPageNumbers());
        model.addAttribute("status",status);
        model.addAttribute("currentPage",pageNumber);
        return "client/list-requests";
    }
    @GetMapping("/update-request")
    public  String updateRequest(@RequestParam("id") long id, Model model){
        RequestEntity request = requestRepository.getById(id);
        model.addAttribute("request", request);
        model.addAttribute("requestForm",new RequestEntity());
        return "client/update-request";
    }
    @PostMapping("/update-request")
    public  String doUpdate(@ModelAttribute("requestForm") RequestEntity requestEntity, HttpServletRequest request){
        long requestId = Integer.parseInt(request.getParameter("requestId"));
        requestEntity.setId(requestId);
        requestService.updateRequest(requestEntity);
        return "redirect:/list-requests?status=0";
    }

    @GetMapping("/cancel-request")
    public String cancelRequest(@RequestParam("id") Long id){
        requestService.cancelRequest(id);
        return "redirect:/list-requests?status=1";
    }

    @GetMapping("/manage-requests")
    public String manageRequests(Model model,
                                 @RequestParam(value = "pageNumber",required = false,defaultValue = "1")int pageNumber) {
        UserEntity user = (UserEntity) session.getAttribute("userInformation");
        ArrayList<RequestEntity> ManageRequests = requestRepository.findAll();
        model.addAttribute("manageRequests", ManageRequests);
        return "admin/manage-requests";
    }
}
