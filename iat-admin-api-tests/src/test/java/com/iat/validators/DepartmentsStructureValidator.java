package com.iat.validators;

import com.iat.Config;
import com.iat.actions.DepartmentsStructureActions;
import com.iat.domain.UserDetailsDoorman;
import com.iat.domain.UserGroupDoorman;
import com.iat.domain.departmentsStructure.CRUDDepartment;
import com.iat.domain.departmentsStructure.Department;
import com.iat.domain.departmentsStructure.Structure;
import com.iat.domain.errors.ErrorResponse;
import com.iat.domain.errors.ErrorResponseFieldsValidation;
import com.iat.repository.UserRepository;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.utils.DataExchanger;
import com.iat.utils.JdbcDatabaseConnector;
import com.iat.utils.ResponseContainer;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DepartmentsStructureValidator {

    private DepartmentsStructureActions departmentsStructureActions = new DepartmentsStructureActions();
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private Structure structure;
    private ErrorResponse errorResponse;
    private ErrorResponseFieldsValidation errorResponseFieldsValidation;
    private UserDetailsDoorman userDetailsDoorman;
    private ResponseContainer response;
    private JdbcDatabaseConnector mySQLConnector_iatAdmin = new JdbcDatabaseConnector(Config.mysqlConnectionPoolIatAdmin);
    private UserRepository userRepository = new UserRepositoryImpl();


    public void validateProperDepartmentManagerAssigned(ResponseContainer response) {
        goDeeper(response.getAsObject(Structure.class).getDepartments());
    }


    private void goDeeper(List<Department> departments) {
        for (Department department : departments) {
            if (department.getSubdepartments().size() != 0)
                goDeeper(department.getSubdepartments());

            String departmentId = department.getId();
            System.out.println();
            System.out.println("Department: " + departmentId + " " + department.getName());
            String managerName = department.getManagerName();
            String managerUuid = mySQLConnector_iatAdmin.getSingleResult("SELECT epointsUuid FROM User WHERE departmentId = '" + departmentId + "' AND userRole = 'manager' AND enabled = 1 order by id asc LIMIT 1");

            if (managerUuid != null) {
                UserDetailsDoorman userDetailsDoorman = userRepository.findById(managerUuid);
                String managerNameToValidate = "";
                for (UserGroupDoorman userGroupDoorman : userDetailsDoorman.getUserGroupDoormen()) {
                    String managerFirstName;
                    String managerLastName;
                    String managerEmail;

                    if (String.valueOf(userGroupDoorman.getPartnerId()).equals(Config.getTestPartnerId())) {
                        managerFirstName = userGroupDoorman.getFirstName();
                        managerLastName = userGroupDoorman.getLastName();
                        managerEmail = userGroupDoorman.getEmail();

                        if (managerFirstName != null)
                            managerNameToValidate = (managerFirstName + " " + managerLastName).trim();
                        else {
                            if (managerLastName != null)
                                managerNameToValidate = managerLastName.trim();
                            else
                                managerNameToValidate = managerEmail;
                        }
                        break;
                    }
                }
                assertThat("Improper manager assigned to department", managerName, is(managerNameToValidate));
                System.out.println("Proper Manager: [response] " + managerName + "  [database] " + managerNameToValidate);
            } else
                System.out.println("Department not have any manager set");
        }
    }


    public void validateGetStructureScope(String credentials, String withRoot, ResponseContainer response) {
        Structure structure = response.getAsObject(Structure.class);

        String departmentAName = "Department level 1 [A]";
        String departmentA1Name = "Department level 2 [A.1]";
        String departmentA11Name = "Department level 3 [A.1.1]";
        String departmentBName = "Department level 1 [B]";
        String departmentCName = "Department level 1 [C]";
        String departmentDName = "Department level 1 [D]";

        List<String> departmentNamesList = asList(departmentAName, departmentBName, departmentCName, departmentDName);

        if (credentials.contains("admin_1")) {
            //Admin always see company structure
            assertThat("Structure not shown company root name", structure.getName(), is("Ecard Test API"));
            assertThat("Incorrect 1st lvl departments count", structure.getDepartments().size(), is(4));
            assertThat("Some of 1st lvl departments not included in response", structure.getDepartments(), everyItem(hasProperty("name", isIn(departmentNamesList))));
        } else {
            if (withRoot.equals("true")) {
                assertThat("Incorrect root department count", structure.getDepartments().size(), is(1));
                if (credentials.contains("api_test_manager_department_1@api.iat.admin.pl")) {
                    assertThat("Structure incorrect root name ", structure.getName(), is(departmentAName));
                    assertThat("Root department not included in response", structure.getDepartments().get(0).getName(), is(departmentAName));
                }
                if (credentials.contains("api_test_manager_subdepartment_1@api.iat.admin.pl")) {
                    assertThat("Structure incorrect root name ", structure.getName(), is(departmentA1Name));
                    assertThat("Root department not included in response", structure.getDepartments().get(0).getName(), is(departmentA1Name));
                }
            } else {
                if (credentials.contains("api_test_manager_department_1@api.iat.admin.pl")) {
                    assertThat("Structure incorrect root name ", structure.getName(), is(departmentAName));
                    assertThat("Root department included in response", structure.getDepartments().get(0).getName(), is(departmentA1Name));
                }
                if (credentials.contains("api_test_manager_subdepartment_1@api.iat.admin.pl")) {
                    assertThat("Structure incorrect root name ", structure.getName(), is(departmentA1Name));
                    assertThat("Root department included in response", structure.getDepartments().get(0).getName(), is(departmentA11Name));
                }
            }
        }
    }


    public void validateGetValidStructureScope(String credentials, ResponseContainer response) {
        Structure structure = response.getAsObject(Structure.class);

        String departmentAName = "Department level 1 [A]";
        String departmentA1Name = "Department level 2 [A.1]";
        String departmentA11Name = "Department level 3 [A.1.1]";
        String departmentBName = "Department level 1 [B]";
        String departmentCName = "Department level 1 [C]";
        String departmentDName = "Department level 1 [D]";

        List<String> departmentNamesList = asList(departmentAName, departmentBName, departmentCName, departmentDName);

        if (dataExchanger.getPointsSharingObject().getManagerSharePointsRange().equals("SAME_COMPANY") ||
                credentials.contains("admin_1")) {
            System.out.println("SAME_COMPANY / admins entered");
            assertThat("Structure not shown company root name", structure.getName(), is("Ecard Test API"));
            assertThat("Incorrect 1st lvl departments count", structure.getDepartments().size(), is(4));
            assertThat("Some of 1st lvl departments not included in response", structure.getDepartments(), everyItem(hasProperty("name", isIn(departmentNamesList))));
        } else {
            if (credentials.contains("api_test_manager_department_1@api.iat.admin.pl")) {
                assertThat("Incorrect 1st lvl departments count", structure.getDepartments().size(), is(1));
                assertThat("Structure not restricted to department level", structure.getName(), is(departmentAName));
                assertThat("Manager department not included in response", structure.getDepartments().get(0).getName(), is(departmentAName));
            }
            if (credentials.contains("api_test_manager_subdepartment_1@api.iat.admin.pl")) {
                assertThat("Incorrect 1st lvl departments count", structure.getDepartments().size(), is(1));
                assertThat("Structure not restricted to department level", structure.getName(), is(departmentA1Name));
                assertThat("Manager department not included in response", structure.getDepartments().get(0).getName(), is(departmentA1Name));
            }
        }
    }


    public void checkIfDepartmentAvailableOnDepartmentsStructure(boolean active, String department) throws IOException {
        if (department.contains("[A"))
            checkIfDepartmentAvailableOnDepartmentsStructure(active, 0);
        else if (department.contains("[B"))
            checkIfDepartmentAvailableOnDepartmentsStructure(active, 1);
        else if (department.contains("[C"))
            checkIfDepartmentAvailableOnDepartmentsStructure(active, 2);
        else if (department.contains("[D"))
            checkIfDepartmentAvailableOnDepartmentsStructure(active, 3);
    }

    private void checkIfDepartmentAvailableOnDepartmentsStructure(boolean active, int department) throws IOException {
        response = departmentsStructureActions.getDepartmentsValidStructure(200);
        structure = response.getAsObject(Structure.class);

        boolean departmentFound = validateDepartmentExists(structure.getDepartments().get(department), dataExchanger.getDepartmentObject().getId(), active);
        assertThat("Department not found", departmentFound);
    }

    private boolean validateDepartmentExists(Department department, String departmentId, boolean active) {

        for (Department subdepartment : department.getSubdepartments()) {
            if (subdepartment.getId().equals(departmentId)) {
                assertThat("Wrong department name", subdepartment.getName(), is(dataExchanger.getDepartmentObject().getName()));
                assertThat("Department activity state is wrong", subdepartment.getActive(), is(active));
                assertThat("Department parentId is invalid!", department.getId(), is(dataExchanger.getDepartmentObject().getParentId()));
                return true;
            }

            if (subdepartment.getSubdepartments().size() > 0 && validateDepartmentExists(subdepartment, departmentId, active))
                return true;
        }

        return false;
    }

    public void validateErrorResponseMessageOfDepartmentManageActions(ResponseContainer response, int status, String error, String message) {
        errorResponse = response.getAsObject(ErrorResponse.class);

        if (message.contains("parentDepartmentIdToBeReplace"))
            message = message.replace("parentDepartmentIdToBeReplace", dataExchanger.getDepartmentObjectBackup().getId());
        if (message.contains("departmentIdToBeReplace"))
            message = message.replace("departmentIdToBeReplace", dataExchanger.getDepartmentObject().getId());

        assertThat("Wrong response status", errorResponse.getStatus(), is(status));
        assertThat("Wrong response error", errorResponse.getError(), is(error));
        assertThat("Wrong response message", errorResponse.getMessage(), is(message));
    }

    public void validateErrorResponseMessageOfDepartmentManageActionsFieldsValidation(ResponseContainer response, String fieldName, String message) {
        errorResponseFieldsValidation = response.getAsObject(ErrorResponseFieldsValidation.class);

        assertThat("Wrong response fieldName", errorResponseFieldsValidation.getErrors().get(0).getFieldName(), is(fieldName));
        assertThat("Wrong response message", errorResponseFieldsValidation.getErrors().get(0).getMessage(), is(message));
    }

    public void validateUserDepartmentNameCorrectness(String userId) {
        if (userId.equals("previous_call"))
            userDetailsDoorman = userRepository.findById(dataExchanger.getUserObject().getId());
        else
            userDetailsDoorman = userRepository.findById(userId);

        boolean groupFound = false;
        for (UserGroupDoorman userGroupDoorman : userDetailsDoorman.getUserGroupDoormen()) {
            groupFound = userGroupDoorman.getPartnerId().toString().equals(Config.getTestPartnerId());

            if (!groupFound) continue;

            assertThat("User department name was not updated after department name update", userGroupDoorman.getDepartmentName(), is(dataExchanger.getDepartmentObject().getName()));
            break;
        }

        assertThat("Not found group for Partner: " + Config.getTestPartnerId(), groupFound);
    }

    public void validateUserManagerCorrectness(String userId, String managerName) {
        if (userId.equals("previously_created"))
            userDetailsDoorman = userRepository.findById(dataExchanger.getUserObject().getId());
        else
            userDetailsDoorman = userRepository.findById(userId);

        boolean groupFound = false;
        for (UserGroupDoorman userGroupDoorman : userDetailsDoorman.getUserGroupDoormen()) {
            groupFound = userGroupDoorman.getPartnerId().toString().equals(Config.getTestPartnerId());

            if (!groupFound) continue;

            assertThat("User manager is invalid!", userGroupDoorman.getManagerName(), is(managerName));
            break;
        }

        assertThat("Not found group for Partner: " + Config.getTestPartnerId(), groupFound);
    }

    public void validateCorrectnessOfDepartmentCreation(CRUDDepartment requestBody, CRUDDepartment responseBody) {
        assertThat("Wrong department name in response: ", requestBody.getName(), is(responseBody.getName()));
        assertThat("Wrong parent department id in response: ", requestBody.getParentId(), is(responseBody.getParentId()));
    }


}