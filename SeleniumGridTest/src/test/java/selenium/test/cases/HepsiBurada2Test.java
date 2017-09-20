package selenium.test.cases;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.test.base.GridParallelTestBase;

@RunWith(selenium.run.parallel.Parallelized.class)
public class HepsiBurada2Test extends GridParallelTestBase {

	public HepsiBurada2Test(String browserName, String browserVersion) {
		super(browserName, browserVersion);
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void girisYapControl() throws InterruptedException {



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
	
	@Test
	public void girisYapControl2() throws InterruptedException {


		long startTime = System.currentTimeMillis();
		driver.get("http://www.hepsiburada.com");
		WebElement loginElement = driver.findElement(By.id("myAccount"));
		
		loginElement.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement loginElement2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("usersProcess")));
		WebElement loginButton= loginElement2.findElement(By.id("login"));
		
		String targetUrl = "www.google.com.tr";
		
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
	
	@After
    public void tearDown() throws Exception {
        driver.quit();
        
    }
	

}
