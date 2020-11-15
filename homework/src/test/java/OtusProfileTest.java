import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class OtusProfileTest {

    private static final Logger logger = LogManager.getLogger(OtusProfileTest.class);

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        OtusPage.driver = new ChromeDriver();
        logger.info("ChromeDriver is up");
    }
    @Test
    public void addProfileData() {
        OtusPage page = new OtusPage();
        page.getOtusPage();
        logger.info("Открыта страница OTUS");
        page.auth();
        logger.info("Авторизация в OTUS");
        page.goToProfile();
        logger.info("Переход в личный профиль");
        page.addPersonalData();
        logger.info("Личные данные заполнены и сохранены");
        OtusPage.driver.manage().deleteAllCookies();
        logger.info("Сессия удалена");
        page.getOtusPage();
        logger.info("Открыта страница OTUS");
        page.auth();
        logger.info("Авторизация в OTUS");
        page.goToProfile();
        logger.info("Переход в личный профиль");
        page.checkPersonalData();
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
