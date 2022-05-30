package com.example.happyprogramming.repository;

import com.example.happyprogramming.entity.ConversationEntity;
import com.example.happyprogramming.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ConversationRepository extends JpaRepository<ConversationEntity,Long> {
    //User1: sender, User2: receiver
        ConversationEntity findByUser1AndUser2(UserEntity user1, UserEntity user2);

        ConversationEntity findById(long id);

        ArrayList<ConversationEntity> findByUser1OrUser2(UserEntity user1, UserEntity user2);
}
