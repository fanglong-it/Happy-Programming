package com.example.happyprogramming.repository;

import com.example.happyprogramming.entity.ConversationEntity;
import com.example.happyprogramming.entity.ConversationReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface ConversationReplyRepository extends JpaRepository<ConversationReplyEntity,Long> {

    ArrayList<ConversationReplyEntity> findByConversationId(ConversationEntity conversation);
}
