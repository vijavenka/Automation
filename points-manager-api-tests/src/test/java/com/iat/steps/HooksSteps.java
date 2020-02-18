package com.iat.steps;

import com.iat.Config;
import com.iat.actions.partnersManagement.CreatePartnerActions;
import com.iat.repository.AccountRepository;
import com.iat.repository.PartnerRepository;
import com.iat.repository.RewardCriteriaRepository;
import com.iat.repository.TagRepository;
import com.iat.repository.impl.AccountRepositoryImpl;
import com.iat.repository.impl.PartnerRepositoryImpl;
import com.iat.repository.impl.RewardCriteriaRepositoryImpl;
import com.iat.repository.impl.TagRepositoryImpl;
import com.iat.utils.DataExchanger;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.util.ArrayList;
import java.util.List;

public class HooksSteps {

    private PartnerRepository partnerRepository = new PartnerRepositoryImpl();
    private CreatePartnerActions createPartnerActions = new CreatePartnerActions();
    private TagRepository tagRepository = new TagRepositoryImpl();
    private RewardCriteriaRepository rewardCriteriaRepository = new RewardCriteriaRepositoryImpl();
    private AccountRepository accountRepository = new AccountRepositoryImpl();
    private DataExchanger dataExchanger = DataExchanger.getInstance();

    private List<String> rewardCriteriaIds = new ArrayList<>();
    private boolean criteriaRemoved = false;

    @Before("@createUnitedPartnersSetBeforeTest")
    public void createUnitedPartnersSetBeforeTest() throws Throwable {
        createPartnerActions.bulkUploadPartners("UnitedSuppliers", "1ULrpMzCxRq3q-DEKX3xXHavI8mdvVcc9bTzkDEKxGs4", 200);
    }

    @After("@deleteAllExternalSupplierDataAfterTest")
    public void deleteAllExternalSupplierData() throws Throwable {
        partnerRepository.deleteExternalPartnerAndRelations("SupplierExternalId_sup_1");
    }

    @Before("@resetUnitedAccountBalanceBeforeTest")
    public void resetUnitedAccountBalanceBeforeTest() {
        System.out.println("RESETTING ACCOUNT BALANCE");
        accountRepository.resetAccount(Config.UNITED_TEST_AUTO_POINTS_USER_ID, "UNITED");
    }

    @After("@deleteRewardsCriteriaDataAfterTest")
    public void deleteRewardsCriteriaDataAfterTest() throws Throwable {
        deleteCreatedRewardsCriteriaAfterTest();
        deleteCreatedTagsAfterTest();
        deleteCreatedPartnersAfterTest();
    }

    @After("@deleteCreatedPartnersAfterTest")
    public void deleteCreatedPartnersAfterTest() {
        System.out.println("REMOVING PARTNERS");
        for (String partner : Config.getBulkUploadPartnersData())
            partnerRepository.deletePartnerByShortname(partner.split(";")[1]);

        //Delete partners created for rewardsCriteriaTests
        for (String partner : Config.getBulkUploadRewardsCriteriaPartnersData())
            partnerRepository.deletePartnerByShortname(partner.split(";")[1]);

        //Additional partners which may be added during supplier bulk upload tests if errors occurs
        String[] partnersDataShortNames = {"BulkPartnerThree", "BulkPartnerFour"};
        for (String partner : partnersDataShortNames)
            partnerRepository.deletePartnerByShortname(partner);
    }

    @After("@deleteCreatedTagsAfterTest")
    public void deleteCreatedTagsAfterTest() {
        System.out.println("REMOVING TAGS");

        if (!criteriaRemoved)
            for (String criteria : Config.getBulkUploadRewardsCriteriaData())
                rewardCriteriaIds.add(rewardCriteriaRepository.findRewardCriteriaByProductId(criteria.split(";")[1]).getId());

        for (String rewardCriteriaId : rewardCriteriaIds)
            tagRepository.deleteTagByTagKey("RewardCriteria_" + rewardCriteriaId);
    }

    @After("@deleteCreatedRewardsCriteriaAfterTest")
    public void deleteCreatedRewardsCriteriaAfterTest() {
        System.out.println("REMOVING REWARDS CRITERIA");

        for (String criteria : Config.getBulkUploadRewardsCriteriaData()) {
            rewardCriteriaIds.add(rewardCriteriaRepository.findRewardCriteriaByProductId(criteria.split(";")[1]).getId());
            rewardCriteriaRepository.deleteRewardCriteriaByProductId(criteria.split(";")[1]);
        }

        criteriaRemoved = true;
    }

    @Before
    public void beforeAll(Scenario scenario) {
        if (scenario.getSourceTagNames().contains("@Ews"))
            dataExchanger.setEwsRequest(true);
        else
            dataExchanger.setEwsRequest(false);
    }

}