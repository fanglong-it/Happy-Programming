package com.example.happyprogramming.service;


import com.example.happyprogramming.entity.ConversationEntity;
import com.example.happyprogramming.entity.ConversationReplyEntity;
import com.example.happyprogramming.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface ConversationService {
    void saveConversation(int conversationId,UserEntity sender,String context);

    int createConversation(UserEntity user1, UserEntity user2);

    int checkConversationExist(UserEntity user1, UserEntity user2);

    ArrayList<ConversationReplyEntity> getConversation(long conversationId);

    ArrayList<ConversationEntity> getUserInConversation(UserEntity user1, UserEntity user2);


}
