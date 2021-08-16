package main;

import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import pages.SignIn;
import reusableclasses.ChromeDriverBase;
import reusableclasses.Excel;

public class SignInRunner {
	
  WebDriver driver;
  SignIn signIn = null;
	
  ExtentReports report;
  ExtentTest test;
  

  @BeforeTest
  public void extentReports() {
	  report = new ExtentReports();
	  report.attachReporter(new ExtentHtmlReporter("SignIn.html"));
	  test = report.createTest("SignInTest");
  }
  
  @Test(dataProvider = "dp")
  public void signInTest(String emailAddress, String password, String url) throws Exception {
	  signIn = new SignIn();
	  test.info("logging in");
	  boolean isSignedIn = signIn.clickSignIn(driver, emailAddress, password, url, report, test);
	  assertTrue(isSignedIn);
	  test.pass("test case passesd");
  }
  
  @BeforeMethod
  public void beforeMethod() {
	  driver = ChromeDriverBase.getChromeDriver();
  }

  @AfterMethod
  public void afterMethod() {
	  report.flush();
	  driver.quit();
  }
  
  @DataProvider
	public Object[][] dp() {
	 
		Excel excel = new Excel("src/test/resources/Files/ExpediaTc.xlsx");
		Object data[][] = new Object[ excel.getLastRowNum("SignIn")][3];
		for (int i = 1; i <= excel.getLastRowNum("SignIn");i++) {
			data[i-1][0] = excel.readData("SignIn", i, 0);
			data[i-1][1] = excel.readData("SignIn", i, 1);
			data[i-1][2] = excel.readData("SignIn", i, 2);
		}
		return data;
	}

}
