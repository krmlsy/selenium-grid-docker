package selenium.test.cases;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.test.base.GridParallelTestBase;

/**
 * KEREM ULUSOY
 */
@RunWith(selenium.run.parallel.Parallelized.class)
public class HepsiBuradaTest extends GridParallelTestBase {
	
	
	
	  //Constructor
    public HepsiBuradaTest(String browserName , String browserVersion)  {
        super(browserName,browserVersion);
    }
	
	@Test
	public void panelControl() throws InterruptedException {

		System.out.println("Starting test " + new Object() {
		}.getClass().getEnclosingMethod().getName() + ", for browser:" +browserName+"V"+browserVersion);


		ArrayList<String> resultForUrl=dm.getScenarioData(this.getClass().getSimpleName(), new Object() {}.getClass().getEnclosingMethod().getName(), "url");
		String url=resultForUrl.get(1);
		
		ArrayList<String> resultForSearchKey=dm.getScenarioData(this.getClass().getSimpleName(), new Object() {}.getClass().getEnclosingMethod().getName(), "searchKey");
		String searchKey=resultForSearchKey.get(1);
		
		long startTime = System.currentTimeMillis();
		driver.get(url);
		WebElement searchBox=driver.findElement(By.id("productSearch"));
		
		searchBox.sendKeys(searchKey);
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String innerHTML = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("rfk_msg_results"))).getText();
		takeScreenShot(new Object() {}.getClass().getEnclosingMethod().getName());

		try {
			assertEquals("\"telefon\" için en iyi sonuçlar", innerHTML);
			System.out.println("SUCCESS: Title matches!");
		} catch (Exception e) {
			System.err.println("ERROR: Title does not match!");
		}

		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Total Time(ms):" + totalTime);
		System.out.println("Ending test " + new Object() {
		}.getClass().getEnclosingMethod().getName()+ ", for browser:" +browserName+"V"+browserVersion);
		
		dm.unlockScenarioDataById(Integer.parseInt(resultForUrl.get(0)));
		dm.unlockScenarioDataById(Integer.parseInt(resultForSearchKey.get(0)));
	}


	@After
    public void tearDown() throws Exception {
        driver.quit();
        
    }


}