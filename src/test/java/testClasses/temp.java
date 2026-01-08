package testClasses;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import abstractComponant.AbstractComponant;
import abstractComponant.CalenderCustomAction;

import org.openqa.selenium.interactions.Actions;

public class temp extends AbstractComponant {

	

	public temp(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		driver.get("https://mpsc.gov.in/home");
		
		//AbstractComponant abstractComponant = new AbstractComponant(driver);
		WebElement onlineFacilities = driver.findElement(By.xpath("//span[text()='Online facilities']/parent::a"));
		
		Actions act = new Actions(driver);
		act.moveToElement(onlineFacilities).perform();		
		
		WebElement onlineApplicationSystem = driver.findElement(By.xpath("//span[text()='Online Application System']/parent::a"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		Set<String> oldWindows = new HashSet<>(driver.getWindowHandles());
		js.executeScript("arguments[0].click();", onlineApplicationSystem);
		
		ArrayList<String> addr = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(addr.get(1));
		
		
		wait.until(ExpectedConditions.urlContains("https://mpsconline.gov.in/candidate"));
		
		WebElement newUserRegistration = driver.findElement(By.xpath("//a[text()='New User Registration']"));
		newUserRegistration.click();
		
		
		WebElement emailId = driver.findElement(By.xpath("//input[@id='registrationFormControlInput1']"));
		WebElement mobileNumber = driver.findElement(By.xpath("//label[text()='Mobile Number']/following-sibling::div/input"));
		WebElement otpReceivedOnEmail = driver.findElement(By.xpath("//label[text()='Enter OTP Received on Email']/following-sibling::div/input"));
		WebElement otpReceivedOnMobile = driver.findElement(By.xpath("//label[text()='Enter OTP Received on Mobile']/following-sibling::div/input"));
		WebElement newPassword = driver.findElement(By.xpath("//input[@id='registrationFormControlInput5']"));
		WebElement confirmPassword = driver.findElement(By.xpath("//input[@id='registrationFormControlInput6']"));
		WebElement dateOfBirth = driver.findElement(By.xpath("//label[text()='Date of Birth']/parent::div/parent::div/following-sibling::div/div/div/div/input"));
		
//		JavascriptExecutor js1 = (JavascriptExecutor) driver;
//		js1.executeScript("arguments[0].scrollIntoView({block:'center'});", dateOfBirth);
//		js1.executeScript("arguments[0].click();", dateOfBirth);
//		CalenderCustomAction.selectDateWithNext(driver);
		WebElement nickName = driver.findElement(By.xpath("//label[contains(text(),'What was your')]/parent::div/following-sibling::div/input"));
		WebElement friendName = driver.findElement(By.xpath("//label[contains(text(),'What is the name of your favorit')]/parent::div/following-sibling::div/input"));
		WebElement bloodType = driver.findElement(By.xpath("//label[contains(text(),'What is your Blood')]/parent::div/following-sibling::div/input"));
		WebElement yearHscCompeteDate = driver.findElement(By.xpath("//label[contains(text(),'Which year did you complete')]/parent::div/following-sibling::div/input"));
		WebElement schoolTeacherName = driver.findElement(By.xpath("//label[contains(text(),'What is the surname of your favorite hig')]/parent::div/following-sibling::div/input"));
		WebElement sportPersonName = driver.findElement(By.xpath("//label[contains(text(),'Who is your favourite sport')]/parent::div/following-sibling::div/input"));		
		
		WebElement registerButton = driver.findElement(By.xpath("//button[text()='Register']"));
		
		emailId.sendKeys("kaido@gmail.com");
		mobileNumber.sendKeys("4568972584");
		otpReceivedOnEmail.sendKeys("1234");
		otpReceivedOnMobile.sendKeys("1234");
		
//		js.executeScript("window.scrollBy(0, 500);");		
//		Thread.sleep(5000);
		
//		wait.until(ExpectedConditions.visibilityOf(newPassword));
		//scrollIntoViewAndSendKeys(driver,newPassword,"kaido@123");
		
//		newPassword.sendKeys("kaido@123");
//		confirmPassword.sendKeys("kaido@123");
		Assert.assertEquals(newPassword.isEnabled(), false);
		Assert.assertEquals(confirmPassword.isEnabled(), false);
		nickName.sendKeys("isuzuku");
		friendName.sendKeys("deku");
		bloodType.sendKeys("O");
		yearHscCompeteDate.sendKeys("2020");
		schoolTeacherName.sendKeys("jiraya sense");
		sportPersonName.sendKeys("mighty guy");

		Assert.assertEquals(registerButton.isEnabled(), false); 
		
		//--------------------------------------------
		driver.close();
		driver.switchTo().window(addr.get(0));
		act.moveToElement(onlineFacilities).perform();	
		
		WebElement candidateInformation = driver.findElement(By.xpath("//span[text()='Candidate Information']/parent::a"));
		WebElement result = driver.findElement(By.xpath("//span[text()='Results']/parent::a"));
		WebElement resultMeritList = driver.findElement(By.xpath("//span[text()='Merit List']/parent::a"));
		act.moveToElement(candidateInformation).perform();
		act.moveToElement(result).perform();
		act.moveToElement(resultMeritList).keyDown(Keys.CONTROL).click().keyUp(Keys.CONTROL).build().perform();
		//driver.switchTo().window(addr.get(1));
		
		
	}
}
