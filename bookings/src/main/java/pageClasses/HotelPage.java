package pageClasses;

import java.util.List;
import java.util.Set;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.ExtentFactory;

public class HotelPage {
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	
	public HotelPage(WebDriver driver, ExtentTest test){
		this.driver = driver;
		this.test=test;
		PageFactory.initElements(driver, this);
	}

	//Hotel Tab
	@FindBy(id="flightmanager-tab-4")
	WebElement hotelTab;
	
	//Destination
	@FindBy(id="flightmanagerHotelFormdestination-name")
	WebElement destinationBox;
	
	//Check-in Date
	//check 2 f's
	@FindBy(id="flightmanagerHotelFormCheckinDateDisplay")
	WebElement checkInDate;
	
	//Check-out Date
	@FindBy(id="flightmanagerHotelFormCheckoutDateDisplay")
	WebElement checkOutDate;
	
	//Search Hotel Button
	@FindBy(xpath="//button[text()='Search hotel']")
	WebElement searchHotelButton;
	
	
	
	//Actions--------------------------------------------------------------------------
	
	//Click Hotel Tab
		public void clickHotelTab(){
			hotelTab.click();
			
		}
		
	//Fill in Destination Box
	public void fillInDestinationBox(String destination){
		destinationBox.sendKeys("Manila");
	}
	
	//set Check-in Date
	public void setCheckInDate(String _date){
		checkInDate.click();
		WebElement cal =driver.findElement(By.xpath("//div[@id='kosa-cal-modal-5']//div[@class='months-wrapper']//div[1]"));
		List<WebElement> dates = cal.findElements(By.tagName("button"));
		for (WebElement date : dates) {
			if (date.getText().equals(_date)) {
				date.click();
				driver.findElement(By.xpath(".//*[@id='kosa-cal-modal-6']/div/div/div[1]/button")).click();
				break;
				
			}
		}
	}
	
	
	
	
	//set Check-out Date
		public void setCheckOutDate(String _date){
			checkOutDate.click();
			WebElement cal =driver.findElement(By.xpath("//div[@id='kosa-cal-modal-6']//div[@class='months-wrapper']/div[2]"));
			List<WebElement> dates = cal.findElements(By.tagName("button"));
			for (WebElement date : dates) {
				if (date.getText().equals(_date)) {
					date.click();
					driver.findElement(By.xpath(".//*[@id='kosa-cal-modal-6']/div/div/div[1]/button")).click();
					break;
					
				}
			}
		}
		
		//Click Search Hotel Button
		public void clickSearchHotelButton() throws InterruptedException{
			String parentHandle = driver.getWindowHandle();
			searchHotelButton.click();
			Set<String>handles=driver.getWindowHandles();
			for (String handle : handles) {
				if (!handle.equals(parentHandle)) {
					driver.switchTo().window(handle);
					Thread.sleep(3000);
					driver.close();
					System.out.println("Closing new window!");
					driver.switchTo().window(parentHandle);
					System.out.println("AT parent handle");
				
					
				}
				
			}
			
			
			
		}
		
		
		public boolean verifyErrorMessage() {
			WebElement errorMessage = null;
			try {
				errorMessage = driver.findElement(By.xpath(".//*[@id='flightmanagerHotelForm']/div[1]/div/div/ul"));
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
		
		
		
		//________________________________________________________________making my script neat and readable
		//Search hotel
		public void searchingHotel(String _city, String _checkInDate, String _checkOutDate) throws InterruptedException{
			
			 
			
			
			clickHotelTab();
			test.log(LogStatus.INFO, "Clicked  Hotel Tab");
			
			destinationBox.sendKeys(_city);
			test.log(LogStatus.INFO, "Entered Destination");
			
			setCheckInDate(_checkInDate);
			test.log(LogStatus.INFO, "Set check-in date");
			
			setCheckOutDate(_checkOutDate);
			test.log(LogStatus.INFO, "Set check-out date");
			
			clickSearchHotelButton();
			test.log(LogStatus.INFO, "Clicked searh hotel....");
			
		}
		
	
	
}
