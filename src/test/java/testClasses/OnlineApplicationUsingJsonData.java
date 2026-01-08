package testClasses;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pomClasses.HomePage;
import pomClasses.OnlineApplicationPage;
import rhl.data.DataDrivenFromExcel;
import rhl.data.DataDrivenFromJson;
import testComponant.BaseTest;

public class OnlineApplicationUsingJsonData extends BaseTest {

	WebDriver driver;
	
	@BeforeMethod
	public void openApplication()
	{
		driver = initializeBrowserAndOpenApplication();
		openMPSCHomePage();
	}
	
	@Test(groups= {"first"})
	public void setUpOnlineApplicationAndPageValidationOfRegistrationPage()
	{		
		HomePage homePage = new HomePage(driver);
		homePage.goToOnlineApplicationSystemPage();	
		Assert.assertEquals(driver.getCurrentUrl(), "https://mpsconline.gov.in/candidate/registration");
	}
	
	
	@Test(dataProvider = "getData")
	public void setUpOnlineApplicationAndValidateRegistration(
	        HashMap<String, String> data) {

	    HomePage homePage = new HomePage(driver);
	    OnlineApplicationPage onlineApplicationPage =
	            homePage.goToOnlineApplicationSystemPage();

	    onlineApplicationPage.registration(
	            data.get("emailId"),
	            data.get("mobileNumber"),
	            data.get("otpReceivedOnEmail"),
	            data.get("otpReceivedOnMobile"),
	            data.get("nickName"),
	            data.get("friendName"),
	            data.get("bloodType"),
	            data.get("hsc"),
	            data.get("schoolTeacherName"),
	            data.get("sportTeacherName")
	    );

	    Assert.assertTrue(onlineApplicationPage.checkRegistorButtonDisable());
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		DataDrivenFromJson jsonData = new DataDrivenFromJson();
	    return jsonData.getDataFromJson();
	}
	
	@AfterMethod(enabled=false)
	public void closeApplication()
	{
		driver.quit();
	}
}
