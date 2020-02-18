package com.iat.b2btests.actions;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.iat.b2btests.executors.IExecutor;
import com.iat.b2btests.locators.ILocator;
import com.iat.b2btests.navigations.AbstractPage;
import com.steadystate.css.parser.ParseException;


public class GenericActions extends AbstractPage {
	
	private String cLocation;
	
	public GenericActions(IExecutor executor) {
		super(executor, "");
	}
	
	public boolean validateDate(String date2Bvalidated) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY hh:mm a");

		try {
			if(date2Bvalidated != null && !date2Bvalidated.isEmpty() && !date2Bvalidated.equals(" ")) {
				format.parse(date2Bvalidated);
				return true;
			}
			else {
				return true;
			}
		} catch (java.text.ParseException e) {
			e.printStackTrace();
			return false;
		}				
	}	
	
	public boolean validateUrl(String url) throws ParseException {
		try {
			URL vUrl = new URL(url);
			return true;
		}
		catch (MalformedURLException e) {
			return false;
		}				
	}	
	
	public String serialize() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		return dateFormat.format(date);		
	}
	
	public String getCurrentUrl() {
		try {
			URL url = new URL(executor.getUrl());
			cLocation = url.getPath();
		}
		catch (MalformedURLException e) {
			System.err.println("not a valid URL");
		}
		return cLocation;
	}
	
	public void enterText(ILocator locator, String input) {
		executor.check(locator);
		executor.send_keys(locator, input);
	}
	
	public void selectOption(ILocator locator, String optionToSelect) {
		List<WebElement> options = executor.getAllOptions(locator);
		
		if(optionToSelect == "random_item") {
			Collections.shuffle(options);
			
			for(WebElement option : options){
				if(!option.getText().equals("- select -")) {
					executor.selectOption(locator, option.getText());
					break;
				}
			}
		}
		else {
			executor.selectOption(locator, optionToSelect);
		}
	}
	
	public List<String> sortList(List<String> passedList) {
		Collections.sort(passedList);
		return passedList;
	}
	
	
	public boolean isUniqueTextInElements(ILocator locator) {
		List<WebElement> elemList = executor.get_elements(locator);
		
		HashSet<String> setTexts = new HashSet<String>();
		for (WebElement element: elemList) {
			setTexts.add(element.getText());
		}
		
		if(setTexts.size() != elemList.size()) {
			return false;
		}
		else {
			return true;
		}
		
	}
}
