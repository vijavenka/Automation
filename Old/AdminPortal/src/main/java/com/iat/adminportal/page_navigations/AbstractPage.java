package com.iat.adminportal.page_navigations;

import com.iat.adminportal.executors.IExecutor;
import com.iat.adminportal.locators.IPageLocators;
import com.iat.adminportal.steps.DataExchanger;

public abstract class AbstractPage {

    DataExchanger dataExchanger = DataExchanger.getInstance();

    protected IExecutor executor = dataExchanger.getExecutor();
    protected IPageLocators locators;
    protected String url;

    public AbstractPage(String url) {
        this.url = url;
    }

    public void open() {
        executor.open(this.url);
    }
}