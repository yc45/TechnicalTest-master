package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = "input[aria-label='Enter your postcode']")
    private WebElement postcodeTextbox;

    @FindBy(css = "button[data-test-id='find-restaurants-button']")
    private WebElement searchButton;

    @FindBy(xpath = "//footer[@class='main-footer']//span[text()='Sign up']")
    private WebElement signupButton;

    public void setPostcode(String postcode) {
        postcodeTextbox.sendKeys(postcode);
    }

    public void clickSearch() {
        searchButton.click();
    }

    public void searchPostcode(String postcode) {
        setPostcode(postcode);
        clickSearch();
    }

    public WebElement getSignupButton() {
        return signupButton;
    }

    public void clickSignup() {
        signupButton.click();
    }
}