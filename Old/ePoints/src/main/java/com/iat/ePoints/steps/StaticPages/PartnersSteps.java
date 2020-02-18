package com.iat.ePoints.Steps.StaticPages;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.StaticPages.FooterNavigation;
import com.iat.ePoints.Navigations.StaticPages.PartnersNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 04.04.14
 * Time: 14:07
 * To change this template use File | Settings | File Templates.
 */
public class PartnersSteps {


    private IExecutor executor;
    private PartnersNavigation partnersNavi;
    private FooterNavigation footerNavi;

    public PartnersSteps(SeleniumExecutor executor){
        this.executor = executor;
    }

    public void go_sleep(int milisec)throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        partnersNavi = new PartnersNavigation(executor);
        footerNavi = new FooterNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    // LEAD GEN SCREEN - add new submission form UI to epoints (RD-1645) - page content
    @Then("^Proper form with needed fields will be visible$")
    public void Proper_form_with_needed_fields_will_be_visible() throws Throwable {
        partnersNavi.checkContentOfPartnersForm();
    }

    // LEAD GEN SCREEN - add new submission form UI to epoints (RD-1645) - alert behaviour
    @When("^Submit button will be pressed without filling any data$")
    public void Submit_button_will_be_pressed_without_filling_any_data() throws Throwable {
        partnersNavi.clickSubmitButton();
    }

    @Then("^Proper alerts will be shown$")
    public void Proper_alerts_will_be_shown() throws Throwable {
        partnersNavi.checkVisibilityOfAlerts();
    }
}
