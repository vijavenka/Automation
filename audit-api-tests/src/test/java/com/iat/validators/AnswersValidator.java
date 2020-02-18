package com.iat.validators;

import com.iat.actions.AnswerActions;
import com.iat.domain.Answer;
import com.iat.domain.Question;
import com.iat.utils.DataExchanger;
import com.iat.utils.HelpFunctions;
import com.iat.utils.JsonParserUtils;
import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonElement;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;


public class AnswersValidator {

    private AnswerActions answerActions = new AnswerActions();
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();
    DataExchanger dataExchanger = DataExchanger.getInstance();
    HelpFunctions helpFunctions = new HelpFunctions();

    public void validateProperAnswersRecorded(String partnerShortName) {
        System.out.println("\nAnswers validation: ");
        //Answers from answers list call
        JsonArray answersStoredInCms = jsonParserUtils.convertStringToJsonArray(answerActions.filterAnswersForAudit(answerActions.getAnswersListWithoutPrint("0;20;auditId,desc&sort=id,desc", 200), dataExchanger.getAuditObject().getId()));

        for (Answer answer : dataExchanger.getAnswers()) {
            boolean answerFound = false;

            //find internal storeId
//            System.out.println("store saved in answer: " + answer.getResults_c_Store_ID());
            String storeId = helpFunctions.findInternalStoreId(answer);

            String questionId = getQuestionId(answer, partnerShortName);

            //search for proper record
            for (JsonElement answerCms : answersStoredInCms) {
                String storeIdAnswerCms = answerCms.getAsJsonObject().get("storeId").getAsString();
                String questionIdAnswerCms = answerCms.getAsJsonObject().get("questionId").getAsString();
                String answerTextAnswerCms = answerCms.getAsJsonObject().get("answerText").getAsString();

                answer.setStoreId(storeIdAnswerCms);
                answer.setQuestionId(questionIdAnswerCms);
                answer.setAnswerText(answerTextAnswerCms);

                if (storeId.equals(storeIdAnswerCms) && questionId.equals(questionIdAnswerCms) && answer.getResults_response().equals(answerTextAnswerCms)) {
                    System.out.println("Answer found! " + " storeId: " + storeId + " questionId: " + questionId + " response: " + answer.getResults_response());
                    answerFound = true;
                    break;
                }
            }
            assertThat("Answer not found, audit: " + dataExchanger.getAuditObject().getId() + ", storeId: " + storeId + " questionId: " + questionId, answerFound);
        }
    }


    public String getQuestionId(Answer answer, String partnerShortName) {
        String questionId = "";

        if (partnerShortName.toLowerCase().contains("todays")) {
            //for product questions internal questionId is store in column c_product_id
            if (answer.getResults_question_type().equals("0"))
                questionId = answer.getResults_c_product_id();

            //for adhoc questions adhoc_ext_id of question is stored in question_id column
            if (answer.getResults_question_type().equals("3")) {
                List<Question> questionsAdHoc = helpFunctions.getQuestionsListOfType("ADHOC");

                for (Question question : questionsAdHoc) {
                    if (question.getAdhocExtId().equals(answer.getResults_question_id())) {
                        questionId = question.getId();
                        break;
                    }
                }
            }

            //todo images are not supported
        } else {
            questionId = answer.getResults_question_id();
//            QuestionActions questionActions = new QuestionActions();
//            String questionType = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(questionActions.getQuestionById(answer.getQuestionId(), 200)), "questionType");
        }

        return questionId;
    }
}
