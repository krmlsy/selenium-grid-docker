package selenium.test.base;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.runners.Parameterized;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import selenium.nodes.Nodes;
import selenium.data.management.*;

/**
 * KEREM ULUSOY
 */
public class GridParallelTestBase {
	// Declare DesiredCapabilities configuration variables

	protected String browserName;
	protected String browserVersion;
	protected Platform platformName;
	protected WebDriver driver;
	protected DataManager dm=new DataManager();
    
	// Hold all Configuration values in a LinkedList
	// Extra Usage Information:
	// http://www.swtestacademy.com/junit-parametrized-tests/
	@Parameterized.Parameters
	public static LinkedList<String[]> getEnvironments() throws Exception {
		LinkedList<String[]> env = new LinkedList<String[]>();
		env.add(new String[] { "firefox", Nodes.Firefox.VERSION_52.getValue() });
//		env.add(new String[] { "firefox", Nodes.Firefox.VERSION_55.getValue() });
		env.add(new String[] { "chrome",  Nodes.Chrome.VERSION_60.getValue() });
//		env.add(new String[] { "chrome",  Nodes.Chrome.VERSION_61.getValue() });
		env.add(new String[]{"internet explorer" , Nodes.InternetExplorer.VERSION_11.getValue()});
		// add more browsers here
		return env;
	}

	// Constructor
	public GridParallelTestBase(String browserName, String browserVersion) {
		this.browserName = browserName;
		this.browserVersion = browserVersion;
	}
	
	public void setPlatform(Platform platform) {
		platformName = platform;
	}

	@Before
	public void setUp() throws Exception {
	
		// Set DesiredCapabilities
		DesiredCapabilities capabilities = new DesiredCapabilities();
		// Firefox Profile Settings
		String url="";
		
		if (browserName.equals("firefox")) {
			capabilities.setCapability(CapabilityType.PLATFORM, Nodes.Firefox.PLATFORM.getValue());
			capabilities.setCapability(CapabilityType.BROWSER_NAME, Nodes.Firefox.BROWSER_NAME.getValue());
			capabilities.setCapability(CapabilityType.BROWSER_VERSION, browserVersion);
			url="217.182.91.245";
		}
		// Chrome Profile Settings
		if (browserName.equals("chrome")) {
			capabilities.setCapability(CapabilityType.PLATFORM, Nodes.Chrome.PLATFORM.getValue());
			capabilities.setCapability(CapabilityType.BROWSER_NAME, Nodes.Chrome.BROWSER_NAME.getValue());
			capabilities.setCapability(CapabilityType.BROWSER_VERSION, browserVersion);
			url="217.182.91.245";
		}
		// IE Profile Settings
		if (browserName.equals("internet explorer")) {
			capabilities.setCapability(CapabilityType.PLATFORM, Nodes.InternetExplorer.PLATFORM.getValue());
			capabilities.setCapability(CapabilityType.BROWSER_NAME, Nodes.InternetExplorer.BROWSER_NAME.getValue());
			capabilities.setCapability(CapabilityType.BROWSER_VERSION, browserVersion);
			capabilities.setCapability("ie.ensureCleanSession", true);
			capabilities.setCapability("ignoreProtectedModeSettings", true);
			url="192.168.8.103";
		}

		driver = new RemoteWebDriver(new URL("http://"+url+":4444/wd/hub/"), capabilities);
		// maximize screen
		driver.manage().window().maximize();

	}

	// TakeScreenShot
	public void takeScreenShot(String testName) {
		String path;
		try {
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			File source = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			path = "./target/screenshots/" + testName+"("+browserName+"-"+browserVersion+").png";
			FileUtils.copyFile(source, new File(path));
		} catch (IOException e) {
			path = "Failed to capture screenshot: " + e.getMessage();
		}
	}

}