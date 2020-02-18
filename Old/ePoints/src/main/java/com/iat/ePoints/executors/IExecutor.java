package com.iat.ePoints.Executors;

import com.iat.ePoints.Locators.ILocator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface IExecutor {
    public WebDriver return_driver();
    public void start();
    public void stop();
    public WebElement get_element(ILocator locator);
    public String getText(ILocator locator);
    public String getValue(ILocator locator);
    public List<WebElement> getAllOptions(ILocator locator);
    public String getSelectedOption(ILocator locator);
    public void selectOptionByText(ILocator locator, String optionToSelect);
    public void selectOptionByValue(ILocator locator, String optionToSelect);
    public String getUrl();
    public void open(String url);
    public void click(ILocator locator);
    public void send_keys(ILocator locator, String keys_to_send);
    public void send_keys(ILocator locator, Keys keys_to_send);
    public void submit(ILocator locator);
    public boolean is_element_present(ILocator locator);
    public boolean is_element_present(WebElement webelement);
    public boolean is_enabled(ILocator locator);
    public boolean check_element_contains_text(ILocator locator, String text);
    public void check(ILocator locator);
    public void uncheck(ILocator locator);
    public void waitUntilElementDisappears(ILocator locator);
    public void waitUntilElementAppears(ILocator locator);
    public List<WebElement> getWebElementsList(ILocator locator);
    public String getBaseUrl();
    public int return_random_value(int upper);
    public void clear(ILocator locator);
    public String getText(WebElement webElement);
    String getAttributeTitle(ILocator locator);
    String getAttributeTitle(WebElement webElement);
    public void scrollPage() throws InterruptedException;
    public boolean handleMultipleWindows(String windowTitle);
    public void click(WebElement webElement);
    public void moveMouseToWebElement(WebElement webElement);
    public void moveMouseToWebElement(ILocator locator);
    public void clickXpath(String xpath);
    public String returnCurrentDate();
    public void pressEnterOnChosenLocator(ILocator locator);
    public void pressEnterOnChosenLocator(WebElement webElement);
    public String getAttribute(ILocator locator, String elementToGet);
    public String getAttribute(WebElement webElement, String elementToGet);
}
