package com.iat.b2btests.executors;

import java.util.concurrent.TimeUnit;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.iat.b2btests.locators.ILocator;

public class SeleniumExecutor implements IExecutor {

	private WebDriver driver;
	private WebDriverWait waitDriver;
	private String base_url;
	
	public SeleniumExecutor() {
		//TODO: load from config
		System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		this.driver = new FirefoxDriver();
		this.waitDriver = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		this.base_url = "http://10.10.32.126:8920";
	}
	
	public void start() {
		//Empty
	}

	public void stop() {
		driver.quit();
	}
	
	public WebElement get_element(ILocator locator) {
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
			default:
				break;
			}
		    return element;
		}	    
	    catch(org.openqa.selenium.NoSuchElementException e) {
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
		}	    
	    catch(org.openqa.selenium.NoSuchElementException e) {
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
	
	public void clear_textarea(ILocator locator){
		WebElement element = get_element(locator);
		element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		element.sendKeys(Keys.DELETE);
	}
	
	public void submit(ILocator locator) {
		WebElement element = this.get_element(locator);
		element.submit();
	}
	
	public void click(ILocator locator) {
		WebElement element = get_element(locator);
		element.click();
	}

	public boolean isPresent(ILocator locator) {
		WebElement element = get_element(locator);
		
		if (element == null || !element.isDisplayed()) {
			return false;
		} else {
            return true;
		}
	}
	
	public boolean arePresent(List<ILocator> elementList) {
		Boolean present = false;

		for (ILocator locator : elementList) {
			if (isPresent(locator)) {
				present = true;
			}
			else {
				System.out.println("Element not present: " + locator.get_body());
				present = false;
				break;
			}
		}
		return present;
	}
	
	public boolean isVisible(ILocator locator) {
		WebElement element = get_element(locator);
		
		if (!element.isDisplayed()) {
			return false;
		} else {
            return true;
		}
	}
	
	public boolean isEnabled(ILocator locator) {
		WebElement element = get_element(locator);
		if (element.isEnabled()) {
			return true;
		} else {
            return false;
		}
	}
	
	public boolean areEnabled(List<ILocator> elementList) {
		Boolean enabled = false;
		
		for (ILocator locator : elementList) {
			if (isEnabled(locator)) {
				enabled = true;
			}
			else {
				enabled = false;
				System.out.println("Element not enabled: " + locator.get_body());
				break;
			}
		}
		return enabled;
	}

	public boolean check_element_contains_text(ILocator locator, String text) {
		WebElement element = get_element(locator);
		String element_text = "";
		if(element.getAttribute("value") == null) {
			element_text = element.getText();
		}
		else {
			element_text = element.getAttribute("value");
		}
		return element_text.contains(text);
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
	
	public boolean isChecked(ILocator locator) {
		WebElement element = get_element(locator);
		if (element.isSelected() == true) {
			return true;
		}
		else {
			return false;
		}			
	}
	
	public String getUrl() {
		return driver.getCurrentUrl();
	}
	
	public String getText(ILocator locator) {
		waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.get_body())));
		WebElement element = get_element(locator);
		return element.getText();
	}
	
	public String getValue(ILocator locator) {
		waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.get_body())));
		WebElement element = get_element(locator);
		return element.getAttribute("value");
	}
	
	public List<WebElement> getAllOptions(ILocator locator) {
		Select select = new Select(get_element(locator));
		List<WebElement> options = select.getOptions();
		return options;
	}
	
	public String getSelectedOption(ILocator locator) {
		Select select = new Select(get_element(locator));
		return select.getFirstSelectedOption().getText();
	}
	
	public String getAttribute(ILocator locator, String attribute) {
		WebElement element = get_element(locator);
		return element.getAttribute(attribute);
	}
	
	public void selectOption(ILocator locator, String optionToSelect) { 
		Select select = new Select(get_element(locator));
		select.selectByVisibleText(optionToSelect);
	}
		
	public void waitUntilElementAppears(ILocator locator) {
		try {
			driver.findElement(By.xpath(locator.get_body()));
			waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.get_body())));
		}
		catch(Exception e) {
			
		}
	}
	
	public void waitUntilElementDisappears(ILocator locator) {
		try {
			driver.findElement(By.xpath(locator.get_body()));
			waitDriver.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator.get_body())));
		}
		catch(Exception e) {
			
		}
	}
	
	public void waitAbsolute(int waitTime) {
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void actionFocusElement(ILocator locator) {
		WebElement element = get_element(locator);
		new Actions(driver).moveToElement(element).perform();
	}
	
	public void actionMoveByOffset(ILocator locator, int xOffset, int yOffset) {
		Actions builder = new Actions(driver);
		
		WebElement element = get_element(locator);
		builder.moveToElement(element, 0, 0)
			   .moveByOffset(xOffset, yOffset)
			   .click();
		
		Action moveByOffset = builder.build();
		moveByOffset.perform();		
	}
}
