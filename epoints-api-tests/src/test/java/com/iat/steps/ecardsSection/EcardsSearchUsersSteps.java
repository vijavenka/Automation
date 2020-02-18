package com.iat.steps.ecardsSection;

import com.iat.Config;
import com.iat.actions.ecardsSection.EcardsSearchUsersActions;
import com.iat.domain.Ecards.Department;
import com.iat.domain.Ecards.Structure;
import com.iat.domain.UserDetails;
import com.iat.domain.UserGroup;
import com.iat.repository.UserRepository;
import com.iat.repository.impl.IatAdminSettingsRepositoryImpl;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.utils.ResponseContainer;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EcardsSearchUsersSteps {

    private EcardsSearchUsersActions ecardsSearchUsersActions = new EcardsSearchUsersActions();
    private ResponseContainer response;
    private UserRepository userRepository = new UserRepositoryImpl();
    private List<String> departmentNamesList;


    @When("^Ecards Users search call with keyword '(.+?)' is made$")
    public void getEcardsUsersSearch(String search) throws Throwable {

        response = ecardsSearchUsersActions.getEcardsUsers(search, 200);
    }

    @Then("^Valid ecards users are returned according partner ecards pointsSharing '(.+?)' settings '(.+?)', '(.+?)'$")
    public void getEcardsUsersSearchValidateResponse(String pointsSharing, String user, String search) throws Throwable {
        List<String> extractedUuids = response.getList("results.findAll{it.uuid != null}.uuid");
        List<String> validDepartmentNamesListForPointsSharingScope = getListOfValidSearchDepartments(pointsSharing, user.split(",")[0]);

        assertThat("There is no results for search (user: " + user.split(",")[0] + ") and keyword: " + search, extractedUuids, is(not(empty())));
        List<Boolean> isUserFromCompanyList = new ArrayList<>();

        for (String uuid : extractedUuids) {
            UserDetails userDetails = userRepository.findById(uuid);
            assertThat("Not every user contains searched keyword!", userDetails.getFirstName() + " " + userDetails.getLastName() + " " + userDetails.getEmail(), containsString(search));

            for (UserGroup userGroup : userDetails.getUserGroups()) {
                if (String.valueOf(userGroup.getPartnerId()).equals(Config.getTestPartnerId())) {
                    isUserFromCompanyList.add(true);

                    //validate if department is in user scope
                    assertThat("Results improper! not from user scope", userGroup.getDepartmentName(), isIn(validDepartmentNamesListForPointsSharingScope));
                    break;
                }
            }

            if (pointsSharing.equals("SAME_COMPANY") || pointsSharing.equals("SAME_DEPARTMENT"))
                assertThat("Returned user is not from company", isUserFromCompanyList, everyItem(is(true)));

            if (pointsSharing.equals("ALL_USERS")) {
                assertThat("For ALL_USERS missing at least one user from company", isUserFromCompanyList, hasItem(is(true)));
                assertThat("For ALL_USERS missing at least one user outside company", isUserFromCompanyList, hasItem(is(false)));
            }
        }
    }

    private List<String> getListOfValidSearchDepartments(String pointsSharing, String userEmail) {
        if (!pointsSharing.equals("ALL_USERS")) {
            departmentNamesList = new ArrayList<>();
            Structure structure = new IatAdminSettingsRepositoryImpl().getDepartmentsStructure("true", Config.getSuperAdminCredentialsForIAT_Admin()).getAsObject(Structure.class);

            if (pointsSharing.equals("SAME_DEPARTMENT")) {
                ResponseContainer responseContainer = new ResponseContainer(given().when().get(Config.getDoormanUrl() + "/users?email=" + userEmail).then());
                String userDepartmentName = responseContainer.getString("userGroups.find{it.partnerId == 7777 }.departmentName");
                getDepartmentNames(singletonList(getValidRootDepartmentFromEntireStructure(structure.getDepartments(), userDepartmentName)));
            }
            if (pointsSharing.equals("SAME_COMPANY"))
                //entire company structure is required
                getDepartmentNames(structure.getDepartments());
            return departmentNamesList;
        } else
            return new ArrayList<>();
    }

    private void getDepartmentNames(List<Department> departments) {
        for (Department department : departments) {
            if (department.getSubdepartments().size() != 0)
                getDepartmentNames(department.getSubdepartments());

            System.out.println("Extract Name: " + department.getName());
            departmentNamesList.add(department.getName());
        }
    }

    private Department getValidRootDepartmentFromEntireStructure(List<Department> departments, String userDepartmentName) {
        Department toReturn;
        for (Department department : departments) {
            if (department.getName().equals(userDepartmentName)) {
                System.out.println("Department Root Found!: " + department.getName());
                toReturn = department;
            } else
                toReturn = getValidRootDepartmentFromEntireStructure(department.getSubdepartments(), userDepartmentName);
            if (toReturn != null) return toReturn;
        }
        return null;
    }
}
