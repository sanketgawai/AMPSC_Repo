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
import testComponant.BaseTest;

public class OnlineApplication extends BaseTest {

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
	
	
	@Test(dataProvider="getData")
	//public void setUpOnlineApplicationAndValidateRegistration(String eml,String mob,String otpRecEmail,String otpRecMob,String nikName, String frenName,String bloodTyp,String hsc,String tcherName,String sportPer)
	public void setUpOnlineApplicationAndValidateRegistration( HashMap<String, String> data)
	{
		HomePage homePage = new HomePage(driver);
		OnlineApplicationPage onlineApplicationPage = homePage.goToOnlineApplicationSystemPage();
		//onlineApplicationPage.registration("saitama@gmail.com", "1238524659", "123", "321", "strongest", "saitama", "O", "2020", "devin", "martialarts");
		//onlineApplicationPage.registration(eml, mob, otpRecEmail, otpRecMob, nikName, frenName, bloodTyp, hsc, tcherName, sportPer);
		 onlineApplicationPage.registration(
		            data.get("Email Id"),
		            data.get("Mobile Number"),
		            data.get("OtpReceivedOnEmail"),
		            data.get("OtpReceivedOnMobile"),
		            data.get("NickName"),
		            data.get("FriendName"),
		            data.get("BloodType"),
		            data.get("Hsc"),
		            data.get("SchooTeacherName"),
		            data.get("SportTeacherName")
		    );
		Assert.assertEquals(onlineApplicationPage.checkRegistorButtonDisable(), true);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		//return new Object [][] {{"saitama@gmail.com", "1238524659", "123", "321", "strongest", "saitama", "O", "2020", "devin", "martialarts"},{"naruto@gmail.com", "1238524651", "456", "654", "hokage", "naruto", "A", "2021", "jiraya", "ninjutsu"}};
		DataDrivenFromExcel dataDrivenFromExcel = new DataDrivenFromExcel();
		return dataDrivenFromExcel.getDataFromExcel("Sheet1");
	}
	
	@AfterMethod(enabled=false)
	public void closeApplication()
	{
		driver.quit();
	}
}
