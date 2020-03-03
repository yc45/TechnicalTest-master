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
import pages.AreaPage;
import pages.HomePage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class StepDefinitions {

    private WebDriver driver;
    private HomePage homepage;
    private AreaPage areapage;

    @Before
    public void before() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.just-eat.co.uk/");
        driver.manage().window().maximize();
        homepage = PageFactory.initElements(driver, HomePage.class);
    }

    @After
    public void after() {
        driver.quit();
    }

    @Given("I want food in {string}")
    public void i_want_food_in(String postcode) {
        areapage = homepage.searchPostcode(postcode);
    }

    @When("I search for restaurants {string}")
    public void i_search_for_restaurants(String restaurant) {
        areapage = PageFactory.initElements(driver, AreaPage.class);
        areapage.searchDish(restaurant);
    }

    @Then("I should see some restaurants in {string}")
    public void i_should_see_some_restaurants_in(String postcode) {
        assertTrue(areapage.getSearchResults().size() > 0);
        assertTrue(areapage.getLocationLabel().getText().contains(postcode));
    }

    @Given("I am at the create account page")
    public void i_am_at_the_create_account_page() {
        WebElement signupLink = driver.findElement(By.cssSelector("footer[class='c-footer'] ul[id='footer-customer-service'] a[href='/account/register']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", signupLink);
        signupLink.click();
    }

    private String name = "tester";

    @When("I enter the required information")
    public void i_enter_the_required_information() {
        String uuid = UUID.randomUUID().toString().substring(0,7);
        String email = "food" + uuid + "@gmail.com";
        String password = "test1234";

        driver.findElement(By.cssSelector("#Name")).sendKeys(name);
        driver.findElement(By.cssSelector("#Email")).sendKeys(email);
        driver.findElement(By.cssSelector("#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("form[class='register'] input[type='submit']")).click();
    }

    @Then("A new JUST EAT account is created")
    public void a_new_JUST_EAT_account_is_created() {
        WebElement user = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='c-nav-container']//span[contains(text(),'" + name + "')]")));
        assertEquals(1, driver.findElements(By.xpath("//div[@class='c-nav-container']//span[contains(text(),'" + name + "')]")).size());
    }

    @Then("I should be able to choose {string}")
    public void iShouldBeAbleToChoose(String restaurant) throws InterruptedException {
        driver.findElement(By.xpath("//div[@data-test-id='searchresults']//h3[text()='" + restaurant + "']")).click();
    }

    @Then("I should be able to add {string} to checkout")
    public void iShouldBeAbleToAddToTheCheckout(String dish) throws InterruptedException, IOException {
        try {
            WebElement itemButton = driver.findElement(By.xpath("//div[@id='menu']//h4[text()='" + dish + "']//..//..//button"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", itemButton);
            itemButton.click();

            if (driver.findElements(By.cssSelector("#preOrder")).size() == 1) {
                driver.findElement(By.cssSelector("#preOrder")).click();
            }

            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@id='basket']//span[@class='total'])[1]")));

            List<WebElement> ordersList = driver.findElements(By.xpath("//div[@id='basket']//h3[@class='basketItemDescription']//span[@class='basketItemName']"));
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

    public static void takeScreenshot(WebDriver driver, String path) throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String filename =  new SimpleDateFormat("yyyyMMddhhmmss'.png'").format(new Date());
        FileUtils.copyFile(scrFile, new File(path+filename));
    }
}
