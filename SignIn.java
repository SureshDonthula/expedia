package pages;

import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class SignIn {
	
	WebDriver driver = null;
	HomePage landingPage = null;
	ExtentReports report = null;
	ExtentTest test = null;
	
	
	String relXpath = "//div[contains(text(),'Sign in')]";
	By signIn = By.xpath(relXpath);
	By emailAddress = By.id("signin-loginid");
	By signInPassword = By.id("signin-password");
	By checkBox = By.id("signin-loggedin");
	By signInButton = By.xpath("/html/body/div[4]/div[1]/div/article/div[1]/form/fieldset[2]/button");
	By signOut = By.className("uitk-text uitk-type-300 uitk-text-default-theme");
	By stays = By.xpath("//li[contains(@class,'active')]//a[@role='tab']");
	By forgotPassword = By.linkText("Forgot your password?");
	
	private void loadExpedia(String url) {
		test.info("navigating to expedia");
		driver.navigate().to(url);
	}
	
	private void clickSignIn() throws Exception {
		test.info("clicking on sign in");
		HomePage homepage = new HomePage(driver);
		homepage.SignIn();
	}
	
	private void fillEmail(String email) {
		WebDriverWait wt = new WebDriverWait(driver,5);
		wt.until(ExpectedConditions.visibilityOfElementLocated(emailAddress));
		test.info("entering email");
		driver.findElement(emailAddress).sendKeys(email);
	}

	private void fillPassword(String password) {
		test.info("entering passowrd");
		driver.findElement(signInPassword).sendKeys(password);
	}
	
	private void clickSignInButton() {
		test.info("clicking on sign in button");
		driver.findElement(signInButton).click();
	}
	
	private boolean verify() {
		
		boolean isSignedIn = true;
		Scanner sc = new Scanner(System.in);
		System.out.println("if verified enter 1");
		int n = sc.nextInt();
		System.out.println("okay resuming...");
		
		try
		{
			WebDriverWait wt = new WebDriverWait(driver,5);
			wt.until(ExpectedConditions.visibilityOfElementLocated(stays));
		}
		catch(Exception e)
		{
			isSignedIn = false;
		}
		
		return isSignedIn;
	}
	
	public void forgotPassword(){
		driver.findElement(forgotPassword).click();
	}

	public  boolean clickSignIn(WebDriver driver, String email, String password, String url,ExtentReports report, ExtentTest test) throws Exception{
		
		
		//Initializing WebDriver
		this.driver = driver;
		//Initializing ExtentReports
		this.report = report;
		//Initializing test
		this.test = test;
		//redirecting to home page of expedia
		loadExpedia(url);
		//clicking signIn
		clickSignIn();
		//filling email
		fillEmail(email);
		//filling password
		fillPassword(password);
		//clicking sign in button
		clickSignInButton();
		//verifySignIn
		return verify();
        
	}
}
