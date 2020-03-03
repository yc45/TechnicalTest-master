package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;

        if (!driver.getTitle().contains("Order takeaway online from 30,000+ food delivery restaurants")) {
            System.out.print("home title is " + driver.getTitle());
            throw new IllegalStateException("This is not the home page");
        }
    }

    @FindBy(css = "input[aria-label='Enter your postcode']")
    private WebElement postcodeTextbox;

    @FindBy(css = "button[data-test-id='find-restaurants-button']")
    private WebElement searchButton;

    public HomePage setPostcode(String postcode) {
        postcodeTextbox.sendKeys(postcode);

        return this;
    }

    public AreaPage clickSearch() {
        searchButton.click();

        return new AreaPage(driver);
    }

    public AreaPage searchPostcode(String postcode) {
        setPostcode(postcode);
        return clickSearch();
    }
}