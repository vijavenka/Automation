package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iat.utils.DataExchanger;
import com.iat.utils.HelpFunctions;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@Getter
@Setter
public class Question {

    private String id;
    private String adhocExtId;
    private String extRelId;
    private String imagesNumber;
    private String placement;
    private String productId;
    private String questionText;
    private String questionType;
    private String categoryId;


    private DateTime dateTime = new DateTime();
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private HelpFunctions helpFunctions = new HelpFunctions();

    public Question(@JsonProperty("id") String id,
                    @JsonProperty("adhocExtId") String adhocExtId,
                    @JsonProperty("extRelId") String extRelId,
                    @JsonProperty("imagesNumber") String imagesNumber,
                    @JsonProperty("placement") String placement,
                    @JsonProperty("productId") String productId,
                    @JsonProperty("questionText") String questionText,
                    @JsonProperty("questionType") String questionType,
                    @JsonProperty("categoryId") String categoryId) {

        if (id != null) {
            this.id = id;
            this.questionText = questionText;
            this.adhocExtId = adhocExtId;
            this.extRelId = extRelId;
            this.imagesNumber = imagesNumber;
            this.placement = placement;
            this.productId = productId;
            this.questionType = questionType;
            this.categoryId = categoryId;
        } else {
            this.id = id;
            this.questionText = questionText == null ? questionText : questionText.isEmpty() ? questionText : questionText.contains("API_AUDIT_CMS_QUESTION_") ? questionText + dateTime.getMillis() : questionText;
            this.adhocExtId = adhocExtId == null ? adhocExtId : adhocExtId.equalsIgnoreCase("RANDOM") ? "1234" + helpFunctions.returnRandomValue(100000) : adhocExtId;
            this.extRelId = extRelId;
            this.imagesNumber = imagesNumber;
            this.placement = placement;
            this.productId = productId == null ? productId : productId.isEmpty() ? productId : productId.contains("previous_call") ? dataExchanger.getProducts().get(dataExchanger.getProducts().size() - 1).getId() : productId;
            this.questionType = questionType;
            this.categoryId = categoryId == null ? categoryId : categoryId.isEmpty() ? categoryId : categoryId.contains("previous_call") ? dataExchanger.getCategories().get(dataExchanger.getCategories().size() - 1).getId() : categoryId;
        }


    }

    @Override
    public String toString() {
        return ("{" +
                (id == null ? "" : "\"id\": " + id + ", ") +
                (questionText == null ? "" : "\"questionText\": \"" + questionText + "\", ") +
                (adhocExtId == null ? "" : "\"adhocExtId\": \"" + adhocExtId + "\", ") +
                (extRelId == null ? "" : "\"extRelId\": \"" + extRelId + "\", ") +
                (imagesNumber == null ? "" : "\"imagesNumber\": \"" + imagesNumber + "\", ") +
                (placement == null ? "" : "\"placement\": \"" + placement + "\", ") +
                (productId == null ? "" : "\"productId\": \"" + productId + "\", ") +
                (questionType == null ? "" : "\"questionType\": \"" + questionType + "\", ") +
                (categoryId == null ? "" : "\"categoryId\":\"" + categoryId + "\", ") +
                '}').replace(", }", "}");
    }
}
