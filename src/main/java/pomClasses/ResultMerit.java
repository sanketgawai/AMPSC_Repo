package pomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponant.AbstractComponant;

public class ResultMerit extends AbstractComponant {

	WebDriver driver;
	public ResultMerit(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(xpath="(//span[text()='Sr. No.']/ancestor::thead/following-sibling::tbody/tr/td/following-sibling::td[4])[1]")
	private WebElement firstMiritListFile;
		
	public void downloadFirstMirit()
	{
		waitForWebElementToVisible(firstMiritListFile);
		firstMiritListFile.click();
		
	}
	
}
