package com.samsung.sds.emarket.marketing.repository;

import com.samsung.sds.emarket.marketing.repository.entity.CampaignEntity;

import java.util.List;

public interface CampaignRepository {
	List<CampaignEntity> listCampaigns();

	CampaignEntity getCampaign(int id);

	int createCampaign(CampaignEntity campaignEntity);

	int updateCampaign(CampaignEntity campaignEntity);

	int deleteCampaign(int id);
}
