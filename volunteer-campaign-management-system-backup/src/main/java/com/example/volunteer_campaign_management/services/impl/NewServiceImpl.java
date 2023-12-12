package com.example.volunteer_campaign_management.services.impl;

import com.example.volunteer_campaign_management.dtos.NewDTO;
import com.example.volunteer_campaign_management.entities.CampaignEntity;
import com.example.volunteer_campaign_management.entities.CurrentStatusEntity;
import com.example.volunteer_campaign_management.entities.MilestoneEntity;
import com.example.volunteer_campaign_management.entities.NewEntity;
import com.example.volunteer_campaign_management.mappers.MapperUtil;
import com.example.volunteer_campaign_management.repositories.NewRepository;
import com.example.volunteer_campaign_management.services.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewServiceImpl implements NewService {

    private final NewRepository newRepository;
    private final MapperUtil mapperUtil;

    @Autowired
    public NewServiceImpl(NewRepository newRepository, MapperUtil mapperUtil) {
        this.newRepository = newRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public NewDTO createNew(NewDTO newDTO) {
        try {

            NewEntity newEntity = new NewEntity();
            newEntity.setNewId(newDTO.getNewId());
            newEntity.setContent(newDTO.getContent());
            newEntity.setTitle(newDTO.getTitle());
            newEntity.setCreated_at(newDTO.getCreated_at());
            newEntity.setImage(newDTO.getImage());
            newRepository.save(newEntity);

            return newDTO;

        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public NewDTO updateNew(int newId, NewDTO newDTO) {
        Optional<NewEntity> optionalNewEntity = newRepository.findById(newId);
        if (optionalNewEntity.isPresent()) {
            NewEntity existingNewEntity = optionalNewEntity.get();
            existingNewEntity.setTitle(newDTO.getTitle());
            existingNewEntity.setContent(newDTO.getContent());
            existingNewEntity.setCreated_at(newDTO.getCreated_at());

            existingNewEntity = newRepository.save(existingNewEntity);

            NewDTO updatedNewDTO = new NewDTO();
            updatedNewDTO.setNewId(existingNewEntity.getNewId());
            updatedNewDTO.setTitle(existingNewEntity.getTitle());
            updatedNewDTO.setContent(existingNewEntity.getContent());
            updatedNewDTO.setCreated_at(existingNewEntity.getCreated_at());
            updatedNewDTO.setImage(existingNewEntity.getImage());

            return updatedNewDTO;
        } else {
            // Handle the case where the NewEntity with the specified ID is not found
            return null;
        }
    }

    @Override
    public boolean deleteNew(int newId) {
        try {
            newRepository.deleteById(newId);
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    @Override
    public List<NewDTO> getAllNews() {
        List<NewEntity> newEntities = newRepository.findAll();
        return newEntities.stream()
                .map(entity -> {
                    NewDTO newDTO = new NewDTO();
                    newDTO.setNewId(entity.getNewId());
                    newDTO.setTitle(entity.getTitle());
                    newDTO.setContent(entity.getContent());
                    newDTO.setCreated_at(entity.getCreated_at());
                    newDTO.setImage(entity.getImage());
                    return newDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public NewDTO getNewById(int newId) {
        Optional<NewEntity> optionalNewEntity = newRepository.findById(newId);
        if (optionalNewEntity.isPresent()) {
            NewEntity newEntity = optionalNewEntity.get();
            NewDTO newDTO = new NewDTO();
            newDTO.setNewId(newEntity.getNewId());
            newDTO.setTitle(newEntity.getTitle());
            newDTO.setContent(newEntity.getContent());
            newDTO.setCreated_at(newEntity.getCreated_at());
            newDTO.setImage(newEntity.getImage());

            return newDTO;
        } else {
            // Handle the case where the NewEntity with the specified ID is not found
            return null;
        }
    }

    @Override
    public List<NewDTO> searchNews(Optional<String> keyword) {
        if (!keyword.isPresent()) {
            return getAllNews();
        }
        List<NewEntity> searchedNews = newRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword);
        return searchedNews.stream()
                .map(entity -> {
                    NewDTO newDTO = new NewDTO();
                    newDTO.setNewId(entity.getNewId());
                    newDTO.setTitle(entity.getTitle());
                    newDTO.setContent(entity.getContent());
                    newDTO.setCreated_at(entity.getCreated_at());
                    return newDTO;
                })
                .collect(Collectors.toList());
    }
}
