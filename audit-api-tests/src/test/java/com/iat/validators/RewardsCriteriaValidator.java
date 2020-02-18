package com.iat.validators;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.Config;
import com.iat.actions.*;
import com.iat.domain.*;
import com.iat.utils.DataExchanger;
import com.iat.utils.JdbcDatabaseConnector;
import com.iat.utils.JsonParserUtils;

import java.io.IOException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class RewardsCriteriaValidator {

    private RewardCriteriaActions rewardCriteriaActions = new RewardCriteriaActions();
    private QuestionActions questionActions = new QuestionActions();
    private ProductActions productActions = new ProductActions();
    private BrandActions brandActions = new BrandActions();
    private SuppliersActions suppliersActions = new SuppliersActions();

    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();
    DataExchanger dataExchanger = DataExchanger.getInstance();
    private String generatedRewardCriteriaName = "";
    private JdbcDatabaseConnector mySQLConnector_audit = new JdbcDatabaseConnector(Config.mysqlConnectionPool_audit);
    private JdbcDatabaseConnector mySQLConnector_pointsManager = new JdbcDatabaseConnector(Config.mysqlConnectionPool_pointsManager);
    ObjectMapper mapper = new ObjectMapper();

    public void validateIfTagCreationInPointsManager(String response) throws IOException {

        String rewardCriteriaId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "id");
        String rewardCriteriaName = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "criteriaName");
        String rewardCriteriaTagKey = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "tagKey");
        String rewardCriteriaPoints = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "points");

        String cap = mySQLConnector_pointsManager.getSingleResult("SELECT cap FROM  Tag where tagKey = \"" + rewardCriteriaTagKey + "\"");
        String frequency = mySQLConnector_pointsManager.getSingleResult("SELECT frequency FROM  Tag where tagKey = \"" + rewardCriteriaTagKey + "\"");
        String description = mySQLConnector_pointsManager.getSingleResult("SELECT description FROM  Tag where tagKey = \"" + rewardCriteriaTagKey + "\"");
        String partnerId = mySQLConnector_pointsManager.getSingleResult("SELECT partnerId FROM  Tag where tagKey = \"" + rewardCriteriaTagKey + "\"");
        String partnerName = mySQLConnector_pointsManager.getSingleResult("SELECT name FROM  Partner where id = " + partnerId);

        assertThat("Created Tag [tagKey] improperly generated", rewardCriteriaTagKey, is(rewardCriteriaName.replace(" ", "_")));
        assertThat("Created Tag [description] improperly set", description, is(rewardCriteriaName));
        assertThat("Created Tag [cap] improperly set", cap, is(rewardCriteriaPoints));
        assertThat("Created Tag [frequency] improperly set", frequency, is("NONE"));
        assertThat("Created Tag [partnerId] improperly set", partnerName, is(getProperSupplierNameForRewardCriteria(rewardCriteriaId)));

    }

    public String getProperSupplierNameForRewardCriteria(String id) throws IOException {
        RewardCriteria rewardCriteria = mapper.readValue(rewardCriteriaActions.getRewardCriteriaById(id, 200), RewardCriteria.class);
        String[] rewardCriteriaRulesList = rewardCriteria.getCriteriaRule().split(" ");


        Optional<Supplier> productSupplier = Optional.empty();
        Optional<Supplier> iatSupplier = Optional.empty();

        for (int i = 0; i < rewardCriteriaRulesList.length; i++) {
            Supplier supplier;
            if (!rewardCriteriaRulesList[i].equals("and") && !rewardCriteriaRulesList[i].equals("or")) {
                Question question = mapper.readValue(questionActions.getQuestionById(rewardCriteriaRulesList[i], 200), Question.class);

                if (question.getQuestionType().equals("ADHOC")) {
                    supplier = mapper.readValue(suppliersActions.getSupplierById(suppliersActions.getIdforSupplierName("IATSupplier"), 200), Supplier.class);
                    iatSupplier = Optional.of(supplier);
                } else {
                    Product product = mapper.readValue(productActions.getProductById(question.getProductId(), 200), Product.class);
                    Brand brand = mapper.readValue(brandActions.getBrandById(product.getBrandId(), 200), Brand.class);
                    supplier = mapper.readValue(suppliersActions.getSupplierById(brand.getSupplierId(), 200), Supplier.class);

                    if (productSupplier.isPresent()) {
                        if (!productSupplier.get().getSupplierName().equals(supplier.getSupplierName()))
                            assertTrue("More than one product supplier found for reward criteria: " + rewardCriteria.getId(), false);
                    } else {
                        productSupplier = Optional.of(supplier);
                    }
                }
            }
        }

        if (productSupplier.isPresent())
            return productSupplier.get().getSupplierName();

        if (iatSupplier.isPresent())
            return iatSupplier.get().getSupplierName();

        return "No suppliers found for reward criteria: " + rewardCriteria.getId();
    }


    public void validatePartnerNotExistForSupplier(String supplierName) {
        String partnerId = mySQLConnector_pointsManager.getSingleResult("SELECT id FROM Partner where name = \"" + supplierName + "\"");

        assertThat("Partner exist for supplier: " + supplierName, String.valueOf(partnerId), is("null"));
    }

}