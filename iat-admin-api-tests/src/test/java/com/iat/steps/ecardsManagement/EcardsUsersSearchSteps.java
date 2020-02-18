package com.iat.steps.ecardsManagement;

import com.iat.Config;
import com.iat.actions.DepartmentsStructureActions;
import com.iat.actions.ecardsManagement.EcardsUsersSearchActions;
import com.iat.domain.SearchUser.SearchUser;
import com.iat.domain.User;
import com.iat.domain.departmentsStructure.Department;
import com.iat.steps.UsersSteps;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EcardsUsersSearchSteps {

    private UsersSteps usersSteps = new UsersSteps();
    private DepartmentsStructureActions departmentsStructureActions = new DepartmentsStructureActions();
    private EcardsUsersSearchActions ecardsUsersSearchActions = new EcardsUsersSearchActions();
    private ResponseContainer response;
    private DataExchanger dataExchanger = DataExchanger.getInstance();


    @When("^Get users from department call for '(.+?)', '(.+?)'$")
    public void getDepartmentUsers(String departmentName, String search) throws Throwable {
        response = ecardsUsersSearchActions.getEcardsUser(Config.getDepartmentIdForName(departmentName), search, 200);
        dataExchanger.setResponse(response);
    }

    @Then("^Get users from department call returns proper data '(.+?)', '(.+?)'$")
    public void getDepartmentUsersMatchContract(String search, String departmentName) throws Throwable {
        if (departmentName.equals("Department from other company") || departmentName.equals("Department not exists")) {
            assertThat("For Department Id 'from other company' or 'not existing' results should be empty!", response, is("[]"));
        } else {
            List<SearchUser> usersListResponse = response.getList("", SearchUser.class);

            //check if only users from  specific department are returned
            for (int i = 0; i < usersListResponse.size(); i++)
                assertThat("Search results [" + i + "] user is not from department: " + departmentName, usersListResponse.get(i).getDepartmentName().equals(departmentName));

            if (!search.equals("null")) {
                //check if results after use search keyword contains searched term
                boolean isContains;
                for (int i = 0; i < usersListResponse.size(); i++) {
                    isContains = false;
                    String createName = usersListResponse.get(i).getName() + " " + usersListResponse.get(i).getEmail();
                    if ((createName).toLowerCase().contains(search.toLowerCase()))
                        isContains = true;

                    assertThat("Search results [" + i + "] not contains search term: " + search + "\nname:  " + createName, isContains);
                }
            }
            assertThat("There is no results for department: " + departmentName, usersListResponse.size(), not(0));
        }
    }

    @Then("^Get manager from department call returns proper data '(.+?)', '(.+?)'$")
    public void getManagersSearchAndValidate(String manager, boolean sharePointsWithManager) throws Throwable {
        List<String> extractedEmail = response.getList("email");

        if (sharePointsWithManager) {
            boolean isContains = false;
            for (int i = 0; i < extractedEmail.size(); i++) {
                if (extractedEmail.get(i).contains(manager)) {
                    isContains = true;
                    break;
                }
            }
            assertThat("Search results for sharePointsWithManager: " + sharePointsWithManager + " not contains manager: " + manager, isContains);

        } else {
            for (int i = 0; i < extractedEmail.size(); i++) {
                if (extractedEmail.get(i).contains(manager))
                    assertThat("Search results [" + i + "] for sharePointsWithManager: " + sharePointsWithManager + " contains manager: " + manager, false);

            }
        }
    }


    @When("^Get users from department call for incorrect data '(.+?)', '(.+?)', '(.+?)'$")
    public void getDepartmentUsersErrorMessage(String departmentName, String search, int status) throws Throwable {
        response = ecardsUsersSearchActions.getEcardsUser(Config.getDepartmentIdForName(departmentName), search, status);
        dataExchanger.setResponse(response);
    }


    @When("^Get users from company call for '(.+?)'$")
    public void getCompanyUsers(String search) throws Throwable {
        response = ecardsUsersSearchActions.searchCompanyUsers(search, 200);

    }

    @Then("^Get users from company call returns proper data '(.+?)', '(.+?)', '(.+?)'$")
    public void getCompanyUsersMatchSearch(String search, String managerSharePointsRange, String resultsCondition) throws Throwable {
        List<SearchUser> usersListResponse = response.getList("", SearchUser.class);

        if (resultsCondition.equals("searchHimself")) {
            for (SearchUser user : usersListResponse)
                assertThat("Current logged user returned in search results", (user.getName() + user.getEmail()), not(containsString(search)));
        }

        if (resultsCondition.equals("outOfScope"))
            assertThat("Users which are not in logged user scope are returned", usersListResponse.size(), is(0));

        //Validation results are in Logged user scope
        if (resultsCondition.equals("keyword")) {

            if (!search.equals("null")) {
                //keyword validation
                List<String> searchSplittedList = asList(search.split(" "));
                searchSplittedList.replaceAll(String::toLowerCase);
                for (SearchUser user : usersListResponse) {
                    List<String> responseSplittedList = asList((user.getName() + user.getEmail()).split(" "));
                    responseSplittedList.replaceAll(String::toLowerCase);

                    assertThat("Result user: " + user + " not contains keyword: " + search, searchSplittedList.stream().allMatch(searchSplitted -> responseSplittedList.stream().anyMatch(responseSplitted -> responseSplitted.contains(searchSplitted))));

                }
            }


            //to validate scope: Manager scope depends of managerSharePointsRange if Admin or superadmin then managerSharePointsRange is ignored and always entire company
            usersSteps.iatAdminUserGetProfile();
            if (dataExchanger.getUserObject().getAdminRole().equals("NONE")) {
                // -> MANAGER
                if (managerSharePointsRange.equals("SAME_COMPANY")) {
                    // -> Can be Entire COMPANY scope -> No validation is required
                } else {
                    // -> department + subs scope
                    List<Department> departments = departmentsStructureActions.getDepartmentsValidStructure(200).getList("departments", Department.class);
                    List<Department> validDepartmentsList = ecardsUsersSearchActions.extractValidDepartmentsList(departments);
                    List<String> validDepartmentsNames = validDepartmentsList.stream().map(Department::getName).collect(toList());

                    for (SearchUser user : usersListResponse)
                        assertThat("Out of valid scope user returned " + user, user.getDepartmentName(), isIn(validDepartmentsNames));
                }
            } else {
                // -> Admins
                // -> Can be Entire COMPANY scope -> No validation is required
            }
        }
    }


    @Then("^Get manager from company call returns proper data '(.+?)', '(.+?)'$")
    public void getManagersFromCompanySearchAndValidate(String manager, boolean sharePointsWithManager) throws Throwable {
        List<User> usersListResponse = response.getList("", User.class);

        if (sharePointsWithManager) {
            boolean isContains = false;
            for (User userResponse : usersListResponse) {
                isContains = userResponse.getEmail().contains(manager);
                if (isContains) break;
            }
            assertThat("Search results for sharePointsWithManager: " + sharePointsWithManager + " not contains manager: " + manager, isContains);
        } else {
            for (int i = 0; i < usersListResponse.size(); i++) {
                if (usersListResponse.get(i).getEmail().contains(manager))
                    assertThat("Search results [" + i + "] for sharePointsWithManager: " + sharePointsWithManager + " contains manager: " + manager, false);
            }
        }
    }
}