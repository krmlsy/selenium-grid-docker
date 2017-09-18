package selenium.test.cases;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import selenium.test.base.GridParallelTestBase;

/**
 * KEREM ULUSOY
 */
@RunWith(selenium.run.parallel.Parallelized.class)
public class SeleniumTest extends GridParallelTestBase {
	
	  //Constructor
    public SeleniumTest(String browserName)  {
        super(browserName);
    }
	
	@Test
	public void titleControl() throws InterruptedException {

		System.out.println("Starting test " + new Object() {
		}.getClass().getEnclosingMethod().getName() + ", for browser:" +browserName);

		long startTime = System.currentTimeMillis();
		driver.get("http://www.google.com.tr");
		WebElement searchBox=driver.findElement(By.id("lst-ib"));
		searchBox.sendKeys("Araba");
		TimeUnit.SECONDS.sleep(1);
		driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
		TimeUnit.SECONDS.sleep(1);
		takeScreenShot();

		try {
			assertEquals("Araba - Google'da Ara", driver.getTitle());
			System.out.println("SUCCESS: Title matches!");
		} catch (Exception e) {
			System.err.println("ERROR: Title does not match!");
		}

		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Total Time(ms):" + totalTime);
		System.out.println("Ending test " + new Object() {
		}.getClass().getEnclosingMethod().getName()+ ", for browser:" +browserName);
	}

	
	@Test
	public void titleControl2() throws InterruptedException {

		System.out.println("Starting test " + new Object() {
		}.getClass().getEnclosingMethod().getName()+ ", for browser:" +browserName);

		long startTime = System.currentTimeMillis();
		driver.get("http://www.google.com.tr");
		WebElement searchBox=driver.findElement(By.id("lst-ib"));
		searchBox.sendKeys("Araba");
		TimeUnit.SECONDS.sleep(1);
		driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
		TimeUnit.SECONDS.sleep(1);
		takeScreenShot();

		try {
			assertEquals("Araba - Google'da Ara", driver.getTitle());
			System.out.println("SUCCESS: Title matches!");
		} catch (Exception e) {
			System.err.println("ERROR: Title does not match!");
		}

		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Total Time(ms):" + totalTime);
		System.out.println("Ending test " + new Object() {
		}.getClass().getEnclosingMethod().getName()+ ", for browser:" +browserName);
	}

	
	@Test
	public void titleControl3() throws InterruptedException {

		System.out.println("Starting test " + new Object() {
		}.getClass().getEnclosingMethod().getName()+ ", for browser:" +browserName);

		long startTime = System.currentTimeMillis();
		driver.get("http://www.google.com.tr");
		WebElement searchBox=driver.findElement(By.id("lst-ib"));
		searchBox.sendKeys("Araba");
		TimeUnit.SECONDS.sleep(1);
		driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
		TimeUnit.SECONDS.sleep(1);
		takeScreenShot();

		try {
			assertEquals("Araba2 - Google'da Ara", driver.getTitle());
			System.out.println("SUCCESS: Title matches!");
		} catch (Exception e) {
			System.err.println("ERROR: Title does not match!");
		}	

		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Total Time(ms):" + totalTime);
		System.out.println("Ending test " + new Object() {
		}.getClass().getEnclosingMethod().getName()+ ", for browser:" +browserName);
	}
	
	@After
    public void tearDown() throws Exception {

        driver.quit();
    }


}