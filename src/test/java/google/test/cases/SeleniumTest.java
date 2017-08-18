package google.test.cases;

 
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
 
public class SeleniumTest {
//	 	private static FirefoxDriver driver;
	 	private static WebDriver driver;
	 	WebElement element;
 
	 @BeforeClass
     public static void openBrowser() throws MalformedURLException{
		 DesiredCapabilities dcap=DesiredCapabilities.firefox();
		 driver=new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub/"),dcap);
		 
//		 System.setProperty("webdriver.gecko.driver", "C:/Users/User/Desktop/SELENIUM/geckodriver.exe" );
//		 driver = new FirefoxDriver();
		 driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		} 
 
	 @Test
     public void titleControl() throws InterruptedException{
 
		 System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());

	     long startTime = System.currentTimeMillis();
	     driver.get("http://www.google.com.tr");	
	     driver.findElement(By.id("lst-ib")).sendKeys("Araba");
	     TimeUnit.SECONDS.sleep(1);
	     driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
	     TimeUnit.SECONDS.sleep(1);
	     captureScreen();
	     
	     try {
	    	 assertEquals("Araba - Google'da Ara", driver.getTitle());
	    	 System.out.println("SUCCESS: Title matches!");
		} catch (Exception e) {
			System.err.println("ERROR: Title does not match!");
		}
	     



	     long endTime   = System.currentTimeMillis();
	     long totalTime = endTime - startTime; 
	     System.out.println("Total Time(ms):"+totalTime);
	     System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
     }
 
 
	 @AfterClass
	 public static void closeBrowser(){
		 driver.quit();
	 }
	 
	 public String captureScreen() {
		    String path;
		    try {
		        WebDriver augmentedDriver = new Augmenter().augment(driver);
		        File source = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
		        path = "./target/screenshots/" + source.getName();
		        FileUtils.copyFile(source, new File(path)); 
		    }
		    catch(IOException e) {
		        path = "Failed to capture screenshot: " + e.getMessage();
		    }
		    return path;
		}
}