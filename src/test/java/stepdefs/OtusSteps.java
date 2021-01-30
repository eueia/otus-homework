package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.OtusContactPage;
import pages.OtusPage;
import pages.OtusPersonalDataPage;

public class OtusSteps {

    OtusPage page;
    OtusPersonalDataPage profilePage;
    OtusContactPage contactPage;

    @Given("Launch the browser")
    public void launchTheBrowser() {
        WebDriverManager.chromedriver().setup();
        OtusPage.driver = new ChromeDriver();
    }

    @When("Open Otus on your browser")
    public void openOtusPage() {
        page = new OtusPage();
        profilePage = new OtusPersonalDataPage();
        page.getOtusPage();
    }
    @Then("Login to Otus")
    public void loginOtus() {
        page.auth();
    }

    @And("Check user name is \"Тест\" after login")
    public void checkName() {
        page.checkNameAfterLogin();
    }

    @And("Go to personal data page")
    public void goToProfile() {
        page.goToProfile();
    }

    @And("Fill profile by personal data")
    public void fillProfile() {
        profilePage.addPersonalData();
    }

    @Then("Clear cookies")
    public void clearCookies() {
        OtusPage.driver.manage().deleteAllCookies();
    }

    @And("Check \"Данные успешно сохранены\" is showing")
    public void issSuccessfulDataFilled() {
        profilePage.checkDataFilled();
    }

    @Then("Shut down browser")
    public void shutBrowser() {
        OtusPage.driver.close();
    }

    @Then("Check Otus support email is {string}")
    public void checkEmail(String email) {
        page.checkOtusEmail(email);
    }

    @Then("Check Otus phone is {string}")
    public void checkPhone(String phone) {
        page.checkOtusPhone(phone);
    }

    @Then("Go to Otus Contact Page")
    public void goToContact() {
        page.goToContacts();
        contactPage = new OtusContactPage();
    }

    @And("Check \"Вконтакте\" link is {string}")
    public void checkVk(String vkLink) {
        contactPage.checkVkLink(vkLink);
    }

    @And("Check \"Facebook\" link is {string}")
    public void checkFb(String fbLink) {
        contactPage.checkFbLink(fbLink);
    }

    @And("Check \"OK\" link is {string}")
    public void checkOk(String okLink) {
        contactPage.checkOkLink(okLink);
    }

    @And("Check \"YouTube\" link is {string}")
    public void checkYT(String ytLink) {
        contactPage.checkYtLink(ytLink);
    }

    @And("Check \"Yandex Zen\" link is {string}")
    public void checkYandex(String yaLink) {
        contactPage.checkYandexLink(yaLink);
    }

    @Then("Check Otus Title is {string}")
    public void checkOtusTitle(String title) {
        String otusTitle = OtusPage.driver.getTitle();
        MatcherAssert.assertThat(otusTitle, CoreMatchers.equalTo(title));
    }
}
