package com.example.happyprogramming.controller;


import com.example.happyprogramming.entity.UserEntity;
import com.example.happyprogramming.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private HttpSession session;

    @GetMapping("/get-uncheck-notification")
    public void getNotification(HttpServletResponse response) throws IOException {
        UserEntity user = (UserEntity) session.getAttribute("userInformation");
        response.getWriter().print(notificationService.getUncheckedNoti(user));
    }
    @GetMapping("/get-notification-content")
    public void getNotificationContent(HttpServletResponse response)throws IOException{
        UserEntity user = (UserEntity) session.getAttribute("userInformation");
        response.getWriter().print(notificationService.getNotification(user));
    }
}
