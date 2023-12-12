package com.example.volunteer_campaign_management.services;

import com.example.volunteer_campaign_management.dtos.MilestoneDTO;
import com.example.volunteer_campaign_management.dtos.NewDTO;

import java.util.List;
import java.util.Optional;

public interface NewService {
    NewDTO createNew(NewDTO newDTO);
    NewDTO updateNew(int newId, NewDTO newDTO);
    boolean deleteNew(int newId);
    List<NewDTO> getAllNews();
    NewDTO getNewById(int newId);
    List<NewDTO> searchNews(Optional<String> query);

}
