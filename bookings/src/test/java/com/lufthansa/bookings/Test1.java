package com.lufthansa.bookings;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pageClasses.FlightsPage;
import pageClasses.HotelPage;
import pageClasses.RentalCarPage;
import utilities.ExtentFactory;
import utilities.Screenshots;

public class Test1 {

	WebDriver driver;

	// use for Listeners
	public WebDriver getDriver() {
		return driver;
	}

	ExtentReports report;
	ExtentTest test;

	FlightsPage fp;
	RentalCarPage rcp;
	HotelPage hp;
	String baseUrl = "http://www.lufthansa.com/uk/en/Homepage";

	// screenshot file name
	String screenShotFilename = "";

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get(baseUrl);
		report = ExtentFactory.getInstance();
		test = report.startTest("Lufthansa Booking");
		fp = new FlightsPage(driver, test);
		rcp = new RentalCarPage(driver, test);
		hp = new HotelPage(driver, test);
		driver.manage().window().maximize();
		test.log(LogStatus.INFO, "Maximized browser");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

	}

	@Test() // Flight Page
	public void flight() throws InterruptedException {
		

		screenShotFilename = "Flight";
		fp.searchFlight("London", "Paris", "19", "25");
		test.log(LogStatus.INFO, "Flight booking processing!");

		boolean result = fp.verifyErrorMessage();
		Assert.assertTrue(result);

		test.log(LogStatus.PASS, "Showing no error!");
		driver.navigate().to(baseUrl);

	}
//	
//	 @Test() // Rental Car
//	 public void carRental() throws InterruptedException {
//
//	 Thread.sleep(1000);
//	 screenShotFilename = "Car Rental";
//	 rcp.searchRentalCar("Davao", "15", "11:00", "Cebu", "26", "16:30", "L");
//	 test.log(LogStatus.INFO, "Car Rental processing!");
//	 boolean result = rcp.verifyErrorMessage();
//    	Assert.assertTrue(result);
//	
//	 test.log(LogStatus.INFO, "Car rental initiated...");
//	
//	 }
	
//	 @Test() // Hotel
//	 public void hotel() throws InterruptedException {
//		 
//		
//	 hp.clickHotelTab();
//	 test.log(LogStatus.INFO, "Clicked HOtel tab");
//	 Thread.sleep(1000);
//	 screenShotFilename = "Hotel";
//	// hp.searchingHotel("london", "14", "28");
//	 hp.clickSearchHotelButton();
//	 test.log(LogStatus.INFO, "Search hotel initiated...!");
//	 
//	 boolean result = hp.verifyErrorMessage();
//	 Assert.assertTrue(result);
//	 test.log(LogStatus.INFO, "Searching hotel initiated ");
//	
//	 }

	@AfterMethod
	public void tearDown(ITestResult testResult) throws IOException {

		if (testResult.getStatus() == ITestResult.FAILURE) {
			String path = Screenshots.takeScreenshot(driver, screenShotFilename);
			String imagePath = test.addScreenCapture(path);
			test.log(LogStatus.FAIL, imagePath);
		}
		report.endTest(test);
		report.flush();
	}

	@AfterClass
	public void afterClass() throws InterruptedException {
		Thread.sleep(2000);
		

		driver.quit();
	}

}
