package com.iat.actions.ecardsManagement;

import com.iat.Config;
import com.iat.controller.ecardsManagement.EcardsPointsAllocationController;
import com.iat.domain.EcardsConfig.PointsAllocation;
import com.iat.domain.EcardsConfig.PointsAllocations;
import com.iat.steps.LoginSteps;
import com.iat.utils.ResponseContainer;

import java.util.List;

import static com.iat.utils.DateTimeUtil.Format;
import static com.iat.utils.DateTimeUtil.getDatesFromParams;
import static com.iat.utils.ResponseContainer.initResponseContainer;
import static com.iat.utils.ResponseContainer.initResponseContainerLanding;


public class EcardsPointsAllocationActions {

    private LoginSteps loginSteps = new LoginSteps();
    private EcardsPointsAllocationController ecardsPointsAllocationController = new EcardsPointsAllocationController();


    public ResponseContainer getEcardsPartners(int status) {
        return initResponseContainer(ecardsPointsAllocationController.getEcardsPartners(status));
    }

    public ResponseContainer getEcardsAllocationsStats(String level, String params, int status) {
        String[] params2 = params.split(",");
        String sortField = params2[0];
        String ascending = params2[1];
        String page = params2[2];
        String maxResults = params2[3];
        String who = params2[4];
        String from = params2[5];
        String to = params2[6];
        String dateFrom = params2[7];
        String dateTo = params2[8];
        String description = params2[9];
        String amount = params2[10];
        String points = params2[11];

        return initResponseContainer(ecardsPointsAllocationController.getEcardsAllocationsStats(level, sortField, ascending,
                page, maxResults, who, from, to, dateFrom, dateTo, description, amount, points, status));
    }

    public ResponseContainer getEcardsAllocationsStats(String level, int status) {
        return getEcardsAllocationsStats(level, "null,null,null,null,null,null,null,null,null,null,null,null", status);
    }

    public ResponseContainer getEcardsAllocationLimit(int status) {
        return initResponseContainer(ecardsPointsAllocationController.getEcardsAllocationLimit(status));
    }

    public ResponseContainer getEcardsAllocationLanding(int status, String ToValidate) {
        return initResponseContainerLanding(ecardsPointsAllocationController.getEcardsAllocationLanding(status, ToValidate), ToValidate);
    }

    public ResponseContainer getEcardsAllocations(String level, String params, int status) {
        String[] params2 = params.split(",");
        String sortField = params2[0];
        String ascending = params2[1];
        String page = params2[2];
        String maxResults = params2[3];
        String who = params2[4];
        String from = params2[5];
        String to = params2[6];
        String dateFrom = params2[7];
        String dateTo = params2[8];
        String description = params2[9];
        String amount = params2[10];
        String points = params2[11];

        return initResponseContainer(ecardsPointsAllocationController.getEcardsAllocations(level, sortField, ascending,
                page, maxResults, who, from, to, dateFrom, dateTo, description, amount, points, status));
    }

    public ResponseContainer setEcardsAllocations(String level, PointsAllocations pointsAllocations, int status) {
        return initResponseContainer(ecardsPointsAllocationController.setEcardsAllocations(level, pointsAllocations, status));
    }

    public void allocateAdditionalPointsForPartner() throws Throwable {
        loginSteps.iatAdminUserLogIn("api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd");
        setEcardsAllocations("partner", new PointsAllocations(new PointsAllocation(Config.getTestPartnerId(), "Additional allocation to PARTNER before grant department by managers/admins", "2500")), 201);
    }


    public ResponseContainer postEcardsAwardExport(String dateFrom, String dateTo, int status) {
        return initResponseContainer(ecardsPointsAllocationController.postEcardsAwardExport(dateFrom, dateTo, status));
    }

    public ResponseContainer postEcardsAwardExport(String params, int status) {
        List<String> dates = getDatesFromParams(params, Format.yyyyMMddTHHmmssXXX);

        return postEcardsAwardExport(dates.get(0), dates.get(1), status);
    }

    public ResponseContainer postEcardsAllocPartnerExport(String dateFrom, String dateTo, int status) {
        return initResponseContainer(ecardsPointsAllocationController.postEcardsAllocPartnerExport(dateFrom, dateTo, status));
    }

    public ResponseContainer postEcardsAllocPartnerExport(String params, int status) {
        List<String> dates = getDatesFromParams(params, Format.yyyyMMddTHHmmssXXX);

        return postEcardsAllocPartnerExport(dates.get(0), dates.get(1), status);
    }

    public ResponseContainer postEcardsAllocDepartmentExport(String dateFrom, String dateTo, int status) {
        return initResponseContainer(ecardsPointsAllocationController.postEcardsAllocDepartmentExport(dateFrom, dateTo, status));
    }

    public ResponseContainer postEcardsAllocDepartmentExport(String params, int status) {
        List<String> dates = getDatesFromParams(params, Format.yyyyMMddTHHmmssXXX);

        return postEcardsAllocDepartmentExport(dates.get(0), dates.get(1), status);
    }


    public ResponseContainer getExportFile(String filename, int status) {
        return initResponseContainer(ecardsPointsAllocationController.getExportFile(filename, status), "RESPONSE: <is a xls file content>");
    }

    public ResponseContainer getExportFile(String filename) {
        return getExportFile(filename, 200);
    }

}
