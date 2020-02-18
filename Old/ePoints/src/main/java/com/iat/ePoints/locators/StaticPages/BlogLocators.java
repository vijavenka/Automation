package com.iat.ePoints.Locators.StaticPages;

import com.iat.ePoints.Locators.Locator;
import com.iat.ePoints.Locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 04.12.13
 * Time: 16:33
 * To change this template use File | Settings | File Templates.
 */
public class BlogLocators {
    public Locator basicArticle = new Locator(LocatorType.XPATH, "//article");
    public Locator followUsSection = new Locator(LocatorType.XPATH, "//aside[@id='text-3']");
    public Locator searchBlogSections = new Locator(LocatorType.XPATH, "//aside[@id='search-3']");
    public Locator categoriesSection = new Locator(LocatorType.XPATH, "//aside[@id='categories-2']");
    public Locator archivesSection = new Locator(LocatorType.XPATH, "//aside[@id='archives-2']");
    public Locator recentPointsSection = new Locator(LocatorType.XPATH, "//aside[@id='recent-posts-2']");
    public Locator votingSection = new Locator(LocatorType.XPATH, "//aside[@id='polls-widget-4']");
    public Locator tweetSection = new Locator(LocatorType.XPATH, "//aside[@id='text-2']");
}
