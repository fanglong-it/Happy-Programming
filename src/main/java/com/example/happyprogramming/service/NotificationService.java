package com.example.happyprogramming.service;


import com.example.happyprogramming.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {

    int getUncheckedNoti(UserEntity user);

    String getNotification(UserEntity user);

    void welcomeNotification(UserEntity user);

    void menteeSendRequestNotification(UserEntity mentor, UserEntity mentee);

    void acceptedNotification(UserEntity mentor, UserEntity mentee);

    void rejectedNotification(UserEntity mentor, UserEntity mentee);

    void receivedNotification(UserEntity mentor, UserEntity mentee);

    void ratedNotification(UserEntity mentor, UserEntity mentee);
}
