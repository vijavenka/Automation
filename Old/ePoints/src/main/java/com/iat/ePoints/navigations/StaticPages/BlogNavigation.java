package com.iat.ePoints.Navigations.StaticPages;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.HeaderLocators;
import com.iat.ePoints.Locators.StaticPages.BlogLocators;
import com.iat.ePoints.Navigations.AbstractPage;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 04.12.13
 * Time: 16:33
 * To change this template use File | Settings | File Templates.
 */
public class BlogNavigation extends AbstractPage {

    BlogLocators locators_blog = new BlogLocators();
    HeaderLocators locator_header = new HeaderLocators();

    public void openBlogPage() {
        executor.click(locator_header.Blog);
    }

    public BlogNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void checkBlogPageTitle() {
        assertEquals("BlogSteps page has wron title", executor.return_driver().getTitle(), "epoints blog | epoints");
    }

    public void checkContentOfBlogPage() {
        assertTrue("Archive section is no visible", executor.is_element_present(locators_blog.archivesSection));
        assertTrue("Any article is no visible", executor.is_element_present(locators_blog.basicArticle));
        assertTrue("Categories section is no visible", executor.is_element_present(locators_blog.categoriesSection));
        assertTrue("Follow us section is no visible", executor.is_element_present(locators_blog.followUsSection));
        assertTrue("Recent Points section is no visible", executor.is_element_present(locators_blog.recentPointsSection));
        assertTrue("Searcher section is no visible", executor.is_element_present(locators_blog.searchBlogSections));
        assertTrue("Tweeter section is no visible", executor.is_element_present(locators_blog.tweetSection));
        assertTrue("Voting section is no visible", executor.is_element_present(locators_blog.votingSection));
    }
}
