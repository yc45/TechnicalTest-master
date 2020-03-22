package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage {
    public final WebDriver driver;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = "#Name")
    private WebElement nameTextbox;

    @FindBy(css = "#Email")
    private WebElement emailTextbox;

    @FindBy(css = "#Password")
    private WebElement passwordTextbox;

    @FindBy(css = "form[class='register'] input[type='submit']")
    private WebElement signupButton;

    public void setName(String name) {
        nameTextbox.sendKeys(name);
    }

    public void setEmail(String email) {
        emailTextbox.sendKeys(email);
    }

    public void setPassword(String password) {
        passwordTextbox.sendKeys(password);
    }

    public void clickSignup() {
        signupButton.click();
    }

    public void signup(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickSignup();
    }
}