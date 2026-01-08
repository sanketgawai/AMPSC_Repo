package abstractComponant;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalenderCustomAction {

	WebDriver driver;
	
	public CalenderCustomAction(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public static void selectDate(WebDriver driver, String month, String year, String day) {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    // Open month dropdown
	    WebElement monthDropdown = wait.until(
	        ExpectedConditions.elementToBeClickable(
	            By.cssSelector(".react-datepicker__month-read-view")
	        )
	    );
	    monthDropdown.click();

	    // Select month
	    wait.until(
	        ExpectedConditions.elementToBeClickable(
	            By.xpath("//div[contains(@class,'react-datepicker__month-option') and text()='" + month + "']")
	        )
	    ).click();

	    // Open year dropdown
	    WebElement yearDropdown = wait.until(
	        ExpectedConditions.elementToBeClickable(
	            By.cssSelector(".react-datepicker__year-read-view")
	        )
	    );
	    yearDropdown.click();

	    // Select year
	    wait.until(
	        ExpectedConditions.elementToBeClickable(
	            By.xpath("//div[contains(@class,'react-datepicker__year-option') and text()='" + year + "']")
	        )
	    ).click();

	    // Select day (ignore disabled & outside month)
	    wait.until(
	        ExpectedConditions.elementToBeClickable(
	            By.xpath(
	                "//div[contains(@class,'react-datepicker__day') and " +
	                "not(contains(@class,'react-datepicker__day--disabled')) and " +
	                "not(contains(@class,'react-datepicker__day--outside-month')) and " +
	                "text()='" + day + "']"
	            )
	        )
	    ).click();
	}
	
	public static void selectDateWithNext(WebDriver driver) throws InterruptedException
	{
		//WebElement priviousButton = driver.findElement(By.xpath("//span[text()='Previous Month']/parent::button"));
		//WebElement monthYearText = driver.findElement(By.xpath("//div[text()='August 1995']"));
		
		String expectedMonthYear = "August 2025";

		WebElement previousButton = driver.findElement(
		        By.xpath("//span[text()='Previous Month']/parent::button")
		);

		// Loop until the desired month-year appears
		while (true) {

		    WebElement monthYearText = driver.findElement(
		            By.xpath("//div[contains(@class,'month') or text()]")
		    );

		    String currentMonthYear = monthYearText.getText();

		    if (currentMonthYear.equals(expectedMonthYear)) {
		        break; // Stop clicking when target is reached
		    }

		    previousButton.click();

		    // Optional: wait to allow UI to update
		    Thread.sleep(500);
		}
		
	}
	
}
