package pomClasses;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponant.AbstractComponant;

public class HomePage extends AbstractComponant {

	WebDriver driver;
	public HomePage(WebDriver driver)	
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[text()='Online facilities']/parent::a")
	private WebElement onlineFacilities; 
	
	@FindBy(xpath = "//span[text()='Online Application System']/parent::a")
	private WebElement onlineApplicationSystem;

	@FindBy(xpath = "//span[text()='Candidate Information']/parent::a")
	private WebElement candidateInformation;

	@FindBy(xpath = "//span[text()='Results']/parent::a")
	private WebElement result;

	@FindBy(xpath = "//span[text()='Merit List']/parent::a")
	private WebElement resultMeritList;
	
	@FindBy(xpath = "//a[text()='New User Registration']")
	private WebElement newUserRegistration;
	
	public OnlineApplicationPage goToOnlineApplicationSystemPage()
	{
		Set<String> oldwindow = driver.getWindowHandles();		
		moveToElement(onlineFacilities);
		clickUsingJavaScript(onlineApplicationSystem);
		switchToNewWindow(oldwindow);
		waitForWebElementToVisible(newUserRegistration);
		newUserRegistration.click();
		return new OnlineApplicationPage(driver);
	}
	
	 public void downloadMeritList()
	{
		 Set<String> oldwindow = driver.getWindowHandles();
		 moveToElement(candidateInformation);
		 moveToElement(result);
		 openWebElementInOtherTab(resultMeritList);
		 switchToNewWindow(oldwindow);
    }
}
