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

/**
 * Created by onurb on 26-Nov-16.
 */
public class GridParallelTestBase {
    //Declare DesiredCapabilities configuration variables
    protected String browserName;
    protected Platform platformName;
    protected WebDriver driver;

    //Hold all Configuration values in a LinkedList
    //Extra Usage Information: http://www.swtestacademy.com/junit-parametrized-tests/
    @Parameterized.Parameters
    public static LinkedList<String[]> getEnvironments() throws Exception {
        LinkedList<String[]> env = new LinkedList<String[]>();
        env.add(new String[]{"firefox"});
        env.add(new String[]{"chrome"});
//        env.add(new String[]{"internet explorer"});
        //add more browsers here
        return env;
    }

    //Constructor
    public GridParallelTestBase(String browserName) {
        this.browserName = browserName;
    }

    public void setPlatform (Platform platform) {
        platformName = platform;
    }

    @Before
    public void setUp() throws Exception {
        //Set DesiredCapabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //Firefox Profile Settings
        if (browserName.equals("firefox")) {
        	capabilities.setCapability(CapabilityType.PLATFORM, "LINUX");
        	capabilities.setCapability(CapabilityType.BROWSER_NAME, "firefox");
        	capabilities.setCapability(CapabilityType.VERSION, "52.0.2");
        }
        //Chrome Profile Settings
        if (browserName.equals("chrome")) {
         	capabilities.setCapability(CapabilityType.PLATFORM, "LINUX");
        	capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        	capabilities.setCapability(CapabilityType.VERSION, "60.0.3112.113");
        }
        //IE Profile Settings
        if (browserName.equals("internet explorer")) {
         	capabilities.setCapability(CapabilityType.PLATFORM, "WINDOWS");
        	capabilities.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
        	capabilities.setCapability(CapabilityType.VERSION, "11.0.9600.17107");
        	capabilities.setCapability("ie.ensureCleanSession", true);
        	capabilities.setCapability("ignoreProtectedModeSettings", true);
        }

        driver = new RemoteWebDriver(new URL("http://192.168.8.102:4444/wd/hub/"), capabilities);
    }

    //TakeScreenShot
    public void takeScreenShot () {
		String path;
		try {
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			File source = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			path = "./target/screenshots/" + source.getName();
			FileUtils.copyFile(source, new File(path));
		} catch (IOException e) {
			path = "Failed to capture screenshot: " + e.getMessage();
		}
    }

}