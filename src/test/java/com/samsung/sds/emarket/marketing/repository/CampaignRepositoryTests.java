package com.samsung.sds.emarket.marketing.repository;

import com.samsung.sds.emarket.marketing.repository.entity.CampaignEntity;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.TestPropertySource;

import java.time.OffsetDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@MybatisTest
@TestPropertySource(properties = { "spring.config.location=classpath:application-test.properties" })
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CampaignRepositoryTests {

	@Autowired
	private CampaignRepository campaignRepository;

	@Test
	public void test_listCampaigns() {
		List<CampaignEntity> list = campaignRepository.listCampaigns();
		assertThat(list).extracting("id", "name", "description").contains(                
				tuple(1, "campaign 1", "campaign description 1"),
				tuple(2, "campaign 2", "campaign description 2")
				);
	}

	@Test
	public void test_createCampaign() {
		String name = "test";
		String desc = "desc";
		String picUri = "/banner1.png";
		String detailPicUri = "/detail-banner1.png";

		OffsetDateTime from = OffsetDateTime.parse("2022-05-18T05:01:43+09:00");
		OffsetDateTime to = OffsetDateTime.parse("2022-06-17T05:01:43+09:00");

		CampaignEntity campaignEntity = new CampaignEntity();
		campaignEntity.setName(name);
		campaignEntity.setDescription(desc);
		campaignEntity.setPictureUri(picUri);
		campaignEntity.setDetailsUri(detailPicUri);
		campaignEntity.setFrom(from);
		campaignEntity.setTo(to);

		int result = campaignRepository.createCampaign(campaignEntity);

		assertThat(campaignEntity.getId()).isNotEqualTo(0);

		CampaignEntity actualCampaign = campaignRepository.getCampaign(campaignEntity.getId());

		assertThat(campaignEntity.getName()).isEqualTo(actualCampaign.getName());
		assertThat(campaignEntity.getDescription()).isEqualTo(actualCampaign.getDescription());
		assertThat(campaignEntity.getDetailsUri()).isEqualTo(actualCampaign.getDetailsUri());
		assertThat(campaignEntity.getFrom()).isEqualTo(actualCampaign.getFrom());
		assertThat(campaignEntity.getTo()).isEqualTo(actualCampaign.getTo());
		assertThat(campaignEntity.getPictureUri()).isEqualTo(actualCampaign.getPictureUri());
	}

	@Test
	public void test_updateCampaign() {
		String name = "test";
		String desc = "desc";
		String picUri = "/banner1.png";
		String detailPicUri = "/detail-banner1.png";

		OffsetDateTime from = OffsetDateTime.parse("2022-05-18T05:01:43+09:00");
		OffsetDateTime to = OffsetDateTime.parse("2022-06-17T05:01:43+09:00");

		CampaignEntity campaignEntity = new CampaignEntity();
		campaignEntity.setId(20);
		campaignEntity.setName(name);
		campaignEntity.setDescription(desc);
		campaignEntity.setPictureUri(picUri);
		campaignEntity.setDetailsUri(detailPicUri);
		campaignEntity.setFrom(from);
		campaignEntity.setTo(to);

		int result = campaignRepository.updateCampaign(campaignEntity);
		assertThat(result).isEqualTo(1);

		CampaignEntity actualCampaign = campaignRepository.getCampaign(campaignEntity.getId());

		assertThat(campaignEntity.getName()).isEqualTo(actualCampaign.getName());
		assertThat(campaignEntity.getDescription()).isEqualTo(actualCampaign.getDescription());
		assertThat(campaignEntity.getDetailsUri()).isEqualTo(actualCampaign.getDetailsUri());
		assertThat(campaignEntity.getFrom()).isEqualTo(actualCampaign.getFrom());
		assertThat(campaignEntity.getTo()).isEqualTo(actualCampaign.getTo());
		assertThat(campaignEntity.getPictureUri()).isEqualTo(actualCampaign.getPictureUri());
	}
}
