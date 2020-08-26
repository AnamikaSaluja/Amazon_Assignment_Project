package Base;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import keywords.SeleniumKeywords;
import util.ExtentManager;
import util.PropertyFileReading;
import util.Utilities;

public class BasePage {

	public WebDriver driver;
	public static ExtentReports extentReport;
	public static ExtentTest test;
	public static Actions action = null;
	String bType = null;
	public Utilities util = new Utilities();;
	public static PropertyFileReading readingPropertiesFile = new PropertyFileReading();
	public static Properties testcaseProperties;
	private static String dir = System.getProperty("user.dir");

	final static Logger log = Logger.getLogger(BasePage.class);
	public static String reportFolder;

	public BasePage() {

	}

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@BeforeSuite
	public void beforeSuite() throws IOException {
		log.info("Into Before Suite - Hook");
		reportFolder = dir + "/reports/" + (Utilities.getCurrentDateTime().replaceAll("/", "-").replaceAll(":", "-"));
		extentReport = ExtentManager.generateReport(reportFolder);
		testcaseProperties = Utilities.readProperties(dir + "/src/test/resources/testCases.properties");

	}

	@BeforeTest(alwaysRun = true)
	@Parameters("browserName")
	public void openBrowser(String browserName) throws Exception {

		try {
			if (browserName == null)
				browserName = "chrome";
			log.info("Into before Test Hook ");
			if (browserName.equalsIgnoreCase("Mozilla")) {
				System.setProperty("webdriver.gecko.driver", dir + PropertyFileReading.getProperty("geckoDriverExe"));
				driver = new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver", dir + PropertyFileReading.getProperty("chomeDriverExe"));
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("IE")) {
				driver = new InternetExplorerDriver();
			}
			driver.manage().timeouts().implicitlyWait(PropertyFileReading.getImplicitlyWait(), TimeUnit.SECONDS);
			driver.manage().window().maximize();
			log.info(browserName + " Browser launched successfully..");
		} catch (Exception e) {
			log.info("Error while launching Browser...");
			log.error(e.getStackTrace());
		}
	}

	@BeforeMethod
	public void init(Method method) {
		test = extentReport.createTest(method.getName());
		if (testcaseProperties.getProperty(method.getName()).equalsIgnoreCase("no")) {
			throw new SkipException("Testcase marked as 'No' in property File");
		}
		log.info("Starting the testcase " + method.getName());
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
			test.fail(result.getThrowable());
			log.error(result.getName() + " FAILED ");
			StringWriter sw = new StringWriter();
			result.getThrowable().printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());
			sw = null;
			try {
				String path = new SeleniumKeywords().getScreenshot(result.getName() + "_FailedScreenShot", driver);
				test.addScreenCaptureFromPath(path);
			} catch (IOException e) {
				log.info("Failed to upload Screenshot in extent report");
			}
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
			log.info(result.getName() + " PASSED ");
		} else {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
			log.error(result.getName() + " SKIPPED ");

		}
	}

	@AfterTest
	public void quitDriver() {
		log.info("Closing brower...");
		driver.quit();
	}

	@AfterSuite
	public void tearDown() {
		try {
			ExtentManager.printReport();
		} catch (Exception e) {
			log.error("Error while printing report at desired location.");
			throw e;
		}
	}

}
