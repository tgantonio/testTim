package pageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.ExtentFactory;

public class FlightsPage {
	
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	
	

	public FlightsPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;

		PageFactory.initElements(driver, this);
	}

	// Flights Tab
	@FindBy(id = "flightmanager-tab-1")
	WebElement flightsTab;

	// Round-tip Tab
	@FindBy(id = "flightmanagerFlightsFormReturnLabel")
	WebElement roundTrip;

	// One-way Tab
	@FindBy(id = "flightmanagerFlightsFormOnewayLabel")
	WebElement oneWay;

	// Origin Box
	@FindBy(id = "flightmanagerFlightsFormOrigin")
	WebElement originBox;

	// Destination Box
	@FindBy(id = "flightmanagerFlightsFormDestination")
	WebElement destinationBox;

	// Departing
	@FindBy(id = "flightmanagerFlightsFormOutboundDateDisplay")
	WebElement depDate;

	// Returning
	@FindBy(id = "flightmanagerFlightsFormInboundDateDisplay")
	WebElement retDate;

	// Number of Adult Passenger
	@FindBy(id = "flightmanagerFlightsFormAdults")
	public WebElement numAdult;

	// Cabin Class
	@FindBy(id = "flightmanagerFlightsFormCabin")
	public WebElement cabinClass;

	// Search Flights
	@FindBy(xpath = "//button[text()='Search flights']")
	public WebElement searchFlightButton;

	// actions------------------------------------------------------------------

	// Fill-in origin box
	public void fillInOrigin(String origin) throws InterruptedException {
		originBox.click();
		originBox.sendKeys(origin);
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//*[@id='flightmanagerFlightsFormOriginPopupList']/li")).click();
	}

	// Fill-in Destination Box
	public void fillInDestination(String destination) throws InterruptedException {
		destinationBox.click();
		destinationBox.sendKeys(destination);
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//*[@id='flightmanagerFlightsFormDestinationPopupList']/li")).click();
	}

	// Set Departure Date
	public void setDepartureDate(String _depDate) {
		depDate.click();
		WebElement depCal = driver
				.findElement(By.xpath("//div[@id='kosa-cal-modal-1']//div[@class='months-wrapper']//div[1]"));

		List<WebElement> depDates = depCal.findElements(By.tagName("button"));
		for (WebElement depDate : depDates) {

			if (depDate.getText().equals(_depDate)) {
				depDate.click();
				driver.findElement(By.xpath("//div[@id='kosa-cal-modal-2']/div/div/div[1]/button")).click();
				System.out.println("Closing calendar1");
				break;
			}
		}

	}

	// Set Returning Date
	public void setReturningDate(String _retDate) {
		retDate.click();
		WebElement retCal = driver
				.findElement(By.xpath("//div[@id='kosa-cal-modal-2']//div[@class='months-wrapper']/div[2]"));

		List<WebElement> retDates = retCal.findElements(By.tagName("button"));
		for (WebElement retDate : retDates) {

			if (retDate.getText().equals(_retDate)) {
				retDate.click();
				driver.findElement(By.xpath("//div[@id='kosa-cal-modal-2']/div/div/div[1]/button")).click();
				System.out.println("Closing calendar2");
				break;
			}
		}

	}

	public boolean verifyErrorMessage() {
		WebElement errorMessage = null;
		try {
			errorMessage = driver.findElement(By.xpath(".//*[@id='flightmanagerFlightsForm']/div[1]/div/div/ul//li"));
			if (errorMessage != null) {
				String msg = errorMessage.getText();
				String errorMassage = msg;
				test.log(LogStatus.FAIL,errorMassage);
				return false;

			}
			System.out.println("Error occured when processing by TIM!");

		} catch (Exception e) {
			// System.out.println(e.getMessage());
			System.out.println("No error occured when processing by TIM!");
			test.log(LogStatus.PASS, "Test passed by TIM!");
			return true;
		}
		return true;
	}

	// ________________________________________________________________making my
	// script neat and readable

	// Searching available flight
	public void searchFlight(String flightOrigin, String flightDestination, String departureDate, String returningDate)
			throws InterruptedException {
		
		

		flightsTab.click();
		test.log(LogStatus.INFO, "FLights tag clicked...");

		roundTrip.click();
		test.log(LogStatus.INFO, "Round-trip selected....");

		fillInOrigin(flightOrigin);
		test.log(LogStatus.INFO, "Entered flight origin...");

		fillInDestination(flightDestination);
		test.log(LogStatus.INFO, "Entered flight destination...");

		setDepartureDate(departureDate);
		test.log(LogStatus.INFO, "Set departure date...");

		setReturningDate(returningDate);
		test.log(LogStatus.INFO, "Set returning date...");

		searchFlightButton.click();
		Thread.sleep(2000);
		test.log(LogStatus.INFO, "Search Button clicked...");

	}

}
