package com.iat.adminportal.page_navigations;

import com.iat.adminportal.locators.AdminPortalHomePageLocators;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertTrue;

public class AdminPortalHomePageNavigation extends AbstractPage {

    protected AdminPortalHomePageLocators locators = new AdminPortalHomePageLocators();
    private String cLocation;

    public AdminPortalHomePageNavigation() {
        super("");
    }

    public void goToPage(String page) throws InterruptedException {
        switch (page) {
            case "Feed Manager":
                executor.click(locators.feed_man_button);
                Thread.sleep(2000);
                break;
            case "Merchant Manager":
                executor.click(locators.mer_man_button);
                Thread.sleep(2000);
                break;
            case "Network Manager":
                executor.click(locators.net_man_button);
                Thread.sleep(2000);
                break;
            case "Brand Manager":
                executor.click(locators.brand_man_button);
                Thread.sleep(2000);
                break;
            case "Store Categories Manager":
                executor.click(locators.stor_man_button);
                Thread.sleep(2000);
                break;
            case "Filter Manager":
                executor.click(locators.filter_man_button);
                Thread.sleep(2000);
                break;
            case "Voucher Manager":
                executor.click(locators.voucher_man_button);
                Thread.sleep(2000);
                break;
            case "Redemption Manager":
                executor.click(locators.redemption_man_button);
                Thread.sleep(2000);
                break;
            case "Email Manager":
                executor.click(locators.email_man_button);
                Thread.sleep(2000);
                break;
            case "Member Manager":
                executor.click(locators.member_man_button);
                Thread.sleep(2000);
                break;
            default:
                break;
        }
        Thread.sleep(500);
        if (page.contentEquals("Email Manager")) {
            assertTrue(page + " page not opened properly", executor.check_element_contains_text(locators.page_header, "Mailing Lists"));
        } else
            assertTrue(page + " page not opened properly", executor.check_element_contains_text(locators.page_header, page));

    }

    public boolean check_is_managers_navi_available(String manager_name) {
        boolean status = false;
        switch (manager_name) {
            case "Feed Manager":
                status = executor.is_element_present(locators.feed_man_button);
                break;
            case "Merchant Manager":
                status = executor.is_element_present(locators.mer_man_button);
                break;
            case "Network Manager":
                status = executor.is_element_present(locators.net_man_button);
                break;
            case "Brand Manager":
                status = executor.is_element_present(locators.brand_man_button);
                break;
            case "Store Categories Manager":
                status = executor.is_element_present(locators.stor_man_button);
                break;
            case "Filter Manager":
                status = executor.is_element_present(locators.filter_man_button);
                break;
            case "Voucher Manager":
                status = executor.is_element_present(locators.voucher_man_button);
                break;
            case "Redemption Manager":
                status = executor.is_element_present(locators.redemption_man_button);
                break;
            case "Email Manager":
                status = executor.is_element_present(locators.redemption_man_button);
                break;
            case "Member Manager":
                status = executor.is_element_present(locators.redemption_man_button);
                break;
            default:
                break;
        }

        return status;
    }

    public String getCurrentUrl() {
        try {
            URL url = new URL(executor.getUrl());
            cLocation = url.getPath();
        } catch (MalformedURLException e) {
            System.err.println("not a valid URL");
        }
        return cLocation;
    }

    public void logOut() {
        executor.click(locators.logout_button);
    }

    public boolean check_if_homePage_opened() {
        return executor.check_element_contains_text(locators.homePageWelcomeMsg, "Welcome to the IAT");
    }

    public boolean check_if_navi_panel_available() {
        return executor.is_element_present(locators.left_navi_panel);
    }

}