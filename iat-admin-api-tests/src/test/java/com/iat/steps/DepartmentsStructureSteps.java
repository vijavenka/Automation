package com.iat.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.Config;
import com.iat.actions.DepartmentsStructureActions;
import com.iat.domain.departmentsStructure.CRUDDepartment;
import com.iat.domain.departmentsStructure.Structure;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;
import com.iat.validators.DepartmentsStructureValidator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Date;

public class DepartmentsStructureSteps {

    private DepartmentsStructureActions departmentsStructureActions = new DepartmentsStructureActions();
    private DepartmentsStructureValidator departmentsStructureValidator = new DepartmentsStructureValidator();
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private ObjectMapper mapper = new ObjectMapper();
    private ResponseContainer response;
    private Structure structure;

    @When("^Get structure call is made '(.+?)'$")
    public void getStructure(String withRoot) throws Throwable {
        response = departmentsStructureActions.getDepartmentsStructure(withRoot, 200);
    }

    @When("^Get valid structure call is made$")
    public void getValidStructure() throws Throwable {
        response = departmentsStructureActions.getDepartmentsValidStructure(200);
    }

    @When("^Get structure call is made for incorrect data '(.+?)', '(.+?)'$")
    public void getStructureErrorMessage(String withRoot, int status) throws Throwable {
        response = departmentsStructureActions.getDepartmentsStructure(withRoot, status);
        dataExchanger.setResponse(response);
    }


    //Get structure - contract validation
    //Grant departments:
    //    ADMIN & SUPER ADMIN- can see & grant to all of the users within their partner group;
    //    MANAGER- can only see & grant to his department's sub-departments;

    @Then("^Get structure call is not affected by the managerSharePointsRange '(.+?)', '(.+?)'$")
    public void getStructureNotAffectedByScope(String credentials, String withRoot) throws Throwable {
        departmentsStructureValidator.validateGetStructureScope(credentials, withRoot, response);
    }

    @Then("^Proper managerName is assigned to each department$")
    public void validateProperManagerReturnedInDepartment() throws Throwable {
        departmentsStructureValidator.validateProperDepartmentManagerAssigned(response);
    }

    //Valid structure is used on Awarding users screen
    //Grant users:
    //    ADMIN & SUPER-ADMIN & MGR-ADMIN -> can see & grant to all users within their company;
    //    MANAGER -> can only see & grant to users in his department and sub departments --> unless it's set differently in the config (manager's scope)
    @Then("^Get valid structure call returns proper results '(.+?)'$")
    public void getValidStructureMatchContract(String credentials) throws Throwable {
        departmentsStructureValidator.validateGetValidStructureScope(credentials, response);
    }


    @When("^New department is created for '(.+?)' and status (\\d+)$")
    public void createNewDepartment(String jsonBody, int status) throws Throwable {
        CRUDDepartment crudDepartment = mapper.readValue(jsonBody, CRUDDepartment.class);
        response = departmentsStructureActions.createNewDepartment(crudDepartment, status);

        if (status == 201) {
            departmentsStructureValidator.validateCorrectnessOfDepartmentCreation(response.getAsObject(CRUDDepartment.class), crudDepartment);

            crudDepartment = response.getAsObject(CRUDDepartment.class);
            dataExchanger.setDepartmentObject(crudDepartment);
        }
    }

    @Then("^New department will be available and (.+?) on departments structure list$")
    public void checkIfDepartmentProperlyCreated(String active) throws Throwable {
        boolean deptActive = active.equals("active");
        departmentsStructureValidator.checkIfDepartmentAvailableOnDepartmentsStructure(deptActive, "[D]");
    }

    @Then("^New department will be available and (.+?) on departments ([^\"]*) structure list$")
    public void newDepartmentWillBeAvailableAndActiveOnDepartmentsStructureList(String active, String department) throws Throwable {
        boolean deptActive = active.equals("active");
        departmentsStructureValidator.checkIfDepartmentAvailableOnDepartmentsStructure(deptActive, department);
    }

    @Then("^Created department can be properly soft deleted when no users assigned to it$")
    public void softDeleteCreatedDepartment() throws Throwable {
        response = departmentsStructureActions.deleteDepartment(dataExchanger.getDepartmentObject().getId(), 200);
        departmentsStructureValidator.checkIfDepartmentAvailableOnDepartmentsStructure(false, "[D]");
    }

    @Then("^Error message will be thrown with '(\\d+)', '(.+?)', '(.+?)'$")
    public void checkDepartmentManageErrorMessage(int status, String error, String message) throws Throwable {
        departmentsStructureValidator.validateErrorResponseMessageOfDepartmentManageActions(response, status, error, message);
    }

    @Then("^Error message will be thrown for department create/update/delete '(.+?)', '(.+?)'$")
    public void checkDepartmentCreationErrorMessage(String fieldName, String message) throws Throwable {
        departmentsStructureValidator.validateErrorResponseMessageOfDepartmentManageActionsFieldsValidation(response, fieldName, message);
    }


    @When("^Created department name will be updated with '(.+?)' and status (\\d+)$")
    public void updateExistingDepartmentName(String newName, int status) throws Throwable {

        if (newName.equals("random_name"))
            dataExchanger.getDepartmentObject().setName("updatedDepartmentName_" + new Date().getTime());
        else if (newName.equals("existing_random_department_name")) {
            response = departmentsStructureActions.getDepartmentsValidStructure(200);
            structure = response.getAsObject(Structure.class);
            dataExchanger.getDepartmentObject().setName(structure.getDepartments().get(1).getName());
        }

        response = departmentsStructureActions.updateDepartmentName(dataExchanger.getDepartmentObject(), status);
    }

    @When("^(.+?) department will be moved to (.+?), (\\d+)$")
    public void moveExistingDepartmentName(String departmentName, String parentName, int status) throws Throwable {
        String parentId;
        CRUDDepartment department;

        if (parentName.toLowerCase().contains("latest"))
            parentId = dataExchanger.getDepartmentObject().getId();
        else
            parentId = Config.getDepartmentIdForName(parentName);

        if (departmentName.equalsIgnoreCase("backup"))
            department = dataExchanger.getDepartmentObjectBackup();
        else
            department = dataExchanger.getDepartmentObject();
        department.setParentId(parentId);
        response = departmentsStructureActions.moveDepartment(department, status);
    }

    @Then("^User profile was properly updated with new department name$")
    public void checkDepartmentNameInUserVirtualGroup() throws Throwable {
        departmentsStructureValidator.validateUserDepartmentNameCorrectness("previous_call");
    }

    @Then("^User profile was properly updated with manager name (.+?)$")
    public void checkUserManagerInUserVirtualGroup(String managerName) throws Throwable {
        departmentsStructureValidator.validateUserManagerCorrectness("previously_created", managerName);
    }

    @When("^Created department will be deleted with '(.+?)' and status (\\d+)$")
    public void deleteDepartment(String departmentId, int status) throws Throwable {
        if (departmentId.equalsIgnoreCase("existing_previously_created_department"))
            departmentId = dataExchanger.getDepartmentObject().getId();
        else if (departmentId.equalsIgnoreCase("existing_previously_created_department_parent"))
            departmentId = dataExchanger.getDepartmentObjectBackup().getId();

        response = departmentsStructureActions.deleteDepartment(departmentId, status);
    }

    @Given("^New department is created under previously created department for '(.+?)' and status (\\d+)$")
    public void createNewDepartmentUnderPreviouslyCreatedDepartment(String jsonBody, int status) throws Throwable {
        createNewDepartment(jsonBody, status);
    }

}