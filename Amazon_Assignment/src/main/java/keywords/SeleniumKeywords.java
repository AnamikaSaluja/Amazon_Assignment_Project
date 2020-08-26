package keywords;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumKeywords {

	private static String dir = System.getProperty("user.dir");
	final static Logger log = Logger.getLogger(SeleniumKeywords.class);

	public String getScreenshot(String screenshotName, WebDriver driver) {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		String destination = null;
		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			destination = dir + "/reports/" + screenshotName + dateName + ".png";
			FileUtils.copyFile(src, new File(destination));
			return destination;
		} catch (IOException e) {
			log.info("Error in taking Screen shots");
		}
		return destination;

	}

	private static WebDriverWait webDriverWait = null;

	public WebDriverWait getWebDriverWait(int waitTime, WebDriver driver) {
		webDriverWait = new WebDriverWait(driver, waitTime);
		return webDriverWait;
	}

}
