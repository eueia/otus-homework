import java.net.MalformedURLException;
import java.net.URI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class SelenoidTest {

//    Настройка Селеноида:
//    curl -s https://aerokube.com/cm/bash | bash
//            ./cm selenoid start --vnc
//            ./cm selenoid-ui start

    protected static RemoteWebDriver driver;
    private static final Logger logger = LogManager.getLogger(SelenoidTest.class);

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), capabilities);
        logger.info("Selenoid Chrome is up");
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
        logger.info("Driver is down");
    }
}
