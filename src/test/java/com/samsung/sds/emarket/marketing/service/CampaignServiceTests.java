package com.samsung.sds.emarket.marketing.service;


import com.samsung.sds.emarket.marketing.repository.CampaignRepository;
import com.samsung.sds.emarket.marketing.repository.entity.CampaignEntity;
import com.samsung.sds.emarket.marketing.service.vo.CampaignVO;
import com.samsung.sds.emarket.marketing.service.vo.NewCampaignVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CampaignServiceTests {

	@Mock
	private CampaignRepository campaignRepository;

	@Test
	public void test_listCampaigns() {

		List<CampaignEntity> result = new ArrayList<>();
		CampaignEntity campaign = new CampaignEntity();
		campaign.setId(3);
		campaign.setName("test name 3");
		result.add(campaign);

		campaign = new CampaignEntity();
		campaign.setId(4);
		campaign.setName("test name 4");
		result.add(campaign);

		when(campaignRepository.listCampaigns()).thenReturn(result);    

		CampaignService campaignService = new CampaignServiceImpl(campaignRepository, new VOMapperImpl());

		List<CampaignVO> list = campaignService.listCampaigns();
		assertThat(list).extracting("id", "name").contains(
				tuple(3, "test name 3"),
				tuple(4, "test name 4")
				);
	}

	@Test
	public void test_createCampaign() {
		String name = "test name";
		OffsetDateTime from = OffsetDateTime.parse("2022-05-18T05:01:43+09:00");
		OffsetDateTime to = OffsetDateTime.parse("2022-06-17T05:01:43+09:00");

		NewCampaignVO newCampaignVO = new NewCampaignVO();
		newCampaignVO.setName(name);
		newCampaignVO.setDescription("desc");
		newCampaignVO.setPictureUri("pictureUri");
		newCampaignVO.setDetailsUri("detailUri");
		newCampaignVO.setFrom(from);
		newCampaignVO.setTo(to);

		when(campaignRepository.createCampaign(any(CampaignEntity.class)))
				.thenAnswer(
						(InvocationOnMock invocation) -> {
							((CampaignEntity)invocation.getArguments()[0]).setId(100);
							return 1;
						});

		CampaignService campaignService = new CampaignServiceImpl(campaignRepository, new VOMapperImpl());

		CampaignVO result = campaignService.createCampaign(newCampaignVO);
		assertThat(result)
				.hasFieldOrPropertyWithValue("id", 100)
				.hasFieldOrPropertyWithValue("name", name)
		;
	}

	@Test
	public void test_updateCampaign() {
		String name = "test name";
		OffsetDateTime from = OffsetDateTime.parse("2022-05-18T05:01:43+09:00");
		OffsetDateTime to = OffsetDateTime.parse("2022-06-17T05:01:43+09:00");

		CampaignVO campaignVO = new CampaignVO();
		campaignVO.setId(300);
		campaignVO.setName(name);
		campaignVO.setDescription("desc");
		campaignVO.setPictureUri("pictureUri");
		campaignVO.setDetailsUri("detailUri");
		campaignVO.setFrom(from);
		campaignVO.setTo(to);

		when(campaignRepository.getCampaign(300)).thenReturn(new CampaignEntity());

		CampaignService campaignService = new CampaignServiceImpl(campaignRepository, new VOMapperImpl());

		CampaignVO result = campaignService.updateCampaign(campaignVO);
		assertThat(result)
				.hasFieldOrPropertyWithValue("id", 300)
				.hasFieldOrPropertyWithValue("name", name)
		;
	}

	@Test
	public void test_updateCampaign_not_found() {
		String name = "test name";
		OffsetDateTime from = OffsetDateTime.parse("2022-05-18T05:01:43+09:00");
		OffsetDateTime to = OffsetDateTime.parse("2022-06-17T05:01:43+09:00");

		CampaignVO campaignVO = new CampaignVO();
		campaignVO.setId(400);
		campaignVO.setName(name);
		campaignVO.setDescription("desc");
		campaignVO.setPictureUri("pictureUri");
		campaignVO.setDetailsUri("detailUri");
		campaignVO.setFrom(from);
		campaignVO.setTo(to);

		CampaignService campaignService = new CampaignServiceImpl(campaignRepository, new VOMapperImpl());

		CampaignVO result = campaignService.updateCampaign(campaignVO);
		assertThat(result).isNull();
	}
}
