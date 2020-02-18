package com.iat.b2btests.navigations;

import com.iat.b2btests.executors.IExecutor;
import com.iat.b2btests.locators.IPageLocators;

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
