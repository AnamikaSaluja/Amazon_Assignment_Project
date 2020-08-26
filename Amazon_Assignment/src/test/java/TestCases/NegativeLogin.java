package TestCases;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Base.BasePage;
import Pages.Login;

public class NegativeLogin extends BasePage {

	final static Logger log = Logger.getLogger(NegativeLogin.class);

	Login lg;

	@BeforeClass
	public void beforeClass() {
		lg = new Login(driver);
	}

	@Test(dataProvider = "Negative Login Data", dataProviderClass = dataProviders.TestDataProviders.class)
	public void testCase_NegativeLogin(String username, String password) {
		lg.launch(readingPropertiesFile.getProperty("url"));
		lg.clickSignInButton();
		lg.enterEmail(username);
		lg.clickContinue();
		lg.enterPass(password);
		lg.submitButton();
		test.log(Status.INFO, "Negative login-Login in with wrong pass");
		this.log.info("Login in with wrong pass");
		lg.wrongPassTitle();
	}
}
