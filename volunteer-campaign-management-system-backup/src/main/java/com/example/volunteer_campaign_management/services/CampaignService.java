package com.example.volunteer_campaign_management.services;

import com.example.volunteer_campaign_management.dtos.CampaignDTO;

import java.util.List;
import java.util.Optional;

public interface CampaignService {
    CampaignDTO createNewCampaign(CampaignDTO campaignDTO);

    CampaignDTO updateCampaignStatus(CampaignDTO campaignDTO);

    List<CampaignDTO> getAllCampaigns();
    List<CampaignDTO> searchCampaign(Optional<String> query);

    CampaignDTO getCampaignById(int campaignId);
    CampaignDTO updateCampaign(int campaignId, CampaignDTO campaignDTO);
}
