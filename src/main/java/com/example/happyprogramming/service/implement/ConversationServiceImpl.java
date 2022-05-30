package com.example.happyprogramming.service.implement;

import com.example.happyprogramming.entity.ConversationEntity;
import com.example.happyprogramming.entity.ConversationReplyEntity;
import com.example.happyprogramming.entity.UserEntity;
import com.example.happyprogramming.repository.ConversationReplyRepository;
import com.example.happyprogramming.repository.ConversationRepository;
import com.example.happyprogramming.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


@Component
public class ConversationServiceImpl implements ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private ConversationReplyRepository conversationReplyRepository;


    @Override
    public void saveConversation(int conversationId,UserEntity sender, String context) {
        ConversationEntity conversation = conversationRepository.findById(conversationId);
        ConversationReplyEntity reply = new ConversationReplyEntity();
        Set<ConversationReplyEntity> replyList = new HashSet<>();
        reply.setContext(context);
        reply.setConversationId(conversation);
        reply.setReplyUser(sender);
        reply.setStatus(0);
        replyList.add(reply);
        conversation.setConversationReply(replyList);
        conversationReplyRepository.save(reply);
        conversationRepository.save(conversation);
    }

    @Override
    public int createConversation(UserEntity user1, UserEntity user2) {
        ConversationEntity conversation = new ConversationEntity();
        conversation.setUser1(user1);
        conversation.setUser2(user2);
        conversationRepository.save(conversation);
        return conversation.getId().intValue();
    }

    @Override
    public int checkConversationExist(UserEntity user1,UserEntity user2) {
        ConversationEntity conversation;
        if(conversationRepository.findByUser1AndUser2(user1,user2)!=null){
            conversation = conversationRepository.findByUser1AndUser2(user1,user2);
            return conversation.getId().intValue();
        }else if(conversationRepository.findByUser1AndUser2(user2,user1)!=null){
            conversation = conversationRepository.findByUser1AndUser2(user2,user1);
            return conversation.getId().intValue();
        }else{
            return -1;
        }
    }

    @Override
    public ArrayList<ConversationReplyEntity> getConversation(long conversationId) {
        ConversationEntity conversation = conversationRepository.findById(conversationId);
        return conversationReplyRepository.findByConversationId(conversation);
    }

    @Override
    public ArrayList<ConversationEntity> getUserInConversation(UserEntity user1, UserEntity user2) {
        ArrayList<ConversationEntity> list = conversationRepository.findByUser1OrUser2(user1,user2);
        return list;
    }
}
