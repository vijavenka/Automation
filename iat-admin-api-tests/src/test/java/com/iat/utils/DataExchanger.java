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

import com.iat.domain.ChangePassword;
import com.iat.domain.EcardsConfig.*;
import com.iat.domain.EcardsConfig.milestones.MilestoneValue;
import com.iat.domain.EcardsConfig.milestones.MilestonesConfig;
import com.iat.domain.User;
import com.iat.domain.departmentsStructure.CRUDDepartment;
import com.iat.domain.ecardsAwarding.EcardsSent;

import java.util.ArrayList;
import java.util.List;

public class DataExchanger {

    private static DataExchanger dataExchanger;

    private DataExchanger() {
    }

    private String sessionId;
    private Reason reasonObject;
    private Reason globalReasonLimits;
    private PointsSharing pointsSharingObject;
    private TemplatesConfig templatesConfigObject;
    private List<MilestonesConfig> milestonesConfigList;
    private MilestoneValue milestoneValue;
    private PointsAllocation pointsAllocationObject;
    private Template template;
    private List<Template> templatesListObject;
    private User userObject;
    private ChangePassword changePasswordObject;
    private CRUDDepartment departmentObject;
    private CRUDDepartment departmentObjectBackup;
    private List<String> validDepartmentsNamesScope = new ArrayList<>();
    private EcardsSent ecardsSentObject;
    private ResponseContainer response;


    public static DataExchanger getInstance() {

        if (dataExchanger == null)
            dataExchanger = new DataExchanger();
        return dataExchanger;
    }

    public void setSessionId(String id) {
        sessionId = id;
    }

    public String getSessionId() {
        return this.sessionId != null ? sessionId : "";
    }

    public User getUserObject() {
        return userObject;
    }

    public void setUserObject(User user) {
        this.userObject = user;
    }


    public ChangePassword getChangePasswordObject() {
        return changePasswordObject;
    }

    public void setChangePasswordObject(ChangePassword changePasswordObject) {
        this.changePasswordObject = changePasswordObject;
    }

    public CRUDDepartment getDepartmentObject() {
        return departmentObject;
    }

    public void setDepartmentObject(CRUDDepartment departmentObject) {
        this.departmentObjectBackup = this.departmentObject;
        this.departmentObject = departmentObject;
    }

    public CRUDDepartment getDepartmentObjectBackup() {
        return departmentObjectBackup;
    }

    public void setDepartmentObjectBackup(CRUDDepartment departmentObjectBackup) {
        this.departmentObjectBackup = departmentObjectBackup;
    }

    public Reason getReasonObject() {
        return reasonObject;
    }

    public void setReasonObject(Reason reasonObject) {
        this.reasonObject = reasonObject;
    }

    public Reason getGlobalReasonLimits() {
        return globalReasonLimits;
    }

    public void setGlobalReasonLimits(Reason globalReasonLimits) {
        this.globalReasonLimits = globalReasonLimits;
    }

    public PointsSharing getPointsSharingObject() {
        return pointsSharingObject;
    }

    public void setPointsSharingObject(PointsSharing pointsSharingObject) {
        this.pointsSharingObject = pointsSharingObject;
    }

    public TemplatesConfig getTemplatesConfigObject() {
        return templatesConfigObject;
    }

    public void setTemplatesConfigObject(TemplatesConfig templatesConfigObject) {
        this.templatesConfigObject = templatesConfigObject;
    }

    public List<MilestonesConfig> getMilestonesConfigList() {
        return milestonesConfigList;
    }

    public void setMilestonesConfigList(List<MilestonesConfig> milestonesConfigList) {
        this.milestonesConfigList = milestonesConfigList;
    }

    public MilestoneValue getMilestoneValue() {
        return milestoneValue;
    }

    public void setMilestoneValue(MilestoneValue milestoneValue) {
        this.milestoneValue = milestoneValue;
    }

    public PointsAllocation getPointsAllocationObject() {
        return pointsAllocationObject;
    }

    public void setPointsAllocationObject(PointsAllocation pointsAllocationObject) {
        this.pointsAllocationObject = pointsAllocationObject;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public List<Template> getTemplatesListObject() {
        return templatesListObject;
    }

    public void setTemplatesListObject(List<Template> templatesListObject) {
        this.templatesListObject = templatesListObject;
    }


    public List<String> getValidDepartmentsNamesScope() {
        return validDepartmentsNamesScope;
    }

    public void setValidDepartmentsNamesScope(List<String> validDepartmentsNamesScope) {
        this.validDepartmentsNamesScope = validDepartmentsNamesScope;
    }

    public EcardsSent getEcardsSentObject() {
        return ecardsSentObject;
    }

    public void setEcardsSentObject(EcardsSent ecardsSentObject) {
        this.ecardsSentObject = ecardsSentObject;
    }


    public ResponseContainer getResponse() {
        return response;
    }

    public void setResponse(ResponseContainer response) {
        this.response = response;
    }
}

