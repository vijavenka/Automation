package com.iat.restful_services.jdbc;

/**
 * Created with IntelliJ IDEA.
 * User: LPanusz
 * Date: 04.06.13
 * Time: 13:54
 * To change this template use File | Settings | File Templates.
 */
public class WSTagTable {

    private int id;
    private String createdAt;
    private String updatedAt;
    private int cap;
    private String frequency;
    private long pendingAutoConfirmTimeout;
    private String tagKey;
    private String tagStatus;
    private int partnerId;

    public WSTagTable(int id, String createdAt, String updatedAt, int cap, String frequency, long pendingAutoConfirmTimeout, String tagKey, String tagStatus, int partnerId) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.cap = cap;
        this.frequency = frequency;
        this.pendingAutoConfirmTimeout = pendingAutoConfirmTimeout;
        this.tagKey = tagKey;
        this.tagStatus = tagStatus;
        this.partnerId = partnerId;
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

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public long getPendingAutoConfirmTimeout() {
        return pendingAutoConfirmTimeout;
    }

    public void setPendingAutoConfirmTimeout(long pendingAutoConfirmTimeout) {
        this.pendingAutoConfirmTimeout = pendingAutoConfirmTimeout;
    }

    public String getTagKey() {
        return tagKey;
    }

    public void setTagKey(String tagKey) {
        this.tagKey = tagKey;
    }

    public String getTagStatus() {
        return tagStatus;
    }

    public void setTagStatus(String tagStatus) {
        this.tagStatus = tagStatus;
    }

    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    public String buildWSTagTableInsertQuery() {
        String query = "INSERT INTO `points_manager`.`Tag`(`id`,`createdAt`,`updatedAt`,`cap`,`frequency`,`pendingAutoConfirmTimeout`,`tagKey`,`tagStatus`,`partnerId`)" + " VALUES " + "(" +
                this.id + ",'" + this.createdAt + "','" + this.updatedAt + "'," + this.cap + ",'" + this.frequency + "'," + this.pendingAutoConfirmTimeout + ",'" + this.tagKey + "','" +
                this.tagStatus + "'," + this.partnerId + ");";

        return query;
    }

    public String buildWSTabTableStaticInsertQuery() {
        String query = "insert into tag(id, createdAt, cap, frequency, tagKey, tagStatus, partnerId) values(1, '2013-05-01 15:41:56', 50, 'MONTHLY', 'TAG1', 'ACTIVE', 1);\n" +
                "insert into tag(id, createdAt, cap, frequency, tagKey, tagStatus, partnerId) values(2, '2013-05-01 15:41:56', 100, 'MONTHLY', 'TAG2', 'ACTIVE', 1);\n" +
                "insert into tag(id, createdAt, cap, frequency, tagKey, tagStatus, partnerId) values(3, '2013-05-02 15:41:56', 100, 'MONTHLY', 'TAG_PR2', 'ACTIVE', 2);\n" +
                "insert into tag(id, createdAt, cap, frequency, tagKey, tagStatus, partnerId) values(4, '2013-05-02 15:41:56', 10000, 'ONCE', 'FILL_PERSONAL_SECTION', 'ACTIVE', 1);\n" +
                "insert into tag(id, createdAt, cap, frequency, tagKey, tagStatus, partnerId) values(5, '2013-05-02 15:41:56', 10000, 'ONCE', 'FILL_CONTACT_SECTION', 'ACTIVE', 1);";

        return query;
    }

    public String buildWSTagTableSelectQuery() {
        String query = "SELECT * FROM points_manager.Tag;";
        return query;
    }

    public String buildWSDeleteAllTagsQuery() {
        String query = "DELETE FROM points_manager.Tag;";
        return query;
    }
}
