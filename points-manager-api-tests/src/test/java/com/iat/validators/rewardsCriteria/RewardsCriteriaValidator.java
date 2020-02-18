package com.iat.validators.rewardsCriteria;

import com.iat.Config;
import com.iat.domain.RewardCriteria;
import com.iat.repository.RewardCriteriaRepository;
import com.iat.repository.TagRepository;
import com.iat.repository.impl.RewardCriteriaRepositoryImpl;
import com.iat.repository.impl.TagRepositoryImpl;
import com.iat.utils.JdbcDatabaseConnector;
import com.iat.utils.ResponseContainer;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class RewardsCriteriaValidator {

    private RewardCriteriaRepository rewardCriteriaRepository;
    private TagRepository tagRepository;
    private JdbcDatabaseConnector mySQLConnector;

    public RewardsCriteriaValidator() {
        rewardCriteriaRepository = new RewardCriteriaRepositoryImpl();
        tagRepository = new TagRepositoryImpl();
        mySQLConnector = new JdbcDatabaseConnector(Config.mysqlConnectionPool);
    }

    public void validateRewardsCriteriaBulkUploadCorrectness(List<String> rewardsCriteriaData) {
        int iterator = 0;
        String criteriaStartDate, criteriaExpiryDate, criteriaProductId, criteriaProductDescription, criteriaEpointsAmount, criteriaPurchaseType, criteriaExternalPartnerId, criteriaPartnerId;
        String pointsManagerStartDate, pointsManagerExpiryDate, pointsManagerProductId, pointsManagerProductDescription, pointsManagerEpointsAmount, pointsManagerPurchaseType, pointsManagerPartnerId;
        for (String criteria : rewardsCriteriaData) {
            String criteriaDataTemp[] = criteria.split(";");

            criteriaExternalPartnerId = criteriaDataTemp[0];
            criteriaPartnerId = mySQLConnector.getSingleResult("SELECT id FROM Partner WHERE externalId = '" + criteriaExternalPartnerId + "'");
            criteriaProductId = criteriaDataTemp[1];
            criteriaProductDescription = criteriaDataTemp[2];
            criteriaEpointsAmount = criteriaDataTemp[3];
            criteriaPurchaseType = criteriaDataTemp[4];
            criteriaStartDate = criteriaDataTemp[6];
            criteriaExpiryDate = criteriaDataTemp[7];

            //Get RewardCriteria record to validate
            RewardCriteria criteriaFromPointsManager = rewardCriteriaRepository.findRewardCriteriaByProductId(criteriaProductId);

            pointsManagerStartDate = criteriaFromPointsManager.getStartDate().replace("T00:00:00.000Z", "");
            pointsManagerExpiryDate = criteriaFromPointsManager.getExpiryDate().replace("T00:00:00.000Z", "");
            pointsManagerProductId = criteriaFromPointsManager.getProductId();
            pointsManagerProductDescription = criteriaFromPointsManager.getProductDescription();
            pointsManagerEpointsAmount = criteriaFromPointsManager.getEpointsAmount();
            pointsManagerPurchaseType = criteriaFromPointsManager.getPurchaseType();
            pointsManagerPartnerId = criteriaFromPointsManager.getPartnerId();

            System.out.println("Product [" + iterator + "] processed");

            assertThat("Incorrect criteria start date : " + pointsManagerStartDate + " saved in points_manager: RewardCriteria table for product id: " + criteriaProductId, criteriaStartDate, is(pointsManagerStartDate));
            assertThat("Incorrect criteria expiry date : " + pointsManagerExpiryDate + " saved in points_manager: RewardCriteria table for product id: " + criteriaProductId, criteriaExpiryDate, is(pointsManagerExpiryDate));
            assertThat("Incorrect criteria product id : " + pointsManagerProductId + " saved in points_manager: RewardCriteria table for product id: " + criteriaProductId, criteriaProductId, is(pointsManagerProductId));
            assertThat("Incorrect criteria product description : " + pointsManagerProductDescription + " saved in points_manager: RewardCriteria table for product id: " + criteriaProductId, criteriaProductDescription, is(pointsManagerProductDescription));
            assertThat("Incorrect criteria epoints amount : " + pointsManagerEpointsAmount + " saved in points_manager: RewardCriteria table for product id: " + criteriaProductId, criteriaEpointsAmount, is(pointsManagerEpointsAmount));
            if (criteriaPurchaseType.isEmpty()) criteriaPurchaseType = null;
            assertThat("Incorrect criteria purchase type : " + pointsManagerPurchaseType + " saved in points_manager: RewardCriteria table for product id: " + criteriaProductId, criteriaPurchaseType, is(pointsManagerPurchaseType));
            assertThat("Incorrect criteria partner id : " + pointsManagerPartnerId + " saved in points_manager: RewardCriteria table for product id: " + criteriaProductId, pointsManagerPartnerId, is(criteriaPartnerId));
            iterator++;
        }
    }

    public void validateIfNewTagsWereCreatedForRewardCriteria(List<String> rewardsCriteriaData) {
        String criteriaProductId, createdTagKey, createdTagId, pointsManagerTagDescription, createdTagDescription;
        String pointsManagerTagId;
        for (String criteria : rewardsCriteriaData) {
            String criteriaDataTemp[] = criteria.split(";");

            criteriaProductId = criteriaDataTemp[1];

            createdTagKey = "RewardCriteria_" + rewardCriteriaRepository.findRewardCriteriaByProductId(criteria.split(";")[1]).getId();
            createdTagId = tagRepository.findByTagKey(createdTagKey).getId();
            createdTagDescription = criteriaDataTemp[2];

            pointsManagerTagId = rewardCriteriaRepository.findRewardCriteriaByProductId(criteriaProductId).getTagId();
            pointsManagerTagDescription = tagRepository.findByTagKey(createdTagKey).getDescription();

            assertThat("Incorrect tag id  : " + pointsManagerTagId + " saved in points_manager: RewardCriteria table for product id: " + criteriaProductId, createdTagId, is(pointsManagerTagId));
            assertThat("Incorrect tag description  : " + pointsManagerTagDescription + " saved in points_manager: RewardCriteria table for product id : " + criteriaProductId, createdTagDescription, is(pointsManagerTagDescription));
        }

    }

    public void validateRewardsCriteriaWereRolledBack(List<String> rewardsCriteriaData) {
        for (String criteria : rewardsCriteriaData) {
            criteria = criteria.split(";")[1];
            assertThat("Criteria(criteriaId): " + criteria + " should be rolled back and not available in points manager", rewardCriteriaRepository.findRewardCriteriaByProductId(criteria).getId(), is(nullValue()));
        }
    }

    public void validatePartnersBulkUploadErrorMessage(ResponseContainer response, String error, String message, String items) {
        assertThat("Wrong error!", response.getString("error"), is(error));
        assertThat("Wrong message!", response.getString("message"), is(message));
        if (!items.equals("null"))
            assertThat("Wrong items!", response.get("items").toString(), is(items));
    }
}