package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Base.BasePage;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = Constants.DROPDOWN)
	public WebElement dropDown;

	@FindBy(xpath = Constants.SEARCH_BOOK_FROM_DROPDOWN)
	public WebElement searchBookFromDropdown;

	@FindBy(xpath = Constants.FOUND_BOOK_TEXT)
	public WebElement foundBooktext;

	@FindBy(xpath = Constants.SEARCH_BUTTON)
	public WebElement SearchButton;

	public void launch(String url) {
		driver.get(url);

	}

	public void clickDropDown() {

		dropDown.click();

	}

	public void searchBook() {

		searchBookFromDropdown.click();

	}

	public void EnterBookType(String BookText) {

		foundBooktext.sendKeys(BookText);

	}

	public void searchButtonClick() {
		SearchButton.click();

	}

}
