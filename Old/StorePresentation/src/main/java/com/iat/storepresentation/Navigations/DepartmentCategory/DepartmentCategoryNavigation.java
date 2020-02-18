package com.iat.storepresentation.Navigations.DepartmentCategory;

import com.iat.storepresentation.Executors.IExecutor;
import com.iat.storepresentation.Locators.DepartmentCategory.DepartmentCategoryLocators;
import com.iat.storepresentation.Locators.ShopHome.HomePageLocators;
import com.iat.storepresentation.Navigations.AbstractPage;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 08.03.14
 * Time: 11:14
 * To change this template use File | Settings | File Templates.
 */
public class DepartmentCategoryNavigation extends AbstractPage{

    private HomePageLocators locators_home = new HomePageLocators();
    private DepartmentCategoryLocators locators_department = new DepartmentCategoryLocators();


    public DepartmentCategoryNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    // FRONT END REFACTOR - add epoints search solution to WLS CATEGORY pages (Rd-1968) - checking of breadcrumb correctness
    String departmentlevel1="none";
    String departmentlevel2="none";
    String departmentlevel3="none";
    public void choseDepartmentFromVisibleDDL(){
        int random = executor.return_random_value(locators_home.departments.length);
        departmentlevel1 = locators_home.departments[random];
        executor.click(locators_home.DepartmentsDDL[random]);
    }

    public void navigateTroughtDepartmentsAndRememberTheirNames(){
        List<WebElement> departments;
        int random;
        if(executor.is_element_present(locators_department.departmentCategorySubCategoryName)){
            departments = executor.getWebElementsList(locators_department.departmentCategorySubCategoryName);
            random = executor.return_random_value(departments.size());
            departmentlevel2 = executor.getText(departments.get(random));
            executor.click(departments.get(random));
            if(executor.is_element_present(locators_department.departmentCategorySubCategoryName)){
                departments = executor.getWebElementsList(locators_department.departmentCategorySubCategoryName);
                random = executor.return_random_value(departments.size());
                departmentlevel3 = executor.getText(departments.get(random));
                executor.click(departments.get(random));
            }else{
                // do nothing
            }
        }else{
            // do nothing
        }
    }

    public void checkIfCorrectCategoryPageWasOpened(){
        if(departmentlevel2.equals("none")){
            assertEquals("Category page title is incorrect", departmentlevel1.toLowerCase(), executor.getText(locators_department.catgoryPageTitle).toLowerCase());
        }else{
            if(departmentlevel3.equals("none")){
                assertEquals("Category page title is incorrect", departmentlevel2.toLowerCase(), executor.getText(locators_department.catgoryPageTitle).toLowerCase());
            }else{
                assertEquals("Category page title is incorrect", departmentlevel3.toLowerCase(), executor.getText(locators_department.catgoryPageTitle).toLowerCase());

            }
        }
    }

    public void checkCorrectnessOfDisplayingBreadcrumbComponent(){
        if(departmentlevel2.equals("none")){
            assertEquals("Breadcrumb level 1 has improper name", departmentlevel1.toLowerCase(), executor.getText(locators_department.searchBreadcrumbLevel1).toLowerCase());
        }else{
            if(departmentlevel3.equals("none")){
                assertEquals("Breadcrumb level 1 has improper name", departmentlevel1.toLowerCase(), executor.getText(locators_department.searchBreadcrumbLevel1).toLowerCase());
                assertEquals("Breadcrumb level 2 has improper name", departmentlevel2.toLowerCase(), executor.getText(locators_department.searchBreadcrumbLevel3).toLowerCase());
            }else{
                assertEquals("Breadcrumb level 1 has improper name", departmentlevel1.toLowerCase(), executor.getText(locators_department.searchBreadcrumbLevel1).toLowerCase());
                assertEquals("Breadcrumb level 2 has improper name", departmentlevel2.toLowerCase(), executor.getText(locators_department.searchBreadcrumbLevel2).toLowerCase());
                assertEquals("Breadcrumb level 3 has improper name", departmentlevel3.toLowerCase(), executor.getText(locators_department.searchBreadcrumbLevel3).toLowerCase());
            }
        }
    }
}
