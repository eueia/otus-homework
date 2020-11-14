import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OtusPage {

    protected static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, 10);

    String email = System.getProperty("email"); // eueiam@mailinator.com
    String password = System.getProperty("password"); // Test1111

    String authRegButton = "//button[@data-modal-id='new-log-reg']//span[1]";
    String emailField = "(//input[@name='email'])[3]";
    String passwordField = "//input[@type='password']";
    String loginBtn = "(//button[@type='submit'])[3]";
    String dropdownMenuHeader = "//div[@class='header2-menu__icon-img ic-blog-default-avatar']";
    String profile = "//div[@class='header2-menu__dropdown-text']";

    String name = "Тест";
    String surname = "Тестоедов";
    String date = "11.11.1999";
    String contact = "My contact";

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

    public void goToProfile() {
        WebElement menu = driver.findElement(By.xpath(dropdownMenuHeader));
        Actions actions = new Actions(driver);
        actions.moveToElement(menu).perform();
        WebElement profileOtus = driver.findElement(By.xpath(profile));
        profileOtus.click();
    }

    public void addPersonalData() {
        WebElement firstName = driver.findElement(By.id("id_fname"));
        WebElement firstNameLatin = driver.findElement(By.id("id_fname_latin"));
        WebElement lastName = driver.findElement(By.id("id_lname"));
        WebElement lastNameLatin = driver.findElement(By.id("id_lname_latin"));
        WebElement blogName = driver.findElement(By.id("id_blog_name"));
        WebElement birthDay = driver.findElement(By.name("date_of_birth"));
        String saveBtn = "(//button[@name='continue'])";

        firstName.clear();
        firstName.sendKeys(name);
        firstNameLatin.clear();
        firstNameLatin.sendKeys(name);
        lastName.clear();
        lastName.sendKeys(surname);
        lastNameLatin.clear();
        lastNameLatin.sendKeys(surname);
        blogName.clear();
        blogName.sendKeys(name);
        birthDay.clear();
        birthDay.sendKeys(date);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1100)");

        WebElement firstContactValue = driver.findElement(By.id("id_contact-0-value"));
        firstContactValue.clear();
        firstContactValue.sendKeys(contact);
        WebElement secondContactValue = driver.findElement(By.id("id_contact-1-value"));
        secondContactValue.clear();
        secondContactValue.sendKeys(contact);

        js.executeScript("window.scrollBy(0,700)");

        driver.findElement(By.xpath(saveBtn)).click();
    }

    public void checkPersonalData() {
        WebElement firstName = driver.findElement(By.id("id_fname"));
        WebElement firstNameLatin = driver.findElement(By.id("id_fname_latin"));
        WebElement lastName = driver.findElement(By.id("id_lname"));
        WebElement lastNameLatin = driver.findElement(By.id("id_lname_latin"));
        WebElement blogName = driver.findElement(By.id("id_blog_name"));
        WebElement birthDay = driver.findElement(By.name("date_of_birth"));

        assert firstName.getAttribute("value").equals(name);
        assert firstNameLatin.getAttribute("value").equals(name);
        assert lastName.getAttribute("value").equals(surname);
        assert lastNameLatin.getAttribute("value").equals(surname);
        assert blogName.getAttribute("value").equals(name);
        assert birthDay.getAttribute("value").equals(date);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1100)");

        WebElement firstContactValue = driver.findElement(By.id("id_contact-0-value"));
        WebElement secondContactValue = driver.findElement(By.id("id_contact-1-value"));

        assert firstContactValue.getAttribute("value").contentEquals(contact);
        assert secondContactValue.getAttribute("value").contentEquals(contact);
    }
}

