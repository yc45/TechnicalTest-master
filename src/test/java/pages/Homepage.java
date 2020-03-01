package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage {
    @FindBy(css = "input[aria-label='Enter your postcode']")
    private WebElement postcodeTextbox;

    @FindBy(css = "button[data-test-id='find-restaurants-button']")
    private WebElement searchButton;

    public void setPostcode(String postcode) {
        postcodeTextbox.sendKeys(postcode);
    }

    public void clickSearch() {
        searchButton.click();
    }

    public void searchPostcode(String postcode) {
        this.setPostcode(postcode);
        this.clickSearch();
    }
}