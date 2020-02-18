package com.iat.adminportal.executors;

import com.iat.adminportal.locators.ILocator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface IExecutor {

    public void start();

    public void stop();

    public WebElement get_element(ILocator locator);

    public List<WebElement> get_elements(ILocator locator);

    public List<String> getTableHeaders();

    public String getText(ILocator locator);

    public List<WebElement> getAllOptions(ILocator locator);

    public String getSelectedOption(ILocator locator);

    public String getUrl();

    public void open(String url);

    public void click(ILocator locator);

    public void send_keys(ILocator locator, String keys_to_send);

    public void submit(ILocator locator);

    public boolean is_element_present(ILocator locator);

    public boolean check_element_contains_text(ILocator locator, String text);

    public boolean is_element_visible(ILocator locator);

    public void check(ILocator locator);

    public void uncheck(ILocator locator);

    public void waitUntilElementDisappears(ILocator locator);

    public String getClass(ILocator locator);

    public void waitUntilElementAppears(ILocator locator);

    public void waitAbsolute(int waitTime);

    public String getText(WebElement webElemnet);

    public void selectOptionByValue(ILocator locator, String optionToSelect);

    public int return_random_value(int upper);

    public void click(WebElement webElement);

    public WebDriver return_driver();

    public void moveMouseToWebElement(WebElement webElement);

    public void moveMouseToWebElement(ILocator locator);

    public void clear(ILocator locator);

    public void selectOptionByText(ILocator locator, String optionToSelect);

    public void pressEnterOnChosenLocator(ILocator locator);

    public void pressEnterOnChosenLocator(WebElement webElement);

    public String generateRandomString(String characters, int length);

    public String returnCurrentDate();

    public String getAttribute(ILocator locator, String elementToGet);

    public String getAttribute(WebElement webElement, String elementToGet);
}
