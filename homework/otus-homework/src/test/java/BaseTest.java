import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class BaseTest {

    protected static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("ChromeDriver is up");
    }

    @Test
    public void checkOtusTitle() {
        driver.get("https://otus.ru/");
        logger.info("Открыта страница OTUS");
        String otusTitle = driver.getTitle();
        assertThat(otusTitle, equalTo("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям"));
        logger.info("Title OTUS в порядке");
    }

    @AfterAll
    public static void setDown() {
        if (driver != null) {
            driver.quit();
        }
        logger.info("ChromeDriver is down");
        logger.info("Это все логи на текущий час, благодарю за внимание!");
    }
}

