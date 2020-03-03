package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AreaPage {
    private final WebDriver driver;

    public AreaPage(WebDriver driver) {
        this.driver = driver;

        if (!driver.getTitle().contains("Restaurants and takeaways in ")) {
            System.out.println("area title is " + driver.getTitle());
            throw new IllegalStateException("This is not the area page");
        }
    }

    @FindBy(css = "#dish-search")
    private WebElement dishTextbox;

    @FindBy(css = "form button[data-test-id='unified-submit-button']")
    private WebElement searchButton;

    @FindBy(css = "div[data-test-id='searchresults'] > div:first-child section")
    private List<WebElement> searchResults;

    @FindBy(css = "div[class='c-locationHeader u-showAboveMid'] h1")
    private WebElement locationLabel;

    public AreaPage setDish(String dish) {
        dishTextbox.sendKeys(dish);

        return this;
    }

    public AreaPage clickSearch() {
        searchButton.click();

        return this;
    }

    public AreaPage searchDish(String dish) {
        setDish(dish);
        return clickSearch();
    }

    public List<WebElement> getSearchResults() {
        return searchResults;
    }

    public WebElement getLocationLabel() {
        return locationLabel;
    }
}
