package com.iat.utils;

import com.iat.Config;
import com.iat.actions.ChainActions;
import com.iat.domain.Answer;
import com.iat.domain.Question;
import com.iat.domain.Store;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class HelpFunctions {

    private DataExchanger dataExchanger = DataExchanger.getInstance();

    public int returnRandomValue(int range) {
        Random rand = new Random();
        int random = rand.nextInt(range);
        return random;
    }

    private JdbcDatabaseConnector mySQLConnector_audit = new JdbcDatabaseConnector(Config.mysqlConnectionPool_audit);
    private JdbcDatabaseConnector mySQLConnector_pointsManager = new JdbcDatabaseConnector(Config.mysqlConnectionPool_pointsManager);
    private ChainActions chainActions = new ChainActions();

    public long returnEpochOfCurrentDay() {
        Date todayDate = new Date();
        return todayDate.getTime();
    }

    public String getChainIdForPartnerShortName(String partnerShortName) {
        String chainId = "";

        if (partnerShortName.toLowerCase().contains("todays"))
            chainId = "1";
        else if (partnerShortName.toLowerCase().contains("premier"))
            chainId = "2";
        else if (partnerShortName.toLowerCase().contains("nisa"))
            chainId = "3";
        else
            chainId = "0";

        return chainId;
    }

    public List<Question> getQuestionsListOfType(String questionType) {

        List<Question> questionsList = new ArrayList<>();

        for (Question q : dataExchanger.getQuestions()) {
            if (q.getQuestionType().equals(questionType))
                questionsList.add(q);
        }
        return questionsList;
    }

    public String findInternalStoreId(Answer answer) {
        String storeId = "";
        for (Store store : dataExchanger.getStores()) {
            if (answer.getResults_c_Store_ID().equals(store.getExtRelId())) {
//                    System.out.println("Store: external: " + store.getExtRelId() + " internal: " + store.getId());
                storeId = store.getId();
                break;
            }
        }
        return storeId;
    }

    public String findRetailerIdForStoreId(String storeId) {
        String retailerId = "";
        for (Store store : dataExchanger.getStores()) {
            if (store.getId().equals(storeId)) {
                retailerId = store.getRetailerId();
                break;
            }
        }
        return retailerId;
    }
}