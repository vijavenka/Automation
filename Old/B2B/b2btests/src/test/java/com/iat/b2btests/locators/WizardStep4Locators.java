package com.iat.b2btests.locators;

public class WizardStep4Locators implements IPageLocators {
	
	public Locator coloursMain_input = new Locator(LocatorType.XPATH, "//input[contains(@class, 'wls-brandcolor-input-main')]");
	public Locator coloursFont_input = new Locator(LocatorType.XPATH, "//input[contains(@class, 'wls-brandcolor-input-font')]");
	public Locator coloursPreview_div = new Locator(LocatorType.XPATH, "//div[@class='wls-brandcolor-preview']");
	public Locator coloursPreview_img = new Locator(LocatorType.XPATH, "//div[@class='wls-brandcolor-preview']/following-sibling::img");
	public Locator coloursReset_link = new Locator(LocatorType.XPATH, "//a[contains(@class, 'wls-brandcolor-reset')]");
	public Locator coloursPicker_div = new Locator(LocatorType.XPATH, "//div[@class='colorpicker dropdown-menu'][1]");
	public Locator coloursPickerCurrentColor_i = new Locator(LocatorType.XPATH, "//i[@class='colorpicker dropdown-menu')][1]/div[@class='colorpicker-saturation']/i");
	public Locator coloursPreviewChanged_div = new Locator(LocatorType.XPATH, "//div[@class='wls-brandcolor-preview' and contains(@style, 'rgb(153, 182, 255)') and contains(@style, 'rgb(24, 84, 9)')]");
	public Locator coloursPreviewDefault_div = new Locator(LocatorType.XPATH, "//div[@class='wls-brandcolor-preview' and contains(@style, 'rgb(245, 245, 245)') and contains(@style, 'rgb(0, 0, 0)')]");
	
	public Locator logoFile_input = new Locator(LocatorType.XPATH, "//input[@class='wls-input-logo-image' and @type='file']");
	public Locator logoPreview_img = new Locator(LocatorType.XPATH, "//div[@class='wls-logo-image-preview']/img");
	
	public Locator headerTabNoHeader_link = new Locator(LocatorType.XPATH, "//li[contains(@class, 'wls-tab-header-none')]/a");
	public Locator headerTabNoHeaderSelected_link = new Locator(LocatorType.XPATH, "//li[contains(@class, 'wls-tab-header-none')]/a/span[@class='icon-check']");
	public Locator headerTabImage_link = new Locator(LocatorType.XPATH, "//li[contains(@class, 'wls-tab-header-image')]/a");
	public Locator headerTabImageSelected_link = new Locator(LocatorType.XPATH, "//li[contains(@class, 'wls-tab-header-image')]/a/span[@class='icon-check']");
	public Locator headerImgFile_input = new Locator(LocatorType.XPATH, "//input[@class='wls-input-header-image' and @type='file']");
	public Locator headerImgPreview_img = new Locator(LocatorType.XPATH, "//div[@class='wls-header-image-preview']/img");
	public Locator headerTabHTML_link = new Locator(LocatorType.XPATH, "//li[contains(@class, 'wls-tab-header-html')]/a");
	public Locator headerTabHTMLSelected_link = new Locator(LocatorType.XPATH, "//li[contains(@class, 'wls-tab-header-html')]/a/span[@class='icon-check']");
	public Locator headerHTML_textarea = new Locator(LocatorType.XPATH, "//textarea[contains(@class, 'wls-input-header-html')]");
	public Locator headerTabLink_link = new Locator(LocatorType.XPATH, "//li[contains(@class, 'wls-tab-header-link')]/a");
	public Locator headerTabLinkSelected_link = new Locator(LocatorType.XPATH, "//li[contains(@class, 'wls-tab-header-link')]/a/span[@class='icon-check']");
	public Locator headerLink_input = new Locator(LocatorType.XPATH, "//input[contains(@class, 'wls-input-header-link')]");
	
	public Locator footerTabNoFooter_link = new Locator(LocatorType.XPATH, "//li[contains(@class, 'wls-tab-footer-none')]/a");
	public Locator footerTabNoFooterSelected_link = new Locator(LocatorType.XPATH, "//li[contains(@class, 'wls-tab-footer-none')]/a/span[@class='icon-check']");
	public Locator footerTabImage_link = new Locator(LocatorType.XPATH, "//li[contains(@class, 'wls-tab-footer-image')]/a");
	public Locator footerTabImageSelected_link = new Locator(LocatorType.XPATH, "//li[contains(@class, 'wls-tab-footer-image')]/a/span[@class='icon-check']");
	public Locator footerImgFile_input = new Locator(LocatorType.XPATH, "//input[@class='wls-input-footer-image' and @type='file']");
	public Locator footerImgPreview_img = new Locator(LocatorType.XPATH, "//div[@class='wls-footer-image-preview']/img");
	public Locator footerTabHTML_link = new Locator(LocatorType.XPATH, "//li[contains(@class, 'wls-tab-footer-html')]/a");
	public Locator footerTabHTMLSelected_link = new Locator(LocatorType.XPATH, "//li[contains(@class, 'wls-tab-footer-html')]/a/span[@class='icon-check']");
	public Locator footerHTML_textarea = new Locator(LocatorType.XPATH, "//textarea[contains(@class, 'wls-input-footer-html')]");
	public Locator footerTabLink_link = new Locator(LocatorType.XPATH, "//li[contains(@class, 'wls-tab-footer-link')]/a");
	public Locator footerTabLinkSelected_link = new Locator(LocatorType.XPATH, "//li[contains(@class, 'wls-tab-footer-link')]/a/span[@class='icon-check']");
	public Locator footerLink_input = new Locator(LocatorType.XPATH, "//input[contains(@class, 'wls-input-footer-link')]");
}
