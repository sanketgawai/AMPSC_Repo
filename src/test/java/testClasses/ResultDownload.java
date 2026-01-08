package testClasses;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import abstractComponant.AbstractComponant;
import pomClasses.HomePage;
import pomClasses.ResultMerit;
import testComponant.BaseTest;

public class ResultDownload extends BaseTest{

	WebDriver driver;
	
	@BeforeMethod
	public void openApplication()
	{
		driver = initializeBrowserAndOpenApplication();
		openMPSCHomePage();
	}
	
	@Test
	public void downloadMeritResult()
	{
		HomePage homePage = new HomePage(driver);
		homePage.downloadMeritList();
		Assert.assertEquals(driver.getCurrentUrl(), "https://mpsc.gov.in/results_merit_lis/14");
		ResultMerit resultMerit = new ResultMerit(driver);
		resultMerit.downloadFirstMirit();


	}
}
