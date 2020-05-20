package baseClass;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseClass {

	protected WebDriver driver;

	@Parameters({ "browser" })
	@BeforeMethod
	public void openBrowser(String browser) throws Exception {
		String projectPath = System.getProperty("user.dir");
		String driverPath = projectPath + "\\browserDrivers";

		if (browser.equalsIgnoreCase("chrome")) {
			// Open Chrome Browser
			System.setProperty("webdriver.chrome.driver", driverPath + "\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("firefox")) {
			// Open Firefox
			System.setProperty("webdriver.gecko.driver", driverPath + "\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("ie")) {
			// Open IE
			System.setProperty("webdriver.ie.driver", driverPath + "\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} else {
			throw new Exception("Browser is not define.");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.get("https://app.fliplearn.com/home/wrv1_home/");
	}

	@AfterMethod
	public void quitBrowser(ITestResult result) throws Exception {

		if (!result.isSuccess()) {
			// Take Screeshot
			File srcScreeshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String screenshotName = result.getTestClass().getName() + "_" + result.getMethod().getMethodName();

			String pattern = "dd-MM-yyyy_hh-mm-ss";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String dateTime = simpleDateFormat.format(new Date());

			String destinationPath = System.getProperty("user.dir") + "\\Screenshot\\" + screenshotName + "_" + dateTime + ".jpeg";

			File destScrrenshot = new File(destinationPath);
			FileUtils.moveFile(srcScreeshot, destScrrenshot);
		}
		driver.quit();
	}
}
