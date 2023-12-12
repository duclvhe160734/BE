package com.example.volunteer_campaign_management.controller;

import com.example.volunteer_campaign_management.dtos.MilestoneDTO;
import com.example.volunteer_campaign_management.dtos.NewDTO;
import com.example.volunteer_campaign_management.services.NewService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/volunteer-campaign-management/api/v1")
@AllArgsConstructor
public class NewController {

    @Autowired
    private NewService newService;

    @PostMapping("/news/create")
    public NewDTO createNew(@RequestBody NewDTO newDTO) {
        return newService.createNew(newDTO);
    }

    @PutMapping("/news/update/{newId}")
    public NewDTO updateNew(@PathVariable int newId, @RequestBody NewDTO newDTO) {
        return newService.updateNew(newId, newDTO);
    }

    @DeleteMapping("/news/delete/{newId}")
    public boolean deleteNew(@PathVariable int newId) {
        return newService.deleteNew(newId);
    }

    @GetMapping("/news/list")
    public List<NewDTO> getAllNews() {
        return newService.getAllNews();
    }

    @GetMapping("/news/getById/{newId}")
    public NewDTO getNewById(@PathVariable int newId) {
        return newService.getNewById(newId);
    }
    @GetMapping (value = {"/news/search","/news/search/{query}"})
    public List<NewDTO> searchNews(@PathVariable(value = "query") Optional<String> query){
        return  newService.searchNews(query);
    }
}