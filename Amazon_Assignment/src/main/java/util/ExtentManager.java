package util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	private static ExtentReports extent;
	private static ExtentHtmlReporter htmlReporter;
	private static Properties prop;
	private static String dir = System.getProperty("user.dir");
	private static String extentPropertyPath = dir + "\\src\\test\\resources\\extentReport.properties";
	final static Logger log = Logger.getLogger(ExtentManager.class);

	public static ExtentReports generateReport(String path) throws IOException {
		new File(path).mkdir();
		log.info("Into extent reporter ----");
		if (extent == null) {
			prop = Utilities.readProperties(extentPropertyPath);
			htmlReporter = new ExtentHtmlReporter(path + prop.getProperty("htmlReportName"));
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			htmlReporter.config().setChartVisibilityOnOpen(true);
			htmlReporter.config().setDocumentTitle((String) prop.getProperty("htmlReportTitle"));
			htmlReporter.config().setReportName((String) prop.getProperty("htmlReportTitle"));
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTheme(Theme.STANDARD);
			htmlReporter.config().setTimeStampFormat((String) prop.getProperty("TimeStampFormat"));
			log.info("HTML report created : " + prop.getProperty("htmlReportTitle"));
		}
		return extent;
	}

	public static void printReport() {
		extent.flush();
		log.info("HTML report saved at " + System.getProperty("user.dir") + "/reports");
	}

}
