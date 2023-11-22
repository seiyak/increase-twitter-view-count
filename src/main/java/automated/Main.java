package automated;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Main {

	private static String WEB_DRIVER_PATH = "DRIVER_PATH";
	private static String EMAIL_ADDRESS = "YOUR_EMAIL";
	private static String USER_NAME = "YOUER_USERNAME";
	private static String PASSWORD = "YOUR_PASSWORD";
	
	public static void main(String[] args) {
		System.setProperty("gecodriver", WEB_DRIVER_PATH);
		
		FirefoxOptions options = new FirefoxOptions();
		//options.setHeadless(true);
		WebDriver driver = new FirefoxDriver(options);
		driver.get("https://twitter.com/");
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		//// Clicks "Sign in" button.
		driver.findElement(By.cssSelector("a[href='/login'")).click();
		
		//// Types email address.
		driver.findElement(By.cssSelector("input[autocomplete='username'")).sendKeys(EMAIL_ADDRESS);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		List<WebElement> el = driver.findElements(By.cssSelector("div[role='button']"));
		el.get(3).click();
		
		
		try {
			//// Type username when they require due do some unusual activites.
			driver.findElement(By.cssSelector("input[name='text'")).sendKeys(USER_NAME);
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			List<WebElement> el2 = driver.findElements(By.cssSelector("div[role='button']"));
			el2.get(1).click();
		}catch(Exception ex) {
			System.out.println("No need to type username this time");
		}

		//// Types password.
		driver.findElement(By.cssSelector("input[name='password'")).sendKeys(PASSWORD);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		List<WebElement> el3 = driver.findElements(By.cssSelector("div[role='button']"));
		el3.get(2).click();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		WebElement elem;
		JavascriptExecutor js;
		for(int i = 0; i < 1000;i++) {
			System.out.println("i: " + i);
			elem = driver.findElement(By.cssSelector("a[href='/pomjakim']"));
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();",elem);
			elem.click();
			
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			driver.navigate().refresh();

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
