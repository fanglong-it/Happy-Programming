package com.example.happyprogramming.repository;

import com.example.happyprogramming.entity.CVEntity;
import com.example.happyprogramming.entity.CommentAndRateEntity;
import com.example.happyprogramming.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RateCommentRepository extends JpaRepository<CommentAndRateEntity,Long> {

    CommentAndRateEntity findByMentorAndMentee(CVEntity mentor, UserEntity mentee);

    CommentAndRateEntity findById(long id);

  //  ArrayList<CommentAndRateEntity> findByMentor(UserEntity mentor);

}
