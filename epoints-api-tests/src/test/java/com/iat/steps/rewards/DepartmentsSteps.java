package com.iat.steps.rewards;

import com.iat.actions.SOLRActions;
import com.iat.actions.rewards.DepartmentsActions;
import com.iat.actions.search.SearchActions;
import com.iat.domain.Department;
import com.iat.utils.ResponseContainer;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DepartmentsSteps {

    private ResponseContainer response;
    private DepartmentsActions departmentsActions = new DepartmentsActions();
    private SearchActions searchActions = new SearchActions();
    private List<Department> departments, departments2;
    private SOLRActions solrActions = new SOLRActions();

    @When("^Department call will be requested for businessId '(.+?)'$")
    public void getDepartmentsByBusinessId(String businessId) throws Throwable {
        response = departmentsActions.getDepartmentsByBusinessId(businessId);
        departments = response.getList("", Department.class);
    }

    @Then("^Number of returned departments will be correct '(\\d+)'$")
    public void checkNumberOfDepartments(int departmentsNumber) throws Throwable {
        assertThat("Wrong number of Departments!", departments, hasSize(departmentsNumber));
    }

    @Then("^Search results count for each department will match values returned by departments and solr call for '(.+?)'$")
    public void getProductNumberForSpecificDepartmentAndValidateCorrectness(String businessId) throws Throwable {
        for (Department department : departments) {
            response = searchActions.getRedemptionItems(businessId, "s_departmentSeoSlug:" + department.getSeoTitle(), "null", false,200);
            int redemptionsValue = response.getInt("allCount");
            assertThat("Wrong number returned products for department: " + department.getTitle(), department.getItemsCount(), is(redemptionsValue));

            int redemptionsCount;
            if (businessId.equals("united"))
                redemptionsCount = solrActions.getSOLRRedemptionsCount("fq=s_departmentSeoSlug:" + department.getSeoTitle() + "&fq=s_productScope:\"UNITED\"&fq=b_zoneUK:true");
            else
                redemptionsCount = solrActions.getSOLRRedemptionsCount("fq=s_departmentSeoSlug:" + department.getSeoTitle() + "&fq=-s_productScope:[\"\" TO *]&fq=b_zoneUK:true");
            assertThat("Wrong number returned products for department: " + department.getTitle(), department.getItemsCount(), is(redemptionsCount));
        }
    }

    @When("^Department call will be requested for united and epoints$")
    public void getDepartmentsForEpointsAndUnited() throws Throwable {
        departments = departmentsActions.getDepartmentsByBusinessId("null").getList("", Department.class);
        departments2 = departmentsActions.getDepartmentsByBusinessId("united").getList("", Department.class);
    }

    @Then("^Apart of products number data should be the same$")
    public void compareDepartmentsDataOfDifferentPartners() throws Throwable {
        int i = -1;
        for (Department department : departments) {
            assertThat("Same department itemsCount for department: " + department.getDisplayTitle(), department.getId(), is(departments2.get(++i).getId()));
            assertThat("Same department itemsCount for department: " + department.getDisplayTitle(), department.getSeoTitle(), is(departments2.get(i).getSeoTitle()));
            assertThat("Same department itemsCount for department: " + department.getDisplayTitle(), department.getTitle(), is(departments2.get(i).getTitle()));
            // This will mainly works on prod when feeds will be independent on qa we are using same feeds with same products for tests
            if (department.getItemsCount() > 0 && departments2.get(i).getItemsCount() > 0)
                assertThat("Same department itemsCount for department: " + department.getDisplayTitle(), department.getItemsCount(), is(not(departments2.get(i).getItemsCount())));
        }
    }
}