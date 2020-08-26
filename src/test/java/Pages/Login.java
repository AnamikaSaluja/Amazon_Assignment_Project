package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import Base.BasePage;

public class Login extends BasePage {
	SoftAssert softassert = new SoftAssert();

	public Login(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@id='nav-signin-tooltip']//span[@class='nav-action-inner'][contains(text(),'Sign in')]")
	public WebElement signInButton;

	@FindBy(xpath = "//input[@id='ap_email']")
	public WebElement enterEmail;

	@FindBy(xpath = "//input[@id='continue']")
	public WebElement clickContinue;

	@FindBy(xpath = "//input[@id='ap_password']")
	public WebElement enterPassword;

	@FindBy(xpath = "//input[@id='signInSubmit']")
	public WebElement clickSubmitButton;

	@FindBy(xpath = "//h4[@class='a-alert-heading'] ")
	public WebElement alertGettext;

	public void launch(String url) {
		driver.get(url);
	}

	public void clickSignInButton() {
		signInButton.click();

	}

	public void enterEmail(String userName) {
		enterEmail.sendKeys(userName);
	}

	public void clickContinue() {
		clickContinue.click();
	}

	public void enterPass(String pwd) {
		enterPassword.sendKeys(pwd);
	}

	public void submitButton() {
		clickSubmitButton.click();
	}

	public void wrongPassTitle() {
		softassert.assertEquals(alertGettext.getText(), "Important Message!");
		softassert.assertAll();

	}
}