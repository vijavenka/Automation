package com.iat.steps;

import com.iat.Config;
import com.iat.actions.AuditResultActions;
import com.iat.actions.AuditsActions;
import com.iat.actions.RetailerActions;
import com.iat.actions.StoreActions;
import com.iat.domain.Store;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.utils.DataExchanger;
import com.iat.utils.JdbcDatabaseConnector;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HooksSteps {

    private LoginSteps loginSteps = new LoginSteps();
    private SuppliersSteps suppliersSteps = new SuppliersSteps();
    private BrandSteps brandSteps = new BrandSteps();
    private CategorySteps categorySteps = new CategorySteps();
    private ProductSteps productSteps = new ProductSteps();
    private QuestionSteps questionSteps = new QuestionSteps();
    private AuditsSteps auditsSteps = new AuditsSteps();
    private RetailerSteps retailerSteps = new RetailerSteps();
    private RetailerActions retailerActions = new RetailerActions();

    private AuditsActions auditsActions = new AuditsActions();
    private AuditResultActions auditResultActions = new AuditResultActions();
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private JdbcDatabaseConnector mySQLConnector_audit = new JdbcDatabaseConnector(Config.mysqlConnectionPool_audit);
    private JdbcDatabaseConnector mySQLConnector_pointsManager = new JdbcDatabaseConnector(Config.mysqlConnectionPool_pointsManager);
    private String prefix = "API_AUDIT_CMS_";

    @Before("@createDataBeforeQuestionsTests")
    public void createDataBeforeQuestionsTests() throws Throwable {
        if (!dataExchanger.isQuestionsTestDataCreated()) {
            System.out.println("\nCREATE DATA BEFORE QUESTIONS TESTS");

            loginSteps.iatAdminUserLogIn("DEFAULT");
            suppliersSteps.createSupplier("DEFAULT", 201);
            brandSteps.createBrand("DEFAULT", 201);
            categorySteps.createCategory("DEFAULT", 201);
            productSteps.createProduct("DEFAULT", 201);


            dataExchanger.setQuestionsTestDataCreated(true);

            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    deleteAllDataOfCreatedAuditAfterTest();
                }
            });
        }
    }


    @Before("@createDataBeforeRewardCriteriaTests")
    public void createDataBeforeRewardCriteriaTests() throws Throwable {
        if (!dataExchanger.isRewardCriteriaTestDataCreated()) {
            System.out.println("\nCREATE DATA BEFORE REWARD CRITERIA TESTS");
            System.out.println("2x Suppliers 2 brands for each supplier and 1 product for each brand");
            System.out.println("Question type PRODUCT is created for each product ");
            System.out.println("Question type ADHOC is created (count: 2)");
            System.out.println("Question type IMAGE is created (count: 2)");
            System.out.println("1x Audit");

            loginSteps.iatAdminUserLogIn("DEFAULT");

            categorySteps.createCategory("DEFAULT", 201);
            for (int suppliersCounter = 0; suppliersCounter < 2; suppliersCounter++) {
                suppliersSteps.createSupplier("DEFAULT", 201);

                for (int brandsCounter = 0; brandsCounter < 2; brandsCounter++) {
                    brandSteps.createBrand("DEFAULT", 201);
                    productSteps.createProduct("DEFAULT", 201);
                    questionSteps.createQuestion("DEFAULT", 201);
                }
            }

            questionSteps.createQuestion("{\"questionText\":\"API_AUDIT_CMS_QUESTION_1_\",\"adhocExtId\":\"RANDOM\",\"questionType\":\"ADHOC\",\"placement\": null,\"imagesNumber\": null,\"extRelId\":null,\"id\":null, \"productId\": null, \"categoryId\": null}", 201);
            questionSteps.createQuestion("{\"questionText\":\"API_AUDIT_CMS_QUESTION_2_\",\"adhocExtId\":\"RANDOM\",\"questionType\":\"ADHOC\",\"placement\": null,\"imagesNumber\": null,\"extRelId\":null,\"id\":null, \"productId\": null, \"categoryId\": null}", 201);
            questionSteps.createQuestion("{\"questionText\":\"API_AUDIT_CMS_QUESTION_1_\",\"adhocExtId\":null,\"questionType\":\"IMAGE\",\"placement\":\"COUNTER\",\"imagesNumber\": 99,\"extRelId\":null,\"id\":null, \"productId\": null, \"categoryId\": null}", 201);
            questionSteps.createQuestion("{\"questionText\":\"API_AUDIT_CMS_QUESTION_2_\",\"adhocExtId\":null,\"questionType\":\"IMAGE\",\"placement\":\"COUNTER\",\"imagesNumber\": 99,\"extRelId\":null,\"id\":null, \"productId\": null, \"categoryId\": null}", 201);

            auditsSteps.createAudit("{\"auditName\": \"API_AUDIT_CMS_AUDIT_\", \"auditStart\": \"2013-01-01\", \"auditEnd\": \"2013-01-01\"}", 201);

            dataExchanger.setRewardCriteriaTestDataCreated(true);

            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    deleteAllDataOfCreatedAuditAfterTest();
                }
            });
        }

    }


    @Before("@createDataBeforeRewardCriteriaTestMissingPartner")
    public void createDataBeforeRewardCriteriaTestMissingPartner() throws Throwable {
        System.out.println("\nCREATE DATA BEFORE TEST: Create reward criteria for not existing partner for supplier");
        System.out.println("Supplier, brand for supplier and 1 product for brand");
        System.out.println("Question type PRODUCT is created for each product ");
        System.out.println("Audit");

        loginSteps.iatAdminUserLogIn("DEFAULT");

        categorySteps.createCategory("DEFAULT", 201);
        for (int suppliersCounter = 0; suppliersCounter < 1; suppliersCounter++) {
            suppliersSteps.createSupplier("DEFAULT", 201);

            for (int brandsCounter = 0; brandsCounter < 1; brandsCounter++) {
                brandSteps.createBrand("DEFAULT", 201);
                productSteps.createProduct("DEFAULT", 201);
                questionSteps.createQuestion("DEFAULT", 201);
            }
        }

        auditsSteps.createAudit("{\"auditName\": \"API_AUDIT_CMS_AUDIT_rewardCriteria_\", \"auditStart\": \"2013-01-01\", \"auditEnd\": \"2013-01-01\"}", 201);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                deleteAllDataOfCreatedAuditAfterTest();
            }
        });
    }

    @Before("@createNewRetailer")
    public void createNewRetailer() throws Throwable {
        retailerSteps.createNewRetailer("{ \"retailerName\": \"API_AUDIT_CMS_RETAILER_\", \"email\": \"api_audit_cms_retailer\",\"chains\": [ {\"id\": \"TODAYS\"}], \"wholesalerId\": \"1\"} ", 201);
    }

//    //Check retailer remove from selected chain
//    @Before("@createNewChain")
//    public void createNewChain() {
//        dataExchanger.setChainName(chainNameBase + func.returnEpochOfCurrentDay());
//        response = chainActions.createNewChain(new Chain(null, dataExchanger.getChainName(), "123", "123", Config.fakePartnerShortName, Config.fakePartnerApiKey));
//        dataExchanger.setChainId(jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "id"));
//    }
//
//
//    @Before("@createSetOfStoresUnderGivenChainAndRetailer")
//    public void createSetOfStoresUnderGivenChainAndRetailer() {
//
//        response = storeActions.createStore(new Store(null, storeNameBase + func.returnEpochOfCurrentDay() + "1", "true", "true", "auditGroup", "storeType", "line1", "line2", "line3", "line4", "postcode", "EnglandWales", Long.toString(func.returnEpochOfCurrentDay()) + "1", "123", dataExchanger.getChainId(), dataExchanger.getRetailerId()), 201);
//        dataExchanger.setStore1Id(jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "id"));
//
//        response = storeActions.createStore(new Store(null, storeNameBase + func.returnEpochOfCurrentDay() + "2", "true", "true", "auditGroup", "storeType", "line1", "line2", "line3", "line4", "postcode", "EnglandWales", Long.toString(func.returnEpochOfCurrentDay()) + "2", "123", Config.todaysChainIdQaAuditCms, dataExchanger.getRetailerId()), 201); //chainId = 1 is todays always available
//        dataExchanger.setStore2Id(jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "id"));
//
//        response = storeActions.createStore(new Store(null, storeNameBase + func.returnEpochOfCurrentDay() + "3", "true", "true", "auditGroup", "storeType", "line1", "line2", "line3", "line4", "postcode", "EnglandWales", Long.toString(func.returnEpochOfCurrentDay()) + "3", "123", Config.premierChainIdQaAuditCms, dataExchanger.getRetailerId()), 201); //chainId = 2 is premier always available
//        dataExchanger.setStore3Id(jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "id"));
//    }
//
//    @Before("@createNewChainRetailerAndStores")
//    public void createNewChainRetailerAndStores() throws InterruptedException, SQLException {
//        createNewChain();
//        Thread.sleep(500);
//        createNewRetailer();
//        Thread.sleep(500);
//        createSetOfStoresUnderGivenChainAndRetailer();
//    }
//
//    @After("@deleteCreatedChainAfterTest")
//    public void deleteCreatedChainAfterTest() {
//        chainActions.deleteSpecificChain(dataExchanger.getChainId());
//    }
//
//    @After("@deleteCreatedStoresAfterTest")
//    public void deleteCreatedStoresAfterTest() {
//        storeActions.deleteStore(dataExchanger.getStore1Id(), 200);
//        storeActions.deleteStore(dataExchanger.getStore2Id(), 200);
//    }
//
//    @After("@deleteNewChainRetailerAndStores")
//    public void deleteNewChainRetailerAndStores() {
//        deleteCreatedStoresAfterTest();
//        deleteCreatedChainAfterTest();
//        deleteCreatedRetailerAfterTest();
//    }
//
//    @After("@deleteNewChainRetailerAndStoresUsingDatabaseQuery")
//    public void deleteNewChainRetailerAndStoresUsingDatabaseQuery() throws InterruptedException {
////        int timeout = 5;
////        MySQLConnector conn = new MySQLConnector("audit-cms");
////        conn.update("DELETE FROM audit.retailer_chain");
////        int counter = 0;
////        while (!conn.get("SELECT COUNT(*) FROM audit.retailer_chain", 1).toString().equals("0")) {
////            Thread.sleep(500);
////            counter++;
////            if (counter > timeout) {
////                break;
////            }
////        }
////        conn.update("DELETE FROM audit.store WHERE store_name LIKE '%" + storeNameBase + "%'");
////        counter = 0;
////        while (!conn.get("SELECT COUNT(*) FROM audit.store WHERE store_name LIKE '%" + storeNameBase + "%'", 1).toString().equals("0")) {
////            Thread.sleep(500);
////            counter++;
////            if (counter > timeout) {
////                break;
////            }
////        }
////        conn.update("DELETE FROM audit.chain WHERE chain_name LIKE '%" + chainNameBase + "%'");
////        counter = 0;
////        while (!conn.get("SELECT COUNT(*) FROM audit.chain WHERE chain_name LIKE '%" + chainNameBase + "%'", 1).toString().equals("0")) {
////            Thread.sleep(500);
////            counter++;
////            if (counter > timeout) {
////                break;
////            }
////        }
////        conn.update("DELETE FROM audit.retailer WHERE retailer_name LIKE '%" + retailerNameBase + "%'");
////        counter = 0;
////        while (!conn.get("SELECT COUNT(*) FROM audit.retailer WHERE retailer_name LIKE '%" + retailerNameBase + "%'", 1).toString().equals("0")) {
////            Thread.sleep(500);
////            counter++;
////            if (counter > timeout) {
////                break;
////            }
////        }
//    }

    @After("@deleteAllDataOfCreatedAuditAfterTest")
    public void deleteAllDataOfCreatedAuditAfterTest() {
        System.out.println("\ndeleteAllDataOfCreatedAuditAfterTest");
        //TODO replace mysql deletions by API calls
        System.out.println("DELETE TEST DATA [AUDIT]: reward_points, answer, audit_criteria, reward_criteria, question, product, category, brand, supplier, stores, audit_criteria, audit");


        HashMap<Integer, List> uuids = mySQLConnector_audit.getResult("SELECT epoints_uuid FROM retailer where retailer_name like '" + prefix + "%'", Arrays.asList("epoints_uuid"));

        mySQLConnector_audit.execute("DELETE FROM reward_points where audit_id in (SELECT id from audit where audit_name like '" + prefix + "%')");
        mySQLConnector_audit.execute("DELETE FROM answer where audit_id in (SELECT id from audit where audit_name like '" + prefix + "%')");
        mySQLConnector_audit.execute("DELETE FROM audit_criteria where audit_id in (SELECT id FROM audit where audit_name like '" + prefix + "%')");

        mySQLConnector_audit.execute("DELETE FROM reward_criteria where criteria_name like '" + prefix + "%'");
        mySQLConnector_audit.execute("DELETE FROM question where question_text like '" + prefix + "%'");
        mySQLConnector_audit.execute("DELETE FROM product where product_name like '" + prefix + "%'");
        mySQLConnector_audit.execute("DELETE FROM category where category_name like '" + prefix + "%'");
        mySQLConnector_audit.execute("DELETE FROM brand where brand_name like '" + prefix + "%'");
        mySQLConnector_audit.execute("DELETE FROM supplier where supplier_name like '" + prefix + "%'");


        mySQLConnector_audit.execute("DELETE FROM store where store_name like '" + prefix + "%'");

        mySQLConnector_audit.execute("DELETE FROM retailer_chain where retailers_id  in (SELECT id FROM retailer where retailer_name like '" + prefix + "%')");
        mySQLConnector_audit.execute("DELETE FROM retailer where retailer_name like '" + prefix + "%'");


        mySQLConnector_audit.execute("DELETE FROM audit where audit_name like '" + prefix + "%'");


        System.out.println("\nDELETE TEST DATA [Points-manager]: Points, Tag, VirtualGroup, Partner, user");
        //points manager
        mySQLConnector_pointsManager.execute("DELETE from Points where tagId in (" + "SELECT id from Tag where tagKey like '" + prefix + "%'" + ")");
        mySQLConnector_pointsManager.execute("DELETE from Tag where tagKey like '" + prefix + "%'");
        mySQLConnector_pointsManager.execute("DELETE from VirtualGroup where partnerId in (SELECT id from Partner where name like '" + prefix + "%')");
        mySQLConnector_pointsManager.execute("DELETE from Partner where name like '" + prefix + "%'");

        //user manager //TODO do not work
        System.out.println("\nDELETE TEST DATA [User-manager]: user");
        for (int i = 0; i < uuids.size(); i++) {
            new UserRepositoryImpl().deleteUserFromDynamoAndPointsManager(uuids.get(i).get(0).toString());
        }
    }


    @After("@deleteDataForAuditResultsTests")
    public void deleteDataForAuditResultsTests() {

        HashMap<Integer, List> uuids = mySQLConnector_audit.getResult("SELECT epoints_uuid FROM retailer where retailer_name like '" + prefix + "%'", Arrays.asList("epoints_uuid"));

        mySQLConnector_audit.execute("DELETE FROM reward_points where audit_id in (SELECT id from audit where audit_name like '" + prefix + "%')");
        mySQLConnector_audit.execute("DELETE FROM answer where audit_id in (SELECT id from audit where audit_name like '" + prefix + "%')");
        mySQLConnector_audit.execute("DELETE FROM audit_criteria where audit_id in (SELECT id FROM audit where audit_name like '" + prefix + "%')");

        mySQLConnector_audit.execute("DELETE FROM reward_criteria where criteria_name like '" + prefix + "%'");
        mySQLConnector_audit.execute("DELETE FROM store where store_name like '" + prefix + "%'");

        mySQLConnector_audit.execute("DELETE FROM retailer_chain where retailers_id  in (SELECT id FROM retailer where retailer_name like '" + prefix + "%')");
        mySQLConnector_audit.execute("DELETE FROM retailer where retailer_name like '" + prefix + "%'");

        System.out.println("\nDELETE TEST DATA [Points-manager]: Points, Tag, VirtualGroup, Partner, user");
        //points manager
        mySQLConnector_pointsManager.execute("DELETE from Points where tagId in (" + "SELECT id from Tag where tagKey like '" + prefix + "%'" + ")");
        mySQLConnector_pointsManager.execute("DELETE from Tag where tagKey like '" + prefix + "%'");
        mySQLConnector_pointsManager.execute("DELETE from VirtualGroup where partnerId in (SELECT id from Partner where name like '" + prefix + "%')");
        mySQLConnector_pointsManager.execute("DELETE from Partner where name like '" + prefix + "%'");

        //user manager
        System.out.println("\nDELETE TEST DATA [User-manager]: user");
        for (int i = 0; i < uuids.size(); i++) {
            new UserRepositoryImpl().deleteUserFromDynamoAndPointsManager(uuids.get(i).get(0).toString());
        }
    }


    @After("@deleteCreatedRetailerAfterTest")
    public void deleteCreatedRetailerAfterTest() {
        new UserRepositoryImpl().deleteUserFromDynamoAndPointsManager(dataExchanger.getRetailer().getUuid());
        retailerActions.deleteRetailer(dataExchanger.getRetailer().getId(), 200);
    }

    @After("@deleteImportedRetailersAndStoreAfterTest")
    public void deleteImportedRetailerAfterTest() {
        System.out.println("\ndeleteImportedRetailersAndStoreAfterTest");

        HashMap<Integer, List> uuids = mySQLConnector_audit.getResult("SELECT epoints_uuid FROM retailer where retailer_name like '" + prefix + "%'", Arrays.asList("epoints_uuid"));

        System.out.println("DELETE TEST DATA [Audit-cms]: store");


        //by sql
        mySQLConnector_audit.execute("DELETE FROM reward_points where audit_id in (SELECT id from audit where audit_name like '" + prefix + "%')");
        mySQLConnector_audit.execute("DELETE FROM answer where audit_id in (SELECT id from audit where audit_name like '" + prefix + "%')");
        mySQLConnector_audit.execute("DELETE FROM audit_criteria where audit_id in (SELECT id FROM audit where audit_name like '" + prefix + "%')");

        //by api
        StoreActions storeActions = new StoreActions();
        for (Store store : dataExchanger.getStores()) {
            storeActions.deleteStore(store.getId(), 200);
        }


        mySQLConnector_audit.execute("DELETE FROM store where store_name like '" + prefix + "%'");

        System.out.println("\nDELETE TEST DATA [Audit-cms]: retailer");
        mySQLConnector_audit.execute("DELETE FROM retailer_chain where retailers_id  in (SELECT id FROM retailer where email like '" + prefix.toLowerCase() + "%')");
        mySQLConnector_audit.execute("DELETE FROM retailer where email like '" + prefix.toLowerCase() + "%'");


        System.out.println("\nDELETE TEST DATA [User-manager]: user");
        for (int i = 0; i < uuids.size(); i++) {
            new UserRepositoryImpl().deleteUserFromDynamoAndPointsManager(uuids.get(i).get(0).toString());
        }


    }

    @Given("^All needed audit cms database are restored$")
    public void allNeededAuditCmsDatabaseAreRestored() throws Throwable {
        //WHOLESALER TABLE
        mySQLConnector_audit.execute("INSERT INTO audit.wholesaler (id,wholesaler_name) VALUES ('7','API_WHOLESALER_1')");
        mySQLConnector_audit.execute("INSERT INTO audit.wholesaler (id,wholesaler_name) VALUES ('7','API_WHOLESALER_1')");

        //SUPPLIER TABLE
        mySQLConnector_audit.execute("INSERT INTO audit.supplier(id, supplier_name,partner_api_key) VALUES ('12','API_SUPPLIER_1','NkGlw5fesuJHNSqXMOx3hpXRq')");
        mySQLConnector_audit.execute("INSERT INTO audit.supplier (id,supplier_name,partner_api_key) VALUES ('13','API_SUPPLIER_2','ZWL69j9cWTv9KVkYAdqknoMSh')");

        //BRAND TABLE
        mySQLConnector_audit.execute("INSERT INTO audit.brand (id,brand_name,supplier_id) VALUES ('14','API_BRAND_1.1','12')");
        mySQLConnector_audit.execute("INSERT INTO audit.brand (id,brand_name,supplier_id) VALUES ('15','API_BRAND_2.1','13')");
        mySQLConnector_audit.execute("INSERT INTO audit.brand (id,brand_name,supplier_id) VALUES ('16','API_COCA_COLA','12')");

        //PRODUCT TABLE
        mySQLConnector_audit.execute("INSERT INTO audit.product (id,product_name,url,description,category_id,brand_id) VALUES ('1250', 'API cola 200ml', NULL, NULL, NULL, '16')");
        mySQLConnector_audit.execute("INSERT INTO audit.product (id,product_name,url,description,category_id,brand_id) VALUES ('1249', 'API cola 150ml', NULL, NULL, NULL, '16')");
        mySQLConnector_audit.execute("INSERT INTO audit.product (id,product_name,url,description,category_id,brand_id) VALUES ('1248', 'API cola 100ml', NULL, NULL, NULL, '16')");
        mySQLConnector_audit.execute("INSERT INTO audit.product (id,product_name,url,description,category_id,brand_id) VALUES ('1247', 'API_PRODUCT_1_BRAND_2.1', 'www.api.product_1_brand_2.1', NULL, NULL, '15')");
        mySQLConnector_audit.execute("INSERT INTO audit.product (id,product_name,url,description,category_id,brand_id) VALUES ('1246', 'API_PRODUCT_2_BRAND_1.1', 'www.api.product_2_brand_1.1', NULL, NULL, '14')");
        mySQLConnector_audit.execute("INSERT INTO audit.product (id,product_name,url,description,category_id,brand_id) VALUES ('1245', 'API_PRODUCT_1_BRAND_1.1', 'www.api.product_1_brand_1.1', NULL, NULL, '14')");

        //QUESTION TABLE
        mySQLConnector_audit.execute("INSERT INTO audit.question (id, question_text, adhoc_ext_id, question_type, placement, images_number, ext_rel_id, product_id, category_id) VALUES ('18', 'is product available', NULL, 'PRODUCT', 'FREEZER', NULL, NULL, '1245', NULL)");
        mySQLConnector_audit.execute("INSERT INTO audit.question (id, question_text, adhoc_ext_id, question_type, placement, images_number, ext_rel_id, product_id, category_id) VALUES ('19', 'is product available', NULL, 'PRODUCT', 'FREEZER', NULL, NULL, '1247', NULL)");
        mySQLConnector_audit.execute("INSERT INTO audit.question (id, question_text, adhoc_ext_id, question_type, placement, images_number, ext_rel_id, product_id, category_id) VALUES ('20', 'Is available hi', NULL, 'PRODUCT', 'FREEZER', NULL, NULL, '1248', NULL)");

        //RETAILER TABLE
        mySQLConnector_audit.execute("INSERT INTO audit.retailer (id, retailer_name, email, epoints_uuid, wholesaler_id) VALUES ('158', 'Test1 Test', 'retailer_1@test.test.pl', 'a3891238-2bcf-4a25-9de8-258f6080dd4a', '7')");

        //STORE TABLE
        mySQLConnector_audit.execute("INSERT INTO audit.store (id, store_name, licensed, active, audit_group, store_type, address_line_1, address_line_2, address_line_3, address_line_4, post_code, country, ext_rel_id, big_dl_branch_id, chain_id, retailer_id) VALUES ('431', 'API_TESTS_STORE_1', NULL, '1', 'A', 'API Tests type', 'Address Line 1 test', 'Address Line 2 test', 'Address Line 3 test', 'Address Line 4 test', '22-400', 'PL', '10005001000', NULL, '1', '158')");
        mySQLConnector_audit.execute("INSERT INTO audit.store (id, store_name, licensed, active, audit_group, store_type, address_line_1, address_line_2, address_line_3, address_line_4, post_code, country, ext_rel_id, big_dl_branch_id, chain_id, retailer_id) VALUES ('431', 'API_TESTS_STORE_1', NULL, '1', 'A', 'API Tests type', 'Address Line 1 test', 'Address Line 2 test', 'Address Line 3 test', 'Address Line 4 test', '22-400', 'PL', '10005001000', NULL, '1', '158')");

        //AUDIT TABLE
        mySQLConnector_audit.execute("INSERT INTO audit.audit (id, audit_name, audit_start, audit_end, ext_rel_id) VALUES ('2', 'API_AUDIT_1', '2016-05-25', '2016-06-30', NULL)");


    }
}