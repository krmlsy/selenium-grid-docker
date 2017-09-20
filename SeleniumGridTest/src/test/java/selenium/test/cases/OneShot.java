package selenium.test.cases;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * KEREM ULUSOY
 */

public class OneShot  {
	
	private static WebDriver driver;
	WebElement element;

	@Before
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\dev\\SELENIUM\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		
	}

	
	@Test
	public void girisYapControl2() throws InterruptedException {


		long startTime = System.currentTimeMillis();
		driver.get("http://www.hepsiburada.com");
		WebElement loginElement = driver.findElement(By.id("myAccount"));
		
		loginElement.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement loginElement2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("usersProcess")));
		WebElement loginButton= loginElement2.findElement(By.id("login"));
		
		String targetUrl = loginButton.getAttribute("href");
		
		loginButton.click();
		String siteUrl = driver.getCurrentUrl();

		takeScreenShot(new Object() {
		}.getClass().getEnclosingMethod().getName());

		try {
			assertEquals(siteUrl, targetUrl);
			System.out.println("SUCCESS: Title matches!");
		} catch (Exception e) {
			System.err.println("ERROR: Title does not match!");
		}

		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Total Time(ms):" + totalTime);
		System.out.println("Ending test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
	}
	
//	@Test
//	public void panelControl() throws InterruptedException {
//
//		long startTime = System.currentTimeMillis();
//		driver.get("http://www.hepsiburada.com");
//		WebElement searchBox = driver.findElement(By.id("productSearch"));
//
//		searchBox.sendKeys("telefon");
//		TimeUnit.SECONDS.sleep(2);
//
//		List<WebElement> panelDiv = driver.findElements(By.className("rfk_msg_results"));
//
//		String innerHTML = panelDiv.get(0).getAttribute("innerHTML");
//		takeScreenShot(new Object() {
//		}.getClass().getEnclosingMethod().getName());
//
//		try {
//			assertEquals("\"telefon\" için en iyi sonuçlar", innerHTML);
//			System.out.println("SUCCESS: Title matches!");
//		} catch (Exception e) {
//			System.err.println("ERROR: Title does not match!");
//		}
//
//		long endTime = System.currentTimeMillis();
//		long totalTime = endTime - startTime;
//		System.out.println("Total Time(ms):" + totalTime);
//		System.out.println("Ending test " + new Object() {
//		}.getClass().getEnclosingMethod().getName());
//	}


	@After
    public void tearDown() throws Exception {
        driver.quit();
        
    }

	// TakeScreenShot
	public void takeScreenShot(String testName) {
		String path;
		try {
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			File source = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			path = "./target/screenshots/" + testName+".png";
			FileUtils.copyFile(source, new File(path));
		} catch (IOException e) {
			path = "Failed to capture screenshot: " + e.getMessage();
		}
	}

}