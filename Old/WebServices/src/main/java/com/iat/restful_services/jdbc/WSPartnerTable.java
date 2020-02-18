package com.iat.restful_services.jdbc;

/**
 * Created with IntelliJ IDEA.
 * User: LPanusz
 * Date: 04.06.13
 * Time: 14:10
 * To change this template use File | Settings | File Templates.
 */
public class WSPartnerTable {

    private int id;
    private String createdAt;
    private String updatedAt;
    private int currentTotal;
    private String accessKey;
    private String name;
    private String paymentType;     //PRE_PAY, POST_PAY
    private long pendingAutoConfirmTimeout;
    private String shortName;
    private int active;
    private String siteUrl;

    public WSPartnerTable(int id, String createdAt, String updatedAt, int currentTotal, String accessKey, String name, String paymentType, long pendingAutoConfirmTimeout, String shortName,
                          int active, String siteUrl) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.currentTotal = currentTotal;
        this.accessKey = accessKey;
        this.name = name;
        this.paymentType = paymentType;
        this.pendingAutoConfirmTimeout = pendingAutoConfirmTimeout;
        this.shortName = shortName;
        this.active = active;
        this.siteUrl = siteUrl;
    }

    public WSPartnerTable() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getCurrentTotal() {
        return currentTotal;
    }

    public void setCurrentTotal(int currentTotal) {
        this.currentTotal = currentTotal;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public long getPendingAutoConfirmTimeout() {
        return pendingAutoConfirmTimeout;
    }

    public void setPendingAutoConfirmTimeout(long pendingAutoConfirmTimeout) {
        this.pendingAutoConfirmTimeout = pendingAutoConfirmTimeout;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String buildWSPartnerTableInsertQuery() {
        String query = "INSERT INTO `points_manager`.`Partner` (`id`,`createdAt`,`updatedAt`,`currentTotal`,`accessKey`,`name`,`paymentType`,`pendingAutoConfirmTimeout`,`shortName`," +
                "`active`,`siteUrl`)" + " VALUES " + "(" + this.id + ",'" + this.createdAt + "','" + this.updatedAt + "'," + this.currentTotal + ",'" + this.accessKey + "','" + this.name + "','" +
                this.paymentType + "'," + this.pendingAutoConfirmTimeout + ",'" + this.shortName + "'," + this.active + ",'" + this.siteUrl + "');";

        return query;
    }

    public String buildWSPartnerStaticInsertQuery() {
        String query = "insert into Partner(id, createdAt, currentTotal, accessKey, name, paymentType, pendingAutoConfirmTimeOut, shortName, active) values(1, now(), 10000, 'accessKey', 'Partner 1', 'PRE_PAY', 2592000000, 'P1', true);\n" +
                "insert into Partner(id, createdAt, currentTotal, accessKey, name, paymentType, pendingAutoConfirmTimeOut, shortName, active) values(2, now(), 0, 'accessKey1', 'Partner 1', 'POST_PAY', 2592000000, 'P2', true);";

        return query;
    }

    public String buildWSPartnerTableSelectQuery() {
        String query = "SELECT * FROM points_manager.Partner;";
        return query;
    }

    public String buildWSDeleteAllPartnersQuery() {
        String query = "DELETE FROM points_manager.Partner;";
        return query;
    }
}
