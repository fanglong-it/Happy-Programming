package com.example.happyprogramming.repository;

import com.example.happyprogramming.entity.RequestEntity;
import com.example.happyprogramming.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public interface RequestRepository  extends JpaRepository<RequestEntity,Long> {
    List<RequestEntity> findRequestEntitiesByMentorIdAndStatus(UserEntity mentorId, int status);
    Optional<RequestEntity> findById(Long id);
    Page<RequestEntity> findByStatusAndAndMenteeId(Pageable pageable, int status,UserEntity mentee);
    RequestEntity findById(long id);
    ArrayList<RequestEntity> findAll();

    @Query(value = "select *from request where substring(created_date, 7,4)= ?1", nativeQuery = true)
    List<RequestEntity> findByYear(int year);
}
