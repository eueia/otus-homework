package pages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OtusPage {

    public static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, 10);

    String email = System.getProperty("email"); // eueiam@mailinator.com
    String password = System.getProperty("password"); // Test1111

    String authRegButton = "//button[@data-modal-id='new-log-reg']//span[1]";
    String emailField = "(//input[@name='email'])[3]";
    String passwordField = "//input[@type='password']";
    String loginBtn = "(//button[@type='submit'])[3]";
    String userName = "//p[@class='header2-menu__item-text header2-menu__item-text__username']";
    String dropdownMenuHeader = "//div[@class='header2-menu__icon-img ic-blog-default-avatar']";
    String profile = "//div[@class='header2-menu__dropdown-text']";

    String otusEmail = "//a[@class='header2_subheader-link header2_subheader-link__mail']";
    String otusPhone = "//a[@class='header2_subheader-link header2_subheader-link__phone']";

    String contacts = "//a[@href='/contacts/']";


    public void getOtusPage() {
        driver.get("https://otus.ru/");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS).implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void auth() {
        driver.findElement(By.xpath(authRegButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(emailField)));
        driver.findElement(By.xpath(emailField)).sendKeys(email);
        driver.findElement(By.xpath(passwordField)).sendKeys(password);
        driver.findElement(By.xpath(loginBtn)).click();
    }

    public void checkNameAfterLogin() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(userName)));
        WebElement uName =  driver.findElement(By.xpath(userName));
        assert uName.getText().contentEquals("Тест");
    }

    public void goToProfile() {
        WebElement menu = driver.findElement(By.xpath(dropdownMenuHeader));
        Actions actions = new Actions(driver);
        actions.moveToElement(menu).perform();
        WebElement profileOtus = driver.findElement(By.xpath(profile));
        profileOtus.click();
    }

    public void checkOtusEmail(String emailOtus) {
        WebElement email =  driver.findElement(By.xpath(otusEmail));
        assert email.getText().contentEquals(emailOtus);
    }

    public void checkOtusPhone(String phoneOtus) {
        WebElement phone =  driver.findElement(By.xpath(otusPhone));
        assert phone.getText().contentEquals(phoneOtus);
    }

    public void goToContacts() {
        WebElement contactInfo = driver.findElement(By.xpath(contacts));
        contactInfo.click();
    }
}

