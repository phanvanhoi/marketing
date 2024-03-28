package com.samsung.sds.emarket.marketing.service;

import com.samsung.sds.emarket.marketing.service.vo.CampaignVO;
import com.samsung.sds.emarket.marketing.service.vo.NewCampaignVO;

import java.util.List;

public interface CampaignService {
	List<CampaignVO> listCampaigns();
	CampaignVO getCampaign(Integer id);
	CampaignVO createCampaign(NewCampaignVO newCampaignVO);

	/**
	 *
	 * @param campaignVO
	 * @return CampaignVO or null if id does not exists
	 */
	CampaignVO updateCampaign(CampaignVO campaignVO);

	boolean deleteCampaign(int id);
}
