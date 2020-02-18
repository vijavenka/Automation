package com.iat.storepresentation.Steps.DepartmentCategory;

import com.iat.storepresentation.Executors.IExecutor;
import com.iat.storepresentation.Executors.SeleniumExecutor;
import com.iat.storepresentation.Navigations.DepartmentCategory.DepartmentCategoryNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 08.03.14
 * Time: 11:14
 * To change this template use File | Settings | File Templates.
 */
public class DepartmentCategorySteps {

    private IExecutor executor;
    private DepartmentCategoryNavigation departmrntNavi;

    public DepartmentCategorySteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        departmrntNavi = new DepartmentCategoryNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    // FRONT END REFACTOR - add epoints search solution to WLS CATEGORY pages (Rd-1968) - checking of breadcrumb correctness
    @When("^User chose some department from DDL$")
    public void User_chose_some_department_from_DDL() throws Throwable {
        departmrntNavi.choseDepartmentFromVisibleDDL();
    }

    @When("^Will navigate through department/categories options$")
    public void Will_navigate_through_department_categories_options() throws Throwable {
        departmrntNavi.navigateTroughtDepartmentsAndRememberTheirNames();
    }

    @Then("^He will be redirected to correct category page$")
    public void He_will_be_redirected_to_correct_category_page() throws Throwable {
        departmrntNavi.checkIfCorrectCategoryPageWasOpened();
    }

    @Then("^Breadcrumb component will show proper path$")
    public void Breadcrumb_component_will_show_proper_path() throws Throwable {
        departmrntNavi.checkCorrectnessOfDisplayingBreadcrumbComponent();
    }
}
