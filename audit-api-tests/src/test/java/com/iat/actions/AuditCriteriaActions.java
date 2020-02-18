package com.iat.actions;


import com.iat.controller.AuditCriteriaController;
import com.iat.utils.DataExchanger;
import com.iat.utils.JsonParserUtils;
import io.restassured.response.ValidatableResponse;


public class AuditCriteriaActions {

    private AuditCriteriaController auditCriteriaController = new AuditCriteriaController();
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();
    DataExchanger dataExchanger = DataExchanger.getInstance();
    String response;


    public String createAuditCriteria(String jsonBody, int code) {
        ValidatableResponse validatableResponse = auditCriteriaController.createAuditCriteria(jsonBody, code);
        return validatableResponse.extract().response().asString();
    }

    public String exportRewardCriteria(String jsonBody, int code) {
        ValidatableResponse validatableResponse = auditCriteriaController.exportAuditCriteria(jsonBody, code);
        return validatableResponse.extract().response().asString();
    }

//    public String generateAuditCriteriaExportBody(String segmentation){
//        String jsonBody = "";
//
//        if (!segmentation.startsWith("{")) {
//            Date dateTime = new Date();
//
//            String[] segmentation2 = segmentation.split(";");
//            String chain = segmentation2[0];
//            String questions = segmentation2[1];
//            String group = segmentation2[2];
//            String licensed = segmentation2[3];
//            String countries = segmentation2[4];
//
//
//            jsonBody = createAuditCriteriaExportJsonBodyTemplate;
//
//            jsonBody = jsonBody.replace("AUDIT_TO_REPLACE", "\"auditId\": " + dataExchanger.getAuditId() + ",");
//
//            //chain
//            if (chain.equals("null"))
//                jsonBody = jsonBody.replace("CHAIN_TO_REPLACE", "");
//            else if (chain.equals("empty"))
//                jsonBody = jsonBody.replace("CHAIN_TO_REPLACE", "\"chainId\": null,");
//            else{
//                if(chain.equals("Todays"))
//                    chain = "1";
//                if(chain.equals("Premier"))
//                    chain = "2";
//                jsonBody = jsonBody.replace("CHAIN_TO_REPLACE", "\"chainId\": " + chain +",");
//            }
//
//            //questions
//            if (questions.equals("null"))
//                jsonBody = jsonBody.replace("QUESTIONS_TO_REPLACE", "");
//            else if (questions.equals("empty"))
//                jsonBody = jsonBody.replace("QUESTIONS_TO_REPLACE", "\"questions\": [],");
//            else {
//                String [] questionsArray = questions.split(",");
//                String question = "\"questions\": [";
//                for(String q : questionsArray){
//                    String questionResponse = questionActions.getQuestionById(q, 200);
//                    question += questionResponse + ", ";
//                }
//                jsonBody = jsonBody.replace("QUESTIONS_TO_REPLACE", (question.trim() +"]").replace(",]", "],"));
//            }
//
//            //group
//            if (group.equals("null"))
//                jsonBody = jsonBody.replace("GROUP_TO_REPLACE", "");
//            else if (group.equals("empty"))
//                jsonBody = jsonBody.replace("GROUP_TO_REPLACE", "\"auditGroup\": null,");
//            else{
//                jsonBody = jsonBody.replace("GROUP_TO_REPLACE", "\"auditGroup\": \"" + group +"\",");
//            }
//
//            //licensed
//            if (licensed.equals("null"))
//                jsonBody = jsonBody.replace("LICENSED_TO_REPLACE", "");
//            else if (licensed.equals("empty"))
//                jsonBody = jsonBody.replace("LICENSED_TO_REPLACE", "\"licensed\": null,");
//            else{
//                jsonBody = jsonBody.replace("LICENSED_TO_REPLACE", "\"licensed\": " + licensed +",");
//            }
//
//            //country
//            if (countries.equals("null"))
//                jsonBody = jsonBody.replace("COUNTRIES_TO_REPLACE", "");
//            else if (countries.equals("empty"))
//                jsonBody = jsonBody.replace("COUNTRIES_TO_REPLACE", "\"countries\": [],");
//            else{
//                String [] countriesArray = countries.split(",");
//                String country = "\"countries\": [";
//                for (String c : countriesArray) {
//                    country += "\"" + c + "\", ";
//                }
//                jsonBody = jsonBody.replace("COUNTRIES_TO_REPLACE", (country.trim() + "]").replace(",]", "],"));
//            }
//
//        } else {
//            jsonBody = segmentation;
//        }
//
//        return (jsonBody.trim() + "}").replace(",}", "}");
//
//    }
//
//
//    public String getExportedAuditCriteriaCountSQL(){
//        String count = mySQLConnector_audit.getSingleResult("Select count(*) FROM audit_criteria WHERE audit_id = " + dataExchanger.getAuditId());
//        return count;
//    }
//
//    public String getStoresCountSQL(String segmentation){
////        String[] segmentation2 = segmentation.split(";");
////        String chain = segmentation2[0];
////        if(chain.equals("Todays"))
////            chain = "1";
////        if(chain.equals("Premier"))
////            chain = "2";
////        String questions = segmentation2[1];
////        String group = segmentation2[2];
////        String licensed = segmentation2[3];
////        String countries = segmentation2[4];
////
////        String whereQuery = " active = true and ";
////        if(!(chain.equals("empty") || chain.equals("null")))
////            whereQuery += "chain_id = " + chain + " and ";
////        if(!(group.equals("empty") || group.equals("null")))
////            whereQuery += "audit_group = \"" + group + "\" and ";
////        if(!(licensed.equals("empty") || licensed.equals("null")))
////            whereQuery += "licensed = " + licensed + " and ";
////        if(!(countries.equals("empty") || countries.equals("null"))){
////            String [] countriesArray = countries.split(",");
////            String country = "(";
////            for (String c : countriesArray) {
////                country += "\"" + c + "\", ";
////            }
////            whereQuery += "country in " + (country.trim() + ")").replace(",)", ")") + " and ";
////        }
////        whereQuery+="END";
////        whereQuery = whereQuery.replace("and END", "");
////        String query = "Select count(*) FROM store WHERE " + whereQuery;
////        System.out.println("Select count(*) FROM store WHERE " + whereQuery);
////        return mySQLConnectorAudit.get(query, 1);
//        return "";
//    }
//
//
//    public void validateIfRandomRowsHaveProperValuesAccordingSelectedSegmentation(String segmentation)throws SQLException {
////        String query = "Select id, store_id, question_id FROM audit_criteria WHERE audit_id = " + dataExchanger.getAuditId();
////        mySQLConnectorAudit.getAllQueryContent(query);
////
////        for(int i = 1; i<11;i++){
////            String auditCriteriaStoreId = mySQLConnectorAudit.getValueOfProperRow(true, 2);
////            String auditCriteriaQuestionId = mySQLConnectorAudit.getValueOfProperRow(false, 3);
////            String auditCriteriaId = mySQLConnectorAudit.getValueOfProperRow(false, 1);
////
////            System.out.println("Iteration: " + i + " Store: " + auditCriteriaStoreId + " Question: " + auditCriteriaQuestionId);
////
////
////            String[] segmentation2 = segmentation.split(";");
////
////            //Question Id validation
////            String questions = segmentation2[1];
////            String [] questionsArray = questions.split(",");
////            boolean questionCorrect = false;
////            for(String q : questionsArray){
////                if(auditCriteriaQuestionId.equals(q)){
////                    questionCorrect = true;
////                    break;
////                }
////            }
////            assertTrue("AuditCriteria id: " + auditCriteriaId + " for store: "  + auditCriteriaStoreId + " Question id: " + auditCriteriaQuestionId + " is incorrect", questionCorrect);
////
////
////            response = storeActions.getStoreById(auditCriteriaStoreId);
////            String storeActive = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "active");
////            String storeChain = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "chainId");
////            String storeGroup = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "auditGroup");
////            String storeLicensed = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "licensed");
////            String storeCountry = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "country");
////
////
////            //validate is store active
////            assertTrue("AuditCriteria id: " + auditCriteriaId + " for store: "  + auditCriteriaStoreId + " active status: " + storeActive + " is incorrect, should be: true", storeActive.equals("true"));
////
////            String chain = segmentation2[0];
////            if(chain.equals("Todays"))
////                chain = "1";
////            if(chain.equals("Premier"))
////                chain = "2";
////            assertTrue("AuditCriteria id: " + auditCriteriaId + " for store: "  + auditCriteriaStoreId + " Chain id: " + storeChain + " is incorrect, should be: " + chain, storeChain.equals(chain));
////
////            String group = segmentation2[2];
////            if(!(group.equals("empty") || group.equals("null")))
////                assertTrue("AuditCriteria id: " + auditCriteriaId + " for store: "  + auditCriteriaStoreId + " AuditGroup: " + storeGroup + " is incorrect, should be: " + group, storeGroup.equals(group));
////
////            String licensed = segmentation2[3];
////            if(!(licensed.equals("empty") || licensed.equals("null")))
////                assertTrue("AuditCriteria id: " + auditCriteriaId + " for store: "  + auditCriteriaStoreId + " Licensed: " + storeLicensed + " is incorrect, should be: " + licensed, storeLicensed.equals(licensed));
////
////            String country = segmentation2[4];
////            String [] countriesArray = country.split(",");
////            if(countriesArray.length == 1){
////                if(!(countriesArray[0].equals("empty") || countriesArray[0].equals("null")))
////                    assertTrue("AuditCriteria id: " + auditCriteriaId + " for store: "  + auditCriteriaStoreId + " Country: " + storeCountry + " is incorrect, should be: " + countriesArray[0], storeCountry.equals(countriesArray[0]));
////            }else{
////                boolean countriesCorrect = false;
////                for(String c : countriesArray){
////                    if(storeCountry.equals(c)){
////                        countriesCorrect = true;
////                        break;
////                    }
////                }
////                assertTrue("AuditCriteria id: " + auditCriteriaId + " for store: "  + auditCriteriaStoreId + " Question id: " + auditCriteriaQuestionId + " is incorrect", countriesCorrect);
////            }
//
//    }
}
