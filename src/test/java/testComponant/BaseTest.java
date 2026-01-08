package testComponant;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	//2nd change from local
	//3rd change from local
	//4th change from masterbranch
	//5th change from local to sanketBranch
	public WebDriver driver;
	public WebDriver initializeBrowserAndOpenApplication()
	{
		String browserName = "chrome";
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.manage().window().maximize();	
		
		return driver;

	}
	
	
	public void openMPSCHomePage()
	{
		driver.get("https://mpsc.gov.in/home");
	}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
//		TakesScreenshot t = (TakesScreenshot)driver; 
//		File src = t.getScreenshotAs(OutputType.FILE);
//		File dest = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
//		//FileHandler.copy(src, dest);
//		FileUtils.copyFile(src, dest);
//		return System.getProperty("user.dir"+"//reports//"+testCaseName+".png");

	    if (driver == null) {
	        return null;
	    }

	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File src = ts.getScreenshotAs(OutputType.FILE);

	    String path = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	    File dest = new File(path);

	    FileUtils.copyFile(src, dest);
	    return path;
	
	}
	
}
