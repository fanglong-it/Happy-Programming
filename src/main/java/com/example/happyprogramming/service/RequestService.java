package com.example.happyprogramming.service;


import com.example.happyprogramming.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RequestService {
    void createRequest(RequestEntity requestEntity, int status);
    List<RequestEntity> findRequestEntitiesByMentorIdAndStatus(UserEntity id, int status);
    Optional<RequestEntity> findById(Long id);
    Pagination<RequestEntity> findByStatus(UserEntity mentee,int status, int pageNumber);
    void updateRequest(RequestEntity request);
    void cancelRequest(Long id);
    Pagination<CVEntity> createRequestWithPagination(RequestEntity requestEntity, int status,int pageNumber);
    Long getSkillIdFromRequest(RequestEntity request);
    List<TotalRequestMonthly> totalRequestMonthly();
    Double rateOfSuccessful();
}
