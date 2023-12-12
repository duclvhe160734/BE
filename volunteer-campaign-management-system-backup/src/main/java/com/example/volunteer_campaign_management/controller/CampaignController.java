package com.example.volunteer_campaign_management.controller;

import com.example.volunteer_campaign_management.dtos.CampaignDTO;
import com.example.volunteer_campaign_management.dtos.RequestVolunteerDTO;
import com.example.volunteer_campaign_management.services.CampaignService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/volunteer-campaign-management/api/v1")
@AllArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;

    @PostMapping("/createCampaign")
    public CampaignDTO createNewCampaign(@RequestBody CampaignDTO campaignDTO) {
        return campaignService.createNewCampaign(campaignDTO);
    }

    @PutMapping("/updateCampaign/{id}")
    public CampaignDTO updateCampaign(@PathVariable(value = "id") int campaignId, @RequestBody CampaignDTO campaignDTO) {
        return campaignService.updateCampaign(campaignId, campaignDTO);
    }

    @GetMapping("/campaign/{id}")
    public CampaignDTO getCampaignById(@PathVariable(value = "id") int campaignId) {
        return campaignService.getCampaignById(campaignId);
    }

    @PutMapping("/campaign/updateCampaignStatus")
    public CampaignDTO updateCampaignStatus(@RequestBody CampaignDTO campaignDTO) {

        return campaignService.updateCampaignStatus(campaignDTO);
    }

    @GetMapping("/campaigns")
    public java.util.List<CampaignDTO> getAllCampaigns() {
        return campaignService.getAllCampaigns();
    }

    @GetMapping (value = {"campaign/searchCampaign","campaign/searchCampaign/{query}"})
    public java.util.List<CampaignDTO> searchCampaign(@PathVariable(value = "query") Optional<String> query){
        return campaignService.searchCampaign(query);
    }

}
