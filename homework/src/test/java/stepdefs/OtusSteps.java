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

    @Then("Check Otus support email is \"help@otus.ru\"")
    public void checkEmail() {
        page.checkOtusEmail("help@otus.ru");
    }

    @Then("Check Otus phone is \"+7 499 938-92-02\"")
    public void checkPhone() {
        page.checkOtusPhone("+7 499 938-92-02");
    }

    @Then("Go to Otus Contact Page")
    public void goToContact() {
        page.goToContacts();
        contactPage = new OtusContactPage();
    }

    @And("Check \"Вконтакте\" link is correct")
    public void checkVk() {
        contactPage.checkVkLink("https://vk.com/club145052891");
    }

    @And("Check \"Facebook\" link is correct")
    public void checkFb() {
        contactPage.checkFbLink("https://www.facebook.com/otus.ru/");
    }

    @And("Check \"OK\" link is correct")
    public void checkOk() {
        contactPage.checkOkLink("https://ok.ru/group/54448251797611");
    }

    @And("Check \"YouTube\" link is correct")
    public void checkYT() {
        contactPage.checkYtLink("https://www.youtube.com/channel/UCetgtvy93o3i3CvyGXKFU3g");
    }

    @And("Check \"Yandex Zen\" link is correct")
    public void checkYandex() {
        contactPage.checkYandexLink("https://zen.yandex.ru/id/5bbcbc1ba5bd5400a990e7d9");
    }

    @Then("Check Otus Title")
    public void checkOtusTitle() {
        String otusTitle = OtusPage.driver.getTitle();
        MatcherAssert.assertThat(otusTitle, CoreMatchers.equalTo("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям"));
    }
}
