package com.iat.utils;

/* Implementation of SessionIdKeeper was done in Singleton convention, please do not modify
*  To get instance of JsonParserUtils for usage, you have to directly add in method:
*  JsonParserUtils jsonParser = JsonParserUtils.getInstance();
*
*  Methods:
*  convertStringToJson - converts provided string to JsonObject
*  extractValueFromFlatJson - returns string value of provided jsonKey within JsonObject
*  extractValuesFromJsonArray - returns StringArray of all values connected with jsonKey.
*                              Method takes as input JsonObject, jsonArrayKey and jsonKey of
*                              seek value.
*
* */

import com.iat.domain.*;

import java.util.ArrayList;
import java.util.List;

public class DataExchanger {

    private static DataExchanger dataExchanger;

    private DataExchanger() {
    }

    private List<Supplier> suppliers = new ArrayList<>();
    private List<Brand> brands = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private List<Question> questions = new ArrayList<>();
    private List<Store> stores = new ArrayList<>();
    private Retailer retailer = new Retailer();
    private List<Retailer> retailers = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Answer> answers = new ArrayList<>();

    private Audit auditObject;
    private RewardCriteria rewardCriteriaObject;


    private boolean questionsTestDataCreated = false;
    private boolean rewardCriteriaTestDataCreated = false;

    private String generatedRetailerMail = "";


    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void addSupplierToSuppliersList(Supplier supplier) {
        this.suppliers.add(supplier);
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public void addBrandToBrandsList(Brand brand) {
        this.brands.add(brand);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void addCategoryToCategoriesList(Category category) {
        this.categories.add(category);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProductToProductsList(Product product) {
        this.products.add(product);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestionToQuestionsList(Question question) {
        this.questions.add(question);
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public void addStoreToStoresList(Store store) {
        this.stores.add(store);
    }

    public void clearStoreListAndSetStore(Store store) {
        this.stores.clear();
        this.stores.add(store);
    }


    public Audit getAuditObject() {
        return auditObject;
    }

    public void setAuditObject(Audit auditObject) {
        this.auditObject = auditObject;
    }

    public RewardCriteria getRewardCriteriaObject() {
        return rewardCriteriaObject;
    }

    public void setRewardCriteriaObject(RewardCriteria rewardCriteriaObject) {
        this.rewardCriteriaObject = rewardCriteriaObject;
    }

    public boolean isQuestionsTestDataCreated() {
        return questionsTestDataCreated;
    }

    public void setQuestionsTestDataCreated(boolean questionsTestDataCreated) {
        this.questionsTestDataCreated = questionsTestDataCreated;
    }


    public boolean isRewardCriteriaTestDataCreated() {
        return rewardCriteriaTestDataCreated;
    }

    public void setRewardCriteriaTestDataCreated(boolean rewardCriteriaTestDataCreated) {
        this.rewardCriteriaTestDataCreated = rewardCriteriaTestDataCreated;
    }

    public Retailer getRetailer() {
        return retailer;
    }

    public void setRetailer(Retailer retailer) {
        this.retailer = retailer;
    }

    public String getGeneratedRetailerMail() {
        return generatedRetailerMail;
    }

    public void setGeneratedRetailerMail(String generatedRetailerMail) {
        this.generatedRetailerMail = generatedRetailerMail;
    }

    public void addGeneratedRetailerMail(String email) {
        this.generatedRetailerMail += email;
    }

    public List<Retailer> getRetailers() {
        return retailers;
    }

    public void addRetailerToRetailersList(Retailer retailers) {
        this.retailers.add(retailers);
    }

    public void clearRetailersList() {
        this.retailers.clear();
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    //  OLD  >>>>>>>>>>>>>>>>>>>>>>>>>>>>
    private String auditId;
    private String rewardCriteriaId;

    private String retailerId;
    private String retailerUuid;
    private String retailerName;
    private String retailerEmail;

    private String store1Id;
    private String store2Id;

    private String chainId;
    private String chainName;

    private String wholesalerId;

    private String userUuid;
    private int userBalance;

    public static DataExchanger getInstance() {

        if (dataExchanger == null) {
            dataExchanger = new DataExchanger();
        }
        return dataExchanger;
    }

    public void setAuditId(String id) {
        auditId = id;
    }

    public String getAuditId() {
        return this.auditId != null ? auditId : "";
    }

    public void setRewardCriteriaId(String id) {
        rewardCriteriaId = id;
    }

    public String getRewardCriteriaId() {
        return rewardCriteriaId;
    }

    //retailer area
    public void setRetailerId(String id) {
        retailerId = id;
    }

    public String getRetailerId() {
        return retailerId;
    }

    public void setRetailerUuid(String uuid) {
        retailerUuid = uuid;
    }

    public String getRetailerUuid() {
        return retailerUuid;
    }

    public void setRetailerName(String name) {
        retailerName = name;
    }

    public String getRetailerName() {
        return retailerName;
    }

    public void setRetailerEmail(String email) {
        retailerEmail = email;
    }

    public String getRetailerEmail() {
        return retailerEmail;
    }

    //store area
    public void setStore1Id(String id) {
        store1Id = id;
    }

    public String getStore1Id() {
        return store1Id;
    }

    public void setStore2Id(String id) {
        store2Id = id;
    }

    public String getStore2Id() {
        return store2Id;
    }

    public void setStore3Id(String id) {
        store1Id = id;
    }

    public String getStore3Id() {
        return store1Id;
    }

    //chain area
    public void setChainId(String id) {
        chainId = id;
    }

    public String getChainId() {
        return chainId;
    }

    public void setChainName(String name) {
        chainName = name;
    }

    public String getChainName() {
        return chainName;
    }

    //wholesaler area
    public void setWholesalerId(String id) {
        wholesalerId = id;
    }

    public String getWholesalerId() {
        return wholesalerId;
    }

    public void setUserBalance(int balance) {
        userBalance = balance;
    }

    public int getUserBalance() {
        return userBalance;
    }

    public void setUserUuid(String uuid) {
        userUuid = uuid;
    }

    public String getUserUuid() {
        return userUuid;
    }
}