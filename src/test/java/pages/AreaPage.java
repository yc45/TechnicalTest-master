package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AreaPage {
    private final WebDriver driver;

    public AreaPage(WebDriver driver) {
        this.driver = driver;
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

    public void clickSearch() {
        searchButton.click();
    }

    public void searchDish(String dish) {
        setDish(dish);
        clickSearch();
    }

    public void clickResult(String restaurant) {
        driver.findElement(By.xpath("//div[@data-test-id='searchresults']//h3[text()='" + restaurant + "']")).click();
    }

    public List<WebElement> getSearchResults() {
        return searchResults;
    }

    public WebElement getLocationLabel() {
        return locationLabel;
    }

    public WebElement getRestaurant(String restaurant) {
        return driver.findElement(By.xpath("//div[@data-test-id='searchresults']//h3[text()='" + restaurant + "']"));
    }
}