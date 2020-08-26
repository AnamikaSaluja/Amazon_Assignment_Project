package Pages;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

import Base.BasePage;

public class TextBook extends BasePage {

	public TextBook(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = Constants.FOUNDBOOKTEXT)
	public WebElement foundBooktext1;

	@FindBy(xpath = "//div[@class='sg-col-inner']//div[1]//div[1]//span[1]//div[1]//div[1]//div[2]//div[2]//div[1]//div[2]//div[1]//div[1]//div[2]//div[1]//div[1]//div[1]//a[1]")
	public WebElement foundBooktext;

	@FindBy(xpath = "//*[@id='search']/div[1]/div[2]/div/span[4]/div[2]/div[1]/div/span/div/div/div[2]/div[2]/div/div")
	public WebElement textBook;

	public BookPage foundBook() {
		// Waiting 30 seconds for an element to be present on the page, checking
		// for its presence once every 5 seconds.
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement linkElement = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));

				if (linkElement.isDisplayed()) {

				}
				return linkElement;
			}

		});

		element.click();

		return new BookPage(driver);
	}
}
