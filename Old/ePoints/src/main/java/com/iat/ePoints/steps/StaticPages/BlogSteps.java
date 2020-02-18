package com.iat.ePoints.Steps.StaticPages;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.StaticPages.BlogNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 04.12.13
 * Time: 16:33
 * To change this template use File | Settings | File Templates.
 */
public class BlogSteps {

    private IExecutor executor;
    private BlogNavigation blogNavi;

    public BlogSteps(SeleniumExecutor executor) {
        this.executor = executor;

    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        blogNavi = new BlogNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    @When("^Blog web page is opened$")
    public void Blog_web_page_is_opened() throws Throwable {
        blogNavi.openBlogPage();
    }

    @Then("^Blog Page is available with proper content$")
    public void Blog_Page_is_available_with_proper_content() throws Throwable {
        blogNavi.checkBlogPageTitle();
        blogNavi.checkContentOfBlogPage();
    }
}
