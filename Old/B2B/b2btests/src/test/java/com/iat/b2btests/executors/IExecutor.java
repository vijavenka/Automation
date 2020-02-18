package com.iat.b2btests.executors;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.iat.b2btests.locators.ILocator;

public interface IExecutor {
	
	public void start();
	
	public void stop();
	
	public WebElement get_element(ILocator locator);
	
	public List<WebElement> get_elements(ILocator locator);
	
	public String getText(ILocator locator);
	
	public String getValue(ILocator locator);
	
	public List<WebElement> getAllOptions(ILocator locator);
	
	public String getSelectedOption(ILocator locator);
	
	public String getAttribute(ILocator locator, String attribute);
	
	public void selectOption(ILocator locator, String optionToSelect);
	
	public String getUrl();
	
	public void open(String url);
	
	public void click(ILocator locator);
	
	public void send_keys(ILocator locator, String keys_to_send);

	public void clear_textarea(ILocator locator);
	
	public void submit(ILocator locator);
	
	public boolean isPresent(ILocator locator);
	
	public boolean arePresent(List<ILocator> elementList);
	
	public boolean isVisible(ILocator locator);
	
	public boolean isEnabled(ILocator locator);
	
	public boolean areEnabled(List<ILocator> elementList);
	
	public boolean check_element_contains_text(ILocator locator, String text);
	
	public void check(ILocator locator);
	
	public void uncheck(ILocator locator);
	
	public boolean isChecked(ILocator locator);
	
	public void waitUntilElementAppears(ILocator locator);
	
	public void waitUntilElementDisappears(ILocator locator);
	
	public void waitAbsolute(int waitTime);
	
	public void actionMoveByOffset(ILocator locator, int xOffset, int yOffset);
	
	public void actionFocusElement(ILocator locator);
	
}
