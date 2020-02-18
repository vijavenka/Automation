package com.iat.ePoints.Navigations.StaticPages;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 20.06.13
 * Time: 19:37
 * To change this template use File | Settings | File Templates.
 */

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.StaticPages.AboutUsLocators;
import com.iat.ePoints.Locators.StaticPages.SubmitARequestPageLocators;
import com.iat.ePoints.Navigations.AbstractPage;

import static org.junit.Assert.assertTrue;

public class AboutUsNavigation extends AbstractPage {

    private AboutUsLocators locators_about = new AboutUsLocators();
    private SubmitARequestPageLocators locators_request= new SubmitARequestPageLocators();

    public AboutUsNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    public void checkIsAboutUsPageOpened(){
        assertTrue("About page not opened correctly", executor.is_element_present(locators_about.aboutPageCheck));
    }

    public void openAboutEpointsPage(){
        executor.click(locators_about.aboutEpointsReference);
    }

    public void checkFourMainTitles(){
        assertTrue("rewardsTitle is not displayed",executor.is_element_present(locators_about.rewardsTitle));
        assertTrue("choiceTitle is not displayed",executor.is_element_present(locators_about.choicesTile));
        assertTrue("valueTitle is not displayed",executor.is_element_present(locators_about.valueTitle));
        assertTrue("missionTitle is not displayed",executor.is_element_present(locators_about.missionTitle));
    }

    public void checkButtonAndTopBackReferenceVisibility(){
        assertTrue("getInButton is not available",executor.is_element_present(locators_about.getInTouchButton));
        assertTrue("backToTopReference is not available",executor.is_element_present(locators_about.backToTopReference));
    }

    public void clickGetInTouchButton(){
        executor.click(locators_about.getInTouchButton);
    }

    public void checkSubmitRequestPageContent(){
        assertTrue("attachfileReference is not available",executor.is_element_present(locators_request.attachfileReference));
        assertTrue(" is not available",executor.is_element_present(locators_request.descriptionTextField));
        assertTrue("descriptionTextField is not available",executor.is_element_present(locators_request.subjectTextField));
        assertTrue("submitARequestTitle is not available",executor.is_element_present(locators_request.submitARequestTitle));
        assertTrue("submitButton is not available",executor.is_element_present(locators_request.submitButton));
        assertTrue("yourEmailAddressTextFied is not available",executor.is_element_present(locators_request.yourEmailAddressTextFied));
    }
}
