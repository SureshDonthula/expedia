package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	WebDriver driver;
	WebDriverWait wt = null;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	By e_location = By.cssSelector("button[data-stid='location-field-destination-menu-trigger']");
	By selectDate = By.id("d1-btn");
	By done = By.cssSelector("button[data-stid='apply-date-picker']");
	By search =By.cssSelector("button[type='submit']");
	
	By sign_in =By.xpath("//button[normalize-space()='Sign in']");
	By signin_button =By.xpath("//a[normalize-space()='Sign in']");
	By signinInPopUp = By.linkText("Sign in");
	By createaccount_button =By.xpath("//a[normalize-space()='Create a free account']");
	
	public void SignIn() throws Exception {
		
		try
		{
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			Thread.sleep(5000);
			driver.findElement(sign_in).click();
			driver.findElement(signinInPopUp).click();
		}
		catch(NoSuchElementException e) {
			//driver.navigate().to("https://www.expedia.co.in/user/signin?ckoflag=0&uurl=e3id%3Dredr%26rurl%3D%2F");
			e.printStackTrace();
		}
	}
	
	public void SignIn_Button() {
		driver.findElement(signin_button).click();
	}
	
	public void CreateAccount_Button() {
		try
		{
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(sign_in).click();
			driver.findElement(createaccount_button).click();
		}
		catch(NoSuchElementException e) {
			driver.navigate().to("https://www.expedia.co.in/user/createaccount?%20&uurl=e3id%3Dredr%26rurl%3D%2F");
		}
		
	}
	
	public void Location(String cityName) {
		driver.findElement(e_location).sendKeys(cityName);
		driver.findElement(By.cssSelector("button[data-stid='location-field-destination-result-item-button']")).click();
	}
	
	public void Checkin(String checkindate) {
		wt = new WebDriverWait(driver, 20);
		driver.findElement(selectDate).click();	
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[aria-label='" + checkindate + "']" )));
		driver.findElement(By.cssSelector("button[aria-label='" + checkindate + "']" )).click();
	}
	
	public void Checkout(String checkoutdate) {
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[aria-label='" + checkoutdate + "']" )));
		driver.findElement(By.cssSelector("button[aria-label='" + checkoutdate + "']" )).click();
		wt.until(ExpectedConditions.visibilityOfElementLocated(done));
		driver.findElement(done).click();		
	}
	
	public void Search() {
		driver.findElement(search).click();
	}	
	
	public void Stay(String cityName,String checkindate,String checkoutdate) {
		//SignIn();
		//SignIn_Button();
		//CreateAccount_Button();
		Location(cityName);
		Checkin(checkindate);
		Checkout(checkoutdate);
		Search();
	}
}