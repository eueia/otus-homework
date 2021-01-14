import java.io.ByteArrayInputStream;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

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

    String fname = "id_fname";
    String fnameLatin = "id_fname_latin";
    String lname = "id_lname";
    String lnameLatin = "id_lname_latin";
    String bDate = "date_of_birth";
    String bName = "id_blog_name";
    String fContact = "id_contact-0-value";
    String sContact = "id_contact-1-value";

    String name = "Тест";
    String surname = "Тестоедов";
    String date = "11.11.1999";
    String contact = "My contact";

    @Step("Get otus.ru")
    public void getOtusPage() {
        driver.get("https://otus.ru/");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS).implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Step("Auth to otus.ru")
    public void auth() {
        driver.findElement(By.xpath(authRegButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(emailField)));
        driver.findElement(By.xpath(emailField)).sendKeys(email);
        driver.findElement(By.xpath(passwordField)).sendKeys(password);
        driver.findElement(By.xpath(loginBtn)).click();
    }

    @Step("Go to Otus profile")
    public void goToProfile() {
        WebElement menu = driver.findElement(By.xpath(dropdownMenuHeader));
        Actions actions = new Actions(driver);
        actions.moveToElement(menu).perform();
        WebElement profileOtus = driver.findElement(By.xpath(profile));
        profileOtus.click();
    }

    @Step("Add personal data to profile")
    public void addPersonalData() {
        WebElement firstName = driver.findElement(By.id(fname));
        WebElement firstNameLatin = driver.findElement(By.id(fnameLatin));
        WebElement lastName = driver.findElement(By.id(lname));
        WebElement lastNameLatin = driver.findElement(By.id(lnameLatin));
        WebElement blogName = driver.findElement(By.id(bName));
        WebElement birthDay = driver.findElement(By.name(bDate));
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

        WebElement firstContactValue = driver.findElement(By.id(fContact));
        firstContactValue.clear();
        firstContactValue.sendKeys(contact);
        WebElement secondContactValue = driver.findElement(By.id("id_contact-1-value"));
        secondContactValue.clear();
        secondContactValue.sendKeys(contact);

        js.executeScript("window.scrollBy(0,700)");

        driver.findElement(By.xpath(saveBtn)).click();
        Allure.addAttachment("PersonalDataPage", new ByteArrayInputStream(((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Check personal data after save")
    public void checkPersonalData() {
        WebElement firstName = driver.findElement(By.id(fname));
        WebElement firstNameLatin = driver.findElement(By.id(fnameLatin));
        WebElement lastName = driver.findElement(By.id(lname));
        WebElement lastNameLatin = driver.findElement(By.id(lnameLatin));
        WebElement blogName = driver.findElement(By.id(bName));
        WebElement birthDay = driver.findElement(By.name(bDate));

        assert firstName.getAttribute("value").contentEquals(name);
        assert firstNameLatin.getAttribute("value").contentEquals(name);
        assert lastName.getAttribute("value").contentEquals(surname);
        assert lastNameLatin.getAttribute("value").contentEquals(surname);
        assert blogName.getAttribute("value").contentEquals(name);
        assert birthDay.getAttribute("value").contentEquals(date);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1100)");

        WebElement firstContactValue = driver.findElement(By.id(fContact));
        WebElement secondContactValue = driver.findElement(By.id(sContact));

        assert firstContactValue.getAttribute("value").contentEquals(contact);
        assert secondContactValue.getAttribute("value").contentEquals(contact);
    }
}

