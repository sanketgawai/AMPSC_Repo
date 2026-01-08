package abstractComponant;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponant {
	
	 protected WebDriver driver;
	    protected WebDriverWait wait;
	    protected Actions act;

	    // 🔹 Constructor
	    public AbstractComponant(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        this.act = new Actions(driver);
	    }

	    // 🔹 Move mouse to element
	    public void moveToElement(WebElement element) {
	        act.moveToElement(element).perform();
	    }

	    // 🔹 Move and click
	    public void moveToElementAndClick(WebElement element) {
	        act.moveToElement(element).click().perform();
	    }
	    public void openWebElementInOtherTab(WebElement ele)
	    {
	    	act.moveToElement(ele).keyDown(Keys.CONTROL).click().keyUp(Keys.CONTROL).build().perform();
	    }

	    // 🔹 JavaScript click (fallback)
	    public void clickUsingJavaScript(WebElement element) {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].click();", element);
	    }

	    // 🔹 Scroll into view and send keys
	    public void scrollIntoViewAndSendKeys(WebElement element, String value) {
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        js.executeScript(
	            "arguments[0].scrollIntoView({block:'center'});",
	            element
	        );

	        element.click();
	        element.clear();
	        element.sendKeys(value);
	    }

	    // 🔹 Switch to newly opened window
	    public void switchToNewWindow(Set<String> oldWindows) {
	        wait.until(d -> d.getWindowHandles().size() > oldWindows.size());

	        Set<String> newWindows = new HashSet<>(driver.getWindowHandles());
	        newWindows.removeAll(oldWindows);

	        if (newWindows.isEmpty()) {
	            throw new RuntimeException("No new window found");
	        }

	        driver.switchTo().window(newWindows.iterator().next());
	    }
	    
	    public void waitForWebElementToVisible(WebElement ele)
	    {
	    	wait.until(ExpectedConditions.visibilityOf(ele));
	    }
	}
