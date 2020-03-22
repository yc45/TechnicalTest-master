package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RestaurantPage {
    private final WebDriver driver;

    public RestaurantPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//div[@id='basket']//h3[@class='basketItemDescription']//span[@class='basketItemName']")
    private List<WebElement> orders;

    @FindBy(xpath = "//div[@id='basket']//h3[@class='basketItemDescription']//span[@class='basketItemName']")
    private WebElement subtotal;
    
    public WebElement getDish(String dish) {
        return driver.findElement(By.xpath("//div[@id='menu']//h4[text()='" + dish + "']"));
    }

    public void addDish(String dish) {
        driver.findElement(By.xpath("//div[@id='menu']//h4[text()='" + dish + "']//..//..//button")).click();
    }

    public WebElement getSubtotal() {
        return subtotal;
    }

    public List<WebElement> getOrders() {
        return orders;
    }
}