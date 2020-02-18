package com.iat.adminportal.page_navigations;

import com.iat.adminportal.locators.AdminPortalFeedPageLocators;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminPortalCommons extends AbstractPage {

    protected AdminPortalFeedPageLocators feedLocators = new AdminPortalFeedPageLocators();

    public AdminPortalCommons() {
        super("");
    }

    public Float getListViewNumberOfRecordsDisplayed() {
        Float numOfRecordsPerPage = Float.parseFloat(executor.getSelectedOption(feedLocators.list_view_pager_select));
        return numOfRecordsPerPage;
    }

    public List<Float> getListViewNumberOfAllRecords() {
        String allRecords = executor.getText(feedLocators.list_view_table_info);
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(allRecords);

        List<Float> matchedNumbers = new ArrayList<Float>();
        while (m.find()) {
            matchedNumbers.add((Float.parseFloat(m.group())));
        }
        return matchedNumbers;
        /*
         * First element: number of first record displayed on the list
		 * Second element: number of last element on the list
		 * Third element: number of all elements in the DB
		 */
    }
}
