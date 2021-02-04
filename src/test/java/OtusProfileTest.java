import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import java.net.URL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.OtusPage;
import pages.OtusPersonalDataPage;

@Feature("Otus Profile")
public class OtusProfileTest {

    private static final Logger logger = LogManager.getLogger(OtusProfileTest.class);

//    @BeforeAll
//    public static void setUp() {
//        String browser = System.getProperty("browser_name").toLowerCase();
//        WebDriverFactory wdf = new WebDriverFactory(browser);
//        OtusPage.driver = wdf.createDriver(new DesiredCapabilities());
//        logger.info("ChromeDriver is up");
//    }
//    @BeforeAll
//    public static void setUp() {
//        WebDriverManager.chromedriver().setup();
//        OtusPage.driver = new ChromeDriver();
//        logger.info("ChromeDriver is up");
//    }

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        OtusPage.driver = new ChromeDriver();
        String slenoidURL = "http://localhost:4444/wd/hub";
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome");
        caps.setVersion("86.0");
        caps.setCapability("enableVNC", true);
        caps.setCapability("screenResolution", "1280x1024");
//        caps.setCapability("enableVideo", true);
//        caps.setCapability("enableLog", true);

        OtusPage.driver = new RemoteWebDriver(caps);
    }

    @Test
    @Description(value = "Add personal data to Otus profile and check after saving")
    public void addProfileData() {
        OtusPage page = new OtusPage();
        page.getOtusPage();
        logger.info("Открыта страница OTUS");
        page.auth();
        logger.info("Авторизация в OTUS");
        page.goToProfile();
        logger.info("Переход в личный профиль");
        OtusPersonalDataPage profilePage = new OtusPersonalDataPage();
        profilePage.addPersonalData();
        logger.info("Личные данные заполнены и сохранены");
        OtusPage.driver.manage().deleteAllCookies();
        logger.info("Сессия удалена");
        page.getOtusPage();
        logger.info("Открыта страница OTUS");
        page.auth();
        logger.info("Авторизация в OTUS");
        page.goToProfile();
        logger.info("Переход в личный профиль");
        profilePage.checkPersonalData();
        logger.info("Личные данные в профиле соответствуют сохраненным ранее");
    }

    @AfterAll
    public static void setDown() {
        if (OtusPage.driver != null) {
            OtusPage.driver.quit();
        }
        logger.info("ChromeDriver is down");
    }
}
