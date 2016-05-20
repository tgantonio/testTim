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

public class Test2 {

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
		test = report.startTest("Lufthansa Booking2");
		fp = new FlightsPage(driver, test);
		rcp = new RentalCarPage(driver, test);
		hp = new HotelPage(driver, test);
		driver.manage().window().maximize();
		test.log(LogStatus.INFO, "Maximized browser");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

	}

//	@Test(priority=3) // Flight Page
//	public void test() throws InterruptedException {
//		
//
//		screenShotFilename = "test";
//		fp.searchFlight("Davao", "Cebu", "15", "20");
//		test.log(LogStatus.INFO, "Flight booking processing!");
//
//		boolean result = fp.verifyErrorMessage();
//		Assert.assertTrue(result);
//
//		test.log(LogStatus.PASS, "Showing no error!");
//
//	}
	
	 @Test//(priority=2) // Rental Car
	 public void test2() throws InterruptedException {

	 Thread.sleep(1000);
	 screenShotFilename = "test2_clone2";
	 rcp.searchRentalCar("Davao", "15", "11:00", "Cebu", "26", "16:30", "L");
	 test.log(LogStatus.INFO, "Car Rental processing!");
	 boolean result = rcp.verifyErrorMessage();
    	Assert.assertTrue(result);
	
	 test.log(LogStatus.INFO, "Car rental initiated...");
	 driver.navigate().to(baseUrl);
	
	 }
	
//	 @Test(priority=1) // Hotel
//	 public void test3() throws InterruptedException {
//		 
//		
//	 
//	 Thread.sleep(1000);
//	 screenShotFilename = "test3";
//	 hp.searchingHotel("london", "14", "28");
//	 test.log(LogStatus.INFO, "Search hotel initiated...!");
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
