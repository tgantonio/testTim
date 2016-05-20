package pageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.ExtentFactory;

public class RentalCarPage {
	ExtentTest test;
	WebDriver driver;
	ExtentReports report;

	public RentalCarPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);

	}

	// Rental tab
	@FindBy(id = "flightmanager-tab-3")
	WebElement rentalCarTab;

	// Pick-up location
	@FindBy(id = "flightmanagerCarFormpickup-location-name")
	WebElement pickUpLocation;

	// Pick-up date
	@FindBy(id = "flightmanagerCarFormpickup-date-display")
	WebElement pickUpDate;

	// Pick-up Time
	@FindBy(id = "flightmanagerCarFormpickup-time")
	WebElement pickUpTime;

	// Drop-off Location
	@FindBy(id = "flightmanagerCarFormdropoff-location-name")
	WebElement dropOffLocation;

	// Drop-off Date
	@FindBy(id = "flightmanagerCarFormdropoff-date-display")
	WebElement dropOffDate;

	// Drop-off Time
	@FindBy(id = "flightmanagerCarFormdropoff-time")
	WebElement dropOffTime;

	// Car Category
	@FindBy(id = "flightmanagerCarFormCarCategory")
	WebElement carCategory;

	// Search Rental Car Button
	@FindBy(xpath = "//button[text()='Search rental car']")
	WebElement searchRentalCarButton;

	// Actions--------------------------------------------------------------------------------------------

	
	
	
	//Fill in Pick-up Location
	public void fillPickUpLocation(String location) throws InterruptedException{
		pickUpLocation.sendKeys(location);
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//*[@id='flightmanagerCarFormpickup-location-namePopupList']/li")).click();
		
	}
	
	//Fill in Drop-off Location
		public void fillDropOffLocation(String location) throws InterruptedException{
			dropOffLocation.sendKeys(location);
			Thread.sleep(1000);
			driver.findElement(By.xpath(".//*[@id='flightmanagerCarFormdropoff-location-namePopupList']/li")).click();
			
		}
	

	// Set Pick-up Date
	public void setPickUpDate(String _date) {
		pickUpDate.click();

		WebElement cal = driver
				.findElement(By.xpath("//div[@id='kosa-cal-modal-3']//div[@class='months-wrapper']//div[1]"));
		List<WebElement> dates = cal.findElements(By.tagName("button"));
		for (WebElement date : dates) {
			if (date.getText().equals(_date)) {
				date.click();
				driver.findElement(By.xpath(".//*[@id='kosa-cal-modal-4']/div/div/div[1]/button")).click();
				break;

			}
		}
	}

	// Set Drop-off Date
	public void setDropOffDate(String _date) {
		dropOffDate.click();

		WebElement cal = driver
				.findElement(By.xpath("//div[@id='kosa-cal-modal-4']//div[@class='months-wrapper']/div[2]"));
		List<WebElement> dates = cal.findElements(By.tagName("button"));
		for (WebElement date : dates) {
			if (date.getText().equals(_date)) {
				date.click();
				
				break;							

			}
		}

	}

	
	// Set Pick-up Time
	public void setPickUpTime(String time) {
		pickUpTime.click();
		Select sel = new Select(pickUpTime);
		sel.selectByValue(time);
	}
	
	// Set Drop-off Time
		public void setDropOffTime(String time) {
			dropOffTime.click();
			Select sel = new Select(pickUpTime);
			sel.selectByValue(time);
		}
	

	// Set Car Category
	public void setCarCategory(String _carCategory) {
		Select sel = new Select(carCategory);
		sel.selectByValue(_carCategory);
				
	}
	
	
	
	
	public boolean verifyErrorMessage() {
		WebElement errorMessage = null;
		try {
			errorMessage = driver.findElement(By.xpath(".//*[@id='flightmanagerCarForm']/div[1]/div/div/ul"));
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
	
	
	
	//___________________________________making my script neat and readable
	
	
	//Search rental car
	public void searchRentalCar(String _pickUpLocation,String _pickUpDate, String _pickUpTime, String _dropOffLocation, String _dropOffDate, String _dropOffTime, String _carCategory) throws InterruptedException{
		
		
		
		rentalCarTab.click();
		test.log(LogStatus.INFO, "Car rental tab clicked...");
		
		fillPickUpLocation(_pickUpLocation);
		test.log(LogStatus.INFO, "Entered pick-up location...");
		
		setPickUpDate(_pickUpDate);
		test.log(LogStatus.INFO, "Set pick-up date...");
		
		setPickUpTime(_pickUpTime);
		test.log(LogStatus.INFO, "Set pick-up time....");
		
		fillDropOffLocation(_dropOffLocation);
		test.log(LogStatus.INFO, "Entered drop-off location...");
		
		setDropOffDate(_dropOffDate);
		test.log(LogStatus.INFO, "Set drop-off date...");
		
		setDropOffTime(_dropOffTime);
		test.log(LogStatus.INFO, "Set drop-off time...");
		
		setCarCategory(_carCategory);
		test.log(LogStatus.INFO, "Picked car category...");
		
		searchRentalCarButton.click();
		Thread.sleep(2000);
		test.log(LogStatus.INFO, "Rental Car Button clicked...");
		
		
		
		
		
		
	}
	
	

}
