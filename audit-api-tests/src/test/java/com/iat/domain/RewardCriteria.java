package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iat.actions.AuditsActions;
import com.iat.utils.DataExchanger;
import com.iat.utils.HelpFunctions;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@Getter
@Setter
public class RewardCriteria {

    private String id;
    private String criteriaName;
    private String criteriaRule;
    private String points;
    private String auditId;
    private String tagKey;

    private DateTime dateTime = new DateTime();
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private AuditsActions auditsActions = new AuditsActions();
    private Random random = new Random();

    public RewardCriteria(@JsonProperty("id") String id,
                          @JsonProperty("criteriaName") String criteriaName,
                          @JsonProperty("criteriaRule") String criteriaRule,
                          @JsonProperty("points") String points,
                          @JsonProperty("auditId") String auditId,
                          @JsonProperty("tagKey") String tagKey) {


        this.id = id;
        this.criteriaName = criteriaName == null ? criteriaName : criteriaName.isEmpty() ? criteriaName : criteriaName.contains("API_AUDIT_CMS_CR_") ? criteriaName + dateTime.getMillis() : criteriaName;
        generateRewardCriteriaRule(criteriaRule);
        this.points = points == null ? points : points.isEmpty() ? points : points.equalsIgnoreCase("RANDOM") ? String.valueOf(random.nextInt(1000) + 1) : points;
        this.auditId = auditId == null ? auditId : auditId.isEmpty() ? auditId : auditId.contains("previous_call") ? dataExchanger.getAuditObject().getId() : auditId.contains("API_") ? auditsActions.getIdForAuditName(auditId) : auditId;
        this.tagKey = tagKey;
    }

    public void generateRewardCriteriaRule(String criteriaRule) {

        HelpFunctions helpFunctions = new HelpFunctions();

        if (criteriaRule != null) {
            if (criteriaRule.contains("ADHOC") || criteriaRule.contains("IMAGE") || criteriaRule.contains("PRODUCT")) {

                String[] questionsWordList = criteriaRule.split(" ");

                StringBuilder rule = new StringBuilder();

                int adhoc = 0;
                int image = 0;
                int product = 0;
                List<Question> questionsTypeAdhoc = helpFunctions.getQuestionsListOfType("ADHOC");
                List<Question> questionsTypeImage = helpFunctions.getQuestionsListOfType("IMAGE");
                List<Question> questionsTypeProduct = helpFunctions.getQuestionsListOfType("PRODUCT");

                for (int i = 0; i < questionsWordList.length; i++) {
                    if (questionsWordList[i].equals("ADHOC")) {
                        assertTrue("Not enough unique ADHOC questions available see ->> createDataBeforeRewardCriteriaTests()", adhoc < questionsTypeAdhoc.size());
                        questionsWordList[i] = questionsTypeAdhoc.get(adhoc++).getId();
                    }

                    if (questionsWordList[i].equals("IMAGE")) {
                        //TODO is not supported right now
                        assertTrue("Not enough unique IMAGE questions available see ->> createDataBeforeRewardCriteriaTests()", image < questionsTypeImage.size());
                        questionsWordList[i] = questionsTypeImage.get(image++).getId();
                    }

                    if (questionsWordList[i].equals("PRODUCT")) {
                        assertTrue("Not enough unique PRODUCT questions available see ->> createDataBeforeRewardCriteriaTests()", product < questionsTypeProduct.size());
                        questionsWordList[i] = questionsTypeProduct.get(product++).getId();
                    }

                    rule.append(questionsWordList[i] + " ");
                }
                criteriaRule = rule.toString();
            }

            this.criteriaRule = criteriaRule.trim();
        } else {
            this.criteriaRule = null;
        }

    }

    @Override
    public String toString() {
        return ("{" +
                (id == null ? "" : "\"id\": " + id + ", ") +
                (criteriaName == null ? "" : "\"criteriaName\": \"" + criteriaName + "\", ") +
                (criteriaRule == null ? "" : "\"criteriaRule\":\"" + criteriaRule + "\", ") +
                (points == null ? "" : "\"points\":\"" + points + "\", ") +
                (auditId == null ? "" : "\"auditId\":\"" + auditId + "\", ") +
                (tagKey == null ? "" : "\"tagKey\": " + tagKey + ", ") +
                '}').replace(", }", "}");
    }
}
