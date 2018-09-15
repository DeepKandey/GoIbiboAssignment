package com.qa.Pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.base.Base;

public class FlightSelectionPage extends Base {

	@FindBy(xpath = "//iframe[contains(@name,'notification-frame-~')]")
	public WebElement notificationframe;

	@FindBy(xpath = "//div[@class='close tablecell']")
	public WebElement closeFrameOption;

	@FindBy(xpath = "//input[@type='button']")
	public WebElement bookFlightBtn;

	@FindBy(xpath = "//div[@id='onwFltContainer']//div[@class='card-block fl width100 padT20 padB15']//div[@class='col-md-3 col-sm-4 col-xs-4']/span[1]")
	public List<WebElement> lstOfDepartureTime;

	@FindBy(xpath = "//div[@id='retFltContainer']//div[@class='card-block fl width100 padT20 padB15']//div[@class='col-md-3 col-sm-4 col-xs-4']/span[1]")
	public List<WebElement> lstOfReturnTime;

	@FindBy(xpath = "//*[@class='fl posAbs icon-close1 ico17 curPoint fb whiteBg closePers black']")
	public WebElement adCloseOption;

	public FlightSelectionPage() {
		PageFactory.initElements(driver, this);
	}

	public void selectFlightTime() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='button']")));

		try {
			driver.switchTo().frame(notificationframe);
			closeFrameOption.click();
			driver.switchTo().defaultContent();
		} catch (Exception e) {
		}

		ArrayList<String> timeList = new ArrayList<String>();

		for (int i = 0; i < lstOfDepartureTime.size(); i++) {
			timeList.add(lstOfDepartureTime.get(i).getAttribute("innerHTML"));
		}

		Collections.sort(timeList);
		System.out.println("Departure time slots-->" + timeList);

		String beforePath = "(//div[@id='onwFltContainer']//div[@class='card-block fl width100 padT20 padB15']//div[@class='col-md-3 col-sm-4 col-xs-4']/span[1])[";
		String afterPath = "]/parent::div/parent::div/parent::div/following-sibling::div//div/label/div";

		for (int i = 0; i < lstOfDepartureTime.size(); i++) {
			if (lstOfDepartureTime.get(i).getAttribute("innerHTML").equals(timeList.get(0))) {
				wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath(beforePath + (i + 1) + afterPath))));
				driver.findElement(By.xpath(beforePath + (i + 1) + afterPath)).click();
				break;
			}
		}

		ArrayList<String> ReturntimeList = new ArrayList<String>();

		for (int i = 0; i < lstOfReturnTime.size(); i++) {
			ReturntimeList.add(lstOfReturnTime.get(i).getAttribute("innerHTML"));
		}

		Collections.sort(ReturntimeList);
		System.out.println("Return time slots-->" + ReturntimeList);

		String beforePath1 = "(//div[@id='retFltContainer']//div[@class='card-block fl width100 padT20 padB15']//div[@class='col-md-3 col-sm-4 col-xs-4']/span[1])[";
		String afterPath1 = "]/parent::div/parent::div/parent::div/following-sibling::div//div/label/div";
		for (int i = 0; i < lstOfReturnTime.size(); i++) {
			if (lstOfReturnTime.get(i).getAttribute("innerHTML").equals(ReturntimeList.get(0))) {
				try {
					WebElement close = driver.findElement(
							By.xpath("//*[@class='fl posAbs icon-close1 ico17 curPoint fb whiteBg closePers black']"));
					if (close.isDisplayed()) {
						close.click();
					}
				} catch (Exception e) {
					js.executeScript("arguments[0].scrollIntoView(true)", lstOfReturnTime.get(i));
					js.executeScript("window.scrollBy(0, -370)");
					driver.findElement(By.xpath(beforePath1 + (i + 1) + afterPath1)).click();
				}
				js.executeScript("arguments[0].scrollIntoView(true)", lstOfReturnTime.get(i));
				js.executeScript("window.scrollBy(0, -370)");
				driver.findElement(By.xpath(beforePath1 + (i + 1) + afterPath1)).click();
				break;
			}
		}
	}

}
