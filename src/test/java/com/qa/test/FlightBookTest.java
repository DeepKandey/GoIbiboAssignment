package com.qa.test;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.qa.Pages.FlightSelectionPage;
import com.qa.Pages.FlightReviewPage;
import com.qa.Pages.FlightSearchPage;
import com.qa.base.Base;
import com.qa.util.TestUtil;

public class FlightBookTest extends Base {
	
	public FlightSearchPage flightSearchPage;
	public FlightSelectionPage flightSelectionPage;
	public FlightReviewPage flightReviewPage;

	@BeforeMethod
	public void setUp() {
		initiate_driver();
		driver.get(TestUtil.APP_URL);
		flightSearchPage= new FlightSearchPage();
		flightSelectionPage= new FlightSelectionPage();
		flightReviewPage= new FlightReviewPage();
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null)
			driver.quit();
	}
	
	public void FlightBookValidation() throws InterruptedException {
		flightSelectionPage=flightSearchPage.submitFlightDetails();
		flightSelectionPage.selectFlightTime();
		flightSelectionPage.bookFlightBtn.click();
		
		wait.until(ExpectedConditions.visibilityOf(flightReviewPage.totalAmountTxt));
		
		String totalAmountTxt=flightReviewPage.totalAmountTxt.getAttribute("innerHTML");
		String proceedToPaymentTxt=flightReviewPage.proceedToPaymentText.getAttribute("innerHTML");
		
		Assert.assertEquals(totalAmountTxt, "Total Amount");
		Assert.assertEquals(proceedToPaymentTxt, "Proceed to Payment");
	}
}
