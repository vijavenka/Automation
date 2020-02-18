package com.iat.adminportal.executors;

import com.iat.adminportal.EnvironmentVariables;
import com.iat.adminportal.locators.ILocator;
import com.iat.adminportal.steps.DataExchanger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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


    //private FirefoxProfile profile = new FirefoxProfile();
    private WebDriver driver;
    private WebDriverWait waitDriver;
    private String base_url;
    private EnvironmentVariables envVariables = new EnvironmentVariables();
    DataExchanger dataExchanger = DataExchanger.getInstance();

    public SeleniumExecutor() {
        switch (EnvironmentVariables.browser) {
            case "chrome":
                Map<String, Object> prefs = new HashMap<String, Object>();

                prefs.put("profile.default_content_setting_values.notifications", 2);
                prefs.put("profile.default_content_settings.popups", 0);
                prefs.put("download.prompt_for_download", false);

                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", prefs);
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-extensions");
                options.addArguments("--disable-print-preview");

                System.setProperty("webdriver.chrome.driver", "src/binary/Chrome/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/binary/Firefox/geckodriver.exe");
                this.driver = new FirefoxDriver();
                break;
            case "ie":
                System.setProperty("webdriver.ie.driver", "src/binary/IE/IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;
        }

        this.waitDriver = new WebDriverWait(driver, 20);
        this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        this.driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
        this.driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        this.base_url = envVariables.getBaseUrl();

        this.driver.manage().window().maximize();
    }

    public WebElement get_element(ILocator locator) {
        WebElement element = null;
        try {
            switch (locator.get_type()) {
                case ID:
                    //waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator.get_body())));
                    element = driver.findElement(By.id(locator.get_body()));
                    break;
                case CSS:
                    //waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator.get_body())));
                    element = driver.findElement(By.cssSelector(locator.get_body()));
                    break;
                case XPATH:
                    //waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.get_body())));
                    element = driver.findElement(By.xpath(locator.get_body()));
                    break;
                case NAME:
                    //waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator.get_body())));
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

    public List<WebElement> get_elements(ILocator locator) {
        List<WebElement> elements = null;
        try {
            switch (locator.get_type()) {
                case ID:
                    elements = driver.findElements(By.id(locator.get_body()));
                    break;
                case CSS:
                    elements = driver.findElements(By.cssSelector(locator.get_body()));
                    break;
                case XPATH:
                    elements = driver.findElements(By.xpath(locator.get_body()));
                    break;
                case NAME:
                    elements = driver.findElements(By.name(locator.get_body()));
                default:
                    break;
            }
            return elements;
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

    public boolean is_element_present(ILocator locator) {
        WebElement element = get_element_no_wait(locator);
        if (element == null || !element.isDisplayed()) {
            return false;
        }

        return true;
    }

    public boolean is_element_visible(ILocator locator) {
        WebElement element = get_element(locator);
        if (element.isDisplayed()) {
            return true;
        }

        return false;
    }

    public void start() {
        //Empty
    }

    public void stop() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.clear();");
        driver.manage().deleteAllCookies();
        dataExchanger.setExecutor(null);
        driver.close();
        driver.quit();
    }

    public boolean check_element_contains_text(ILocator locator, String text) {
        WebElement element = get_element(locator);
        String element_text = element.getText();
        return element_text.contains(text);
    }

    public void check(ILocator locator) {
        WebElement element = get_element(locator);
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void uncheck(ILocator locator) {
        WebElement element = get_element(locator);
        if (element.isSelected()) {
            element.click();
        }
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public List<String> getTableHeaders() {
        List<WebElement> elemList = driver.findElements(By.xpath("//table/thead/tr/th"));
        List<String> headersList = new ArrayList<>();

        for (int x = 1; x <= elemList.size(); x++) {
            WebElement element = driver.findElement(By.xpath("//table/thead/tr/th[" + x + "]"));
            String thText = element.getText();
            headersList.add(thText);
        }

        return headersList;
    }

    public String getText(ILocator locator) {
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.get_body())));
        WebElement element = get_element(locator);
        return element.getText();
    }

    public String getClass(ILocator locator) {
        WebElement element = get_element(locator);
        return element.getAttribute("class");
    }

    public List<WebElement> getAllOptions(ILocator locator) {
        WebElement element = get_element_no_wait(locator);
        Select select = new Select(element);
        return select.getOptions();
    }

    public String getSelectedOption(ILocator locator) {
        WebElement element = get_element(locator);
        Select select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }

    public void selectOption(ILocator locator, String optionToSelect) {
        List<WebElement> options = getAllOptions(locator);
        for (WebElement option : options) {
            if (option.getText().equals(optionToSelect)) {
                option.click();
            }
        }
    }

    public void waitUntilElementDisappears(ILocator locator) {
        try {
            driver.findElement(By.xpath(locator.get_body()));
            waitDriver.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator.get_body())));
        } catch (Exception e) {
            e.toString();
        }
    }

    public void waitUntilElementAppears(ILocator locator) {
        try {
            driver.findElement(By.xpath(locator.get_body()));
            waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.get_body())));
        } catch (Exception e) {
            e.toString();
        }
    }

    public void waitAbsolute(int waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getText(WebElement webElemnet) {
        waitDriver.until(ExpectedConditions.visibilityOf(webElemnet));
        return webElemnet.getText();
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

    public WebDriver return_driver() {
        return this.driver;
    }

    public void moveMouseToWebElement(WebElement webElement) {
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).build().perform();
    }

    public void moveMouseToWebElement(ILocator locator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(get_element_no_wait(locator)).build().perform();
    }

    public void clear(ILocator locator) {
        WebElement webElement = get_element(locator);
        webElement.clear();
    }

    public void selectOptionByText(ILocator locator, String optionToSelect) {
        List<WebElement> options = getAllOptions(locator);
        for (WebElement option : options) {
            if (option.getText().equals(optionToSelect)) {
                option.click();
            }
        }
    }

    public void pressEnterOnChosenLocator(ILocator locator) {
        get_element(locator).sendKeys(Keys.ENTER);
    }

    public void pressEnterOnChosenLocator(WebElement webElement) {
        webElement.sendKeys(Keys.ENTER);
    }

    public String generateRandomString(String characters, int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(return_random_value(characters.length()));
        }
        return new String(text);
    }

    public String returnCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/London"));
        String CurrentDate = simpleDateFormat.format(new Date());
        return CurrentDate;
    }

    public String getAttribute(ILocator locator, String elementToGet) {
        WebElement element = get_element(locator);
        return element.getAttribute(elementToGet);
    }

    public String getAttribute(WebElement webElement, String elementToGet) {
        return webElement.getAttribute(elementToGet);
    }

}