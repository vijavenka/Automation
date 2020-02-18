package com.iat.ePoints.Navigations;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.IPageLocators;

public abstract class AbstractPage {

    protected IExecutor executor;
    protected IPageLocators locators;
    protected String url;

    public AbstractPage(IExecutor executor, String url) {
        this.executor = executor;
        this.url = url;
    }

    public void open() {
        executor.open(url);
    }
}
