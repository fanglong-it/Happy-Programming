package com.example.happyprogramming.controller;


import com.example.happyprogramming.entity.ConversationReplyEntity;
import com.example.happyprogramming.entity.UserEntity;
import com.example.happyprogramming.repository.UserRepository;
import com.example.happyprogramming.service.ConversationService;
import com.example.happyprogramming.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class ConversationController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConversationService conversationService;

    @MessageMapping("/chat/{id}/{conversationId}/{senderId}/{mess}")
    public void sendMessage(@DestinationVariable long id,@DestinationVariable int conversationId,@DestinationVariable long senderId,@DestinationVariable String mess) {
        ConversationReplyEntity c = new ConversationReplyEntity();
        c.setContext(mess);
        UserEntity sender = userRepository.findById(senderId);
        conversationService.saveConversation(conversationId,sender,mess);
        simpMessagingTemplate.convertAndSend("/topic/messages/"+id, c);
    }


    @GetMapping("/contact")
    public String openContact(@RequestParam("id")long id, Model model){
        UserEntity receiver = userRepository.findById(id);
        UserEntity sender = (UserEntity) session.getAttribute("userInformation");
        int conversationId = conversationService.checkConversationExist(sender,receiver);
        if(conversationId==-1){
            return "client/404-error";
        }else{
            model.addAttribute("userInConversation",conversationService.getUserInConversation(sender,sender));
            model.addAttribute("conversationMessage",conversationService.getConversation(conversationId));
            model.addAttribute("conversationId",conversationId);
            model.addAttribute("receiver",receiver);
            return "client/box-chat";
        }

    }
}
