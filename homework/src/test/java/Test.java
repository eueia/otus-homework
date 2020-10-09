import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Test {

    protected static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(Test.class);

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("ChromeDriver is up");
    }

    @org.junit.Test
    public void checkOtusTitle() {
        driver.get("https://otus.ru/");
        logger.info("Открыта страница OTUS");
        String otusTitle = driver.getTitle();
        assertThat(otusTitle, equalTo("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям"));
        logger.info("Title OTUS в порядке");
    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
        logger.info("ChromeDriver is down");
        logger.info("Это все логи на текущий час, благодарю за внимание!");
    }
}

