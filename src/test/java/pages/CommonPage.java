package pages;

import org.openqa.selenium.WebDriver;

import static org.junit.Assert.fail;

public class CommonPage {
    private final WebDriver driver;

    public CommonPage(WebDriver driver) {
        this.driver = driver;
    }

    public void gotoPage(String page) {
        if (page.equals("home")) {
            driver.get("https://www.just-eat.co.uk/");
        }
        else if (page.equals("signup")) {
            driver.get("https://www.just-eat.co.uk/account/register");
        }
        else {
            fail("The page specified does not exist");
        }
    }
}
