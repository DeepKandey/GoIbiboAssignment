package com.qa.Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.Base;

public class FlightSearchPage extends Base {

	@FindBy(id = "gi_roundtrip_label")
	public WebElement roundTripBtn;

	@FindBy(id = "gosuggest_inputSrc")
	public WebElement srcTxtBox;

	@FindBy(id = "gosuggest_inputDest")
	public WebElement destTxtBox;

	@FindBy(xpath = "descendant::input[@class='form-control inputTxtLarge widgetCalenderTxt'][1]")
	public WebElement calendarBtn;

	@FindBy(xpath = "//div[@class='DayPicker-Week']//*[@class='DayPicker-Day DayPicker-Day--today DayPicker-Day--selected']/following-sibling::div")
	public WebElement departureDateOption1;

	@FindBy(xpath = "descendant::div[@class='DayPicker-Body'][1]/div/div[@class='DayPicker-Day DayPicker-Day--today DayPicker-Day--selected']/parent::div/following-sibling::div[1]/div[1]")
	public WebElement departureDateOption2;

	@FindBy(xpath = "//div[@class='DayPicker-Week']//*[@class='DayPicker-Day DayPicker-Day--selected']")
	public WebElement returnDate;

	@FindBy(id = "gi_search_btn")
	public WebElement searchBtn;

	public FlightSearchPage() {
		PageFactory.initElements(driver, this);
	}

	public FlightSelectionPage submitFlightDetails() throws InterruptedException {
		roundTripBtn.click();

		srcTxtBox.sendKeys("Bangalore");
		Thread.sleep(1000);
		srcTxtBox.sendKeys(Keys.DOWN, Keys.RETURN);

		destTxtBox.sendKeys("Mumbai");
		Thread.sleep(1000);
		destTxtBox.sendKeys(Keys.DOWN, Keys.RETURN);

		calendarBtn.click();

		try {
			departureDateOption1.click();
		} catch (Exception e) {
			departureDateOption2.click();
		}
		returnDate.click();
		searchBtn.click();
		return new FlightSelectionPage();
	}
}
