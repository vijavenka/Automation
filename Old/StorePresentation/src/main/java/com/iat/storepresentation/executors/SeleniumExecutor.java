package com.iat.storepresentation.Executors;

import com.iat.storepresentation.EnvironmentVariables;
import com.iat.storepresentation.Locators.ILocator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class SeleniumExecutor implements IExecutor {

    private WebDriver driver;
    private WebDriverWait waitDriver;
    private String base_url;
    private EnvironmentVariables envVariables;

    public SeleniumExecutor() {

        envVariables = new EnvironmentVariables();

        if(envVariables.usedBrowser.equals("firefox")){
            System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
            this.driver = new FirefoxDriver();
        }
        if(envVariables.usedBrowser.equals("chrome")){
            //System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
            //this.driver = new ChromeDriver();
            setChromeDriver();
            this.driver = new ChromeDriver();
        }
        if(envVariables.usedBrowser.equals("ie")){
            setIEDriver();
            this.driver = new InternetExplorerDriver();
        }
        this.waitDriver = new WebDriverWait(driver, 100);

        this.driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        this.driver.manage().timeouts().setScriptTimeout(50, TimeUnit.SECONDS);
        this.driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
        this.base_url = envVariables.baseUrl;
        this.driver.manage().getCookies();
        this.driver.manage().window().maximize();
    }
    public void setChromeDriver()
    {
        String chromeBinary = "src/main/drivers/chrome/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeBinary);
    }

    public void setIEDriver()
    {
        String ieBinary = "src/main/drivers/ie/IEDriverServer.exe";
        System.setProperty("webdriver.ie.driver", ieBinary);
    }

    public String getBaseUrl() {
        return base_url;
    }

    public WebElement get_element(ILocator locator) {
        WebElement element = null;
        try {
            switch (locator.get_type()) {
                case ID:
                    waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator.get_body())));
                    element = driver.findElement(By.id(locator.get_body()));
                    break;
                case CSS:
                    waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator.get_body())));
                    element = driver.findElement(By.cssSelector(locator.get_body()));
                    break;
                case XPATH:
                    waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.get_body())));
                    element = driver.findElement(By.xpath(locator.get_body()));
                    break;
                case NAME:
                    waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator.get_body())));
                    element = driver.findElement(By.name(locator.get_body()));
                    break;
                default:
                    break;
            }
            return element;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return null;
        }
    }

    public WebElement get_element_no_wait(ILocator locator) {
        WebElement element = null;
        try {
            switch (locator.get_type()) {
                case ID:
                    element = driver.findElement(By.id(locator.get_body()));
                    break;
                case CSS:
                    element = driver.findElement(By.cssSelector(locator.get_body()));
                    break;
                case XPATH:
                    element = driver.findElement(By.xpath(locator.get_body()));
                    break;
                case NAME:
                    element = driver.findElement(By.name(locator.get_body()));
                    break;
                default:
                    break;
            }
            return element;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return null;
        }
    }

    public void open(String url) {
        driver.get(base_url + url);
    }

    public void send_keys(ILocator locator, String keys_to_send) {
        WebElement element = get_element(locator);
        element.sendKeys(keys_to_send);
    }

    public void send_keys(ILocator locator, Keys keys_to_send) {
        WebElement element = get_element(locator);
        element.sendKeys(keys_to_send);
    }

    public void submit(ILocator locator) {
        WebElement element = this.get_element(locator);
        element.submit();
    }

    public void click(ILocator locator) {
        WebElement element = get_element(locator);
        element.click();
    }

    public void click(WebElement webElement) {
        webElement.click();
    }

    public void clickXpath(String xpath){
        driver.findElement(By.xpath(xpath)).click();
    }

    public boolean is_element_present(ILocator locator) {
        WebElement element = get_element_no_wait(locator);
        if (element == null || !element.isDisplayed()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean is_element_present(WebElement webElement) {
        if (webElement == null || !webElement.isDisplayed()) {
            return false;
        } else {
            return true;
        }
    }

    public void start() {
        //Empty
    }

    public void stop() {
        driver.quit();
    }

    public boolean check_element_contains_text(ILocator locator, String text) {
        WebElement element = get_element(locator);
        String element_text = element.getText();
        return element_text.contains(text);
    }

    public boolean is_enabled(ILocator locator) {
        WebElement element = get_element(locator);
        return (element.isEnabled());
    }

    public void check(ILocator locator) {
        WebElement element = get_element(locator);
        if (element.isSelected() == false) {
            element.click();
        }
    }

    public void uncheck(ILocator locator) {
        WebElement element = get_element(locator);
        if (element.isSelected() == true) {
            element.click();
        }
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public List<WebElement> getWebElementsList(ILocator locator) {
        List<WebElement> elemList;
        try {
            switch (locator.get_type()) {
                case ID:
                    return elemList = driver.findElements(By.id(locator.get_body()));
                case CSS:
                    return elemList = driver.findElements(By.cssSelector(locator.get_body()));
                case XPATH:
                    return elemList = driver.findElements(By.xpath(locator.get_body()));
                case NAME:
                    return elemList = driver.findElements(By.name(locator.get_body()));
                default:
                    return null;
            }
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return null;
        }
    }

    public String getText(ILocator locator) {
        WebElement element = get_element(locator);
        return element.getText();
    }

    public String getText(WebElement webElemnet) {
        return webElemnet.getText();
    }

    public String getValue(ILocator locator) {
        WebElement element = get_element(locator);
        return element.getAttribute("value");
    }

    public String getAttributeTitle(ILocator locator) {
        WebElement element = get_element(locator);
        return element.getAttribute("title");
    }

    public String getAttributeTitle(WebElement webElement) {
        return webElement.getAttribute("title");
    }

    public List<WebElement> getAllOptions(ILocator locator) {
        WebElement element = get_element_no_wait(locator);
        Select select = new Select(element);
        List<WebElement> options = select.getOptions();
        return options;
    }

    public String getSelectedOption(ILocator locator) {
        WebElement element = get_element(locator);
        Select select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }

    public void selectOptionByText(ILocator locator, String optionToSelect) {
        List<WebElement> options = getAllOptions(locator);
        for (WebElement option : options) {
            if (option.getText().equals(optionToSelect)) {
                option.click();
            }
        }
    }

    public void selectOptionByValue(ILocator locator, String optionToSelect) {
        List<WebElement> options = getAllOptions(locator);
        for (WebElement option : options) {
            if (option.getAttribute("value").equals(optionToSelect)) {
                option.click();
            }
        }
    }

    public int return_random_value(int upper) {
        Random rand = new Random();
        return rand.nextInt(upper);
    }

    public void waitUntilElementDisappears(ILocator locator) {
        try {
            driver.findElement(By.xpath(locator.get_body()));
            waitDriver.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator.get_body())));
        } catch (Exception e) {

        }
    }

    public void waitUntilElementAppears(ILocator locator) {
        try {
            driver.findElement(By.xpath(locator.get_body()));
            waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.get_body())));
        } catch (Exception e) {

        }
    }

    public void clear(ILocator locator) {
        WebElement webElement = get_element(locator);
        webElement.clear();
    }

    public void scrollPage() throws InterruptedException {
        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        jsx.executeScript("window.scrollBy(0,450)", "");
    }

    public WebDriver return_driver() {
        return this.driver;
    }

    public boolean handleMultipleWindows(String windowTitle) {
        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {
            driver.switchTo().window(window);
            if (driver.getTitle().contains(windowTitle)) {
                return true;
            }
        }
        return false;
    }

    public void moveMouseToWebElement(WebElement webElement){
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).contextClick().build().perform();
    }

    public void moveMouseToWebElement(ILocator locator){
        Actions actions = new Actions(driver);
        actions.moveToElement(get_element_no_wait(locator)).contextClick().build().perform();
    }

    public String returnCurrentDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/London"));
        String CurrentDate = simpleDateFormat.format(new Date());
        return CurrentDate;
    }
}