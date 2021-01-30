package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OtusPersonalDataPage extends OtusPage {

    String fname = "id_fname";
    String fnameLatin = "id_fname_latin";
    String lname = "id_lname";
    String lnameLatin = "id_lname_latin";
    String bDate = "date_of_birth";
    String bName = "id_blog_name";
    String fContact = "id_contact-0-value";
    String sContact = "id_contact-1-value";

    String dataFilledMessage = "//span[@class='success']";

    String name = "Тест";
    String surname = "Тестоедов";
    String date = "11.11.1999";
    String contact = "My contact";

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
    }
    @Step("Check personal data successfully saved")
    public void checkDataFilled() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dataFilledMessage)));
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
