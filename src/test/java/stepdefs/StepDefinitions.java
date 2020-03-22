package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class StepDefinitions {

    private WebDriver driver;
    private HomePage homepage;
    private AreaPage areapage;
    private AccountPage accountpage;
    private RestaurantPage restaurantPage;
    private CommonPage commonpage;

    @Before
    public void before() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        commonpage = PageFactory.initElements(driver, CommonPage.class);
    }

    @After
    public void after() {
        driver.quit();
    }

    @Given("I go to the {string} page")
    public void iGoToThePage(String page) {
        commonpage.gotoPage(page);
    }

    @When("I search for {string}")
    public void i_search_for_restaurants(String postcode) {
        homepage = PageFactory.initElements(driver, HomePage.class);
        homepage.searchPostcode(postcode);
        areapage = PageFactory.initElements(driver, AreaPage.class);
    }

    @Then("I should see some restaurants in {string}")
    public void i_should_see_some_restaurants_in(String postcode) {
        assertTrue(areapage.getSearchResults().size() > 0);
        assertTrue(areapage.getLocationLabel().getText().contains(postcode));
    }

    @Then("I should be able to choose {string}")
    public void iShouldBeAbleToChoose(String restaurant) {
        areapage.searchDish(restaurant);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(areapage.getRestaurant(restaurant)));
        areapage.clickResult(restaurant);
        restaurantPage = PageFactory.initElements(driver, RestaurantPage.class);
    }

    @Then("I should be able to add {string} to checkout")
    public void iShouldBeAbleToAddToTheCheckout(String dish) throws InterruptedException, IOException {
        try {
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.visibilityOf(restaurantPage.getDish(dish)));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", restaurantPage.getDish(dish));
            restaurantPage.addDish(dish);

            if (driver.findElements(By.cssSelector("#preOrder")).size() == 1) {
                driver.findElement(By.cssSelector("#preOrder")).click();
            }

            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='basketItemDescription']//span[text()='" + dish + "']")));

            List<WebElement> ordersList = restaurantPage.getOrders();
            for (WebElement order : ordersList) {
                if (order.getText().equals(dish)) {
                    return;
                }
            }
        }
        catch (Exception e) {
            this.takeScreenshot(driver,"C:\\yc\\interview\\bmo\\ss\\");
        }
        fail("Item was not added to checkout successfully");
    }

    private String name = "tester";

    @When("I enter the required information")
    public void i_enter_the_required_information() {
        String uuid = UUID.randomUUID().toString().substring(0,7);
        String email = "food" + uuid + "@gmail.com";
        String password = "test1234";
        accountpage = PageFactory.initElements(driver, AccountPage.class);
        accountpage.signup(name, email, password);
    }

    @Then("A new JUST EAT account is created")
    public void a_new_JUST_EAT_account_is_created() {
        WebElement user = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='user-inner']//div[@class='name' and contains(text(),'" + name + "')]")));
        assertEquals(1, driver.findElements(By.xpath("//div[@class='user-inner']//div[@class='name' and contains(text(),'" + name + "')]")).size());
    }

    public void takeScreenshot(WebDriver driver, String path) throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String filename =  new SimpleDateFormat("yyyyMMddhhmmss'.png'").format(new Date());
        FileUtils.copyFile(scrFile, new File(path+filename));
    }
}