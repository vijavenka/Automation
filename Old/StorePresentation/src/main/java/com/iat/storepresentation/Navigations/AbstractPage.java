package com.iat.storepresentation.Navigations;

import com.iat.storepresentation.Executors.IExecutor;
import com.iat.storepresentation.Locators.IPageLocator;

public abstract class AbstractPage {

    protected IExecutor executor;
    protected IPageLocator locators;
    protected String url;

    public AbstractPage(IExecutor executor, String url) {
        this.executor = executor;
        this.url = url;
    }

    public void open() {
        executor.open(url);
    }
}