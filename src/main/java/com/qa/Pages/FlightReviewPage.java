package com.qa.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.Base;

public class FlightReviewPage extends Base {

	@FindBy(xpath = "//div[@class='blueBgLt fl padTB10 width100 ']/span[1]/span")
	public WebElement totalAmountTxt;

	@FindBy(xpath = "//button[@class='button orange col-md-3 fr large']")
	public WebElement proceedToPaymentText;

	public FlightReviewPage() {
		PageFactory.initElements(driver, this);
	}
}
