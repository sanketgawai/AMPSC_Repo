package pomClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OnlineApplicationPage {

	WebDriver driver;
	
	public OnlineApplicationPage(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	
		@FindBy(xpath="//input[@id='registrationFormControlInput1']")
		private WebElement emailId;

	    // 🔹 Mobile Number
	    @FindBy(xpath = "//label[text()='Mobile Number']/following-sibling::div/input")
	    private WebElement mobileNumber;

	    // 🔹 OTP Email
	    @FindBy(xpath = "//label[text()='Enter OTP Received on Email']/following-sibling::div/input")
	    private WebElement otpReceivedOnEmail;

	    // 🔹 OTP Mobile
	    @FindBy(xpath = "//label[text()='Enter OTP Received on Mobile']/following-sibling::div/input")
	    private WebElement otpReceivedOnMobile;

	    // 🔹 Password
	    @FindBy(id = "registrationFormControlInput5")
	    private WebElement newPassword;

	    // 🔹 Confirm Password
	    @FindBy(id = "registrationFormControlInput6")
	    private WebElement confirmPassword;

	    // 🔹 Date of Birth
	    @FindBy(xpath = "//label[text()='Date of Birth']/parent::div/parent::div/following-sibling::div//input")
	    private WebElement dateOfBirth;

	    // 🔹 Security Questions
	    @FindBy(xpath = "//label[contains(text(),'What was your')]/parent::div/following-sibling::div/input")
	    private WebElement nickName;

	    @FindBy(xpath = "//label[contains(text(),'What is the name of your favorit')]/parent::div/following-sibling::div/input")
	    private WebElement friendName;

	    @FindBy(xpath = "//label[contains(text(),'What is your Blood')]/parent::div/following-sibling::div/input")
	    private WebElement bloodType;

	    @FindBy(xpath = "//label[contains(text(),'Which year did you complete')]/parent::div/following-sibling::div/input")
	    private WebElement yearHscCompleteDate;

	    @FindBy(xpath = "//label[contains(text(),'What is the surname of your favorite hig')]/parent::div/following-sibling::div/input")
	    private WebElement schoolTeacherName;

	    @FindBy(xpath = "//label[contains(text(),'Who is your favourite sport')]/parent::div/following-sibling::div/input")
	    private WebElement sportPersonName;

	    // 🔹 Register Button
	    @FindBy(xpath = "//button[text()='Register']")
	    private WebElement registerButton;

	    public void registration(String eml,String mob,String otpRecEmail,String otpRecMob,String nikName, String frenName,String bloodTyp,String hsc,String tcherName,String sportPer)
	    {
	    	emailId.sendKeys(eml);
	    	mobileNumber.sendKeys(mob);
	    	otpReceivedOnEmail.sendKeys(otpRecEmail);
	    	otpReceivedOnMobile.sendKeys(otpRecMob);
	    	nickName.sendKeys(nikName);
	    	friendName.sendKeys(frenName);
	    	bloodType.sendKeys(bloodTyp);
	    	yearHscCompleteDate.sendKeys(hsc);
	    	schoolTeacherName.sendKeys(tcherName);
	    	sportPersonName.sendKeys(sportPer);
	    }
	   
	    public boolean checkRegistorButtonDisable()
	    {
	    	return registerButton.isDisplayed();
	    }
	    
	   
}
