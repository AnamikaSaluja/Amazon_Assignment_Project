package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import Base.BasePage;

public class BookPage extends BasePage {
	static SoftAssert softassertion = new SoftAssert();

	public BookPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = Constants.PRODUCT_TITLE)
	public WebElement ProductTitle;

	@FindBy(xpath = Constants.ADD_TO_CART_BUTTON)
	public WebElement AddToCartButton;

	public void VerifyTitle() {
		Assert.assertEquals(ProductTitle.getText().trim(), "Learning Selenium Testing Tools with Python");
	}

	public AddedToCartPage addToCartButon() {
		AddToCartButton.click();
		return new AddedToCartPage(driver);
	}
}
