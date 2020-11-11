
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WDFTest {


    protected static WebDriver driver;

    @BeforeAll
    public void setUpDriver() {
        String browser = System.getProperty("browser").toLowerCase();
        WebDriverFactory wdf = new WebDriverFactory(browser);
        driver = wdf.createDriver(new DesiredCapabilities());
    }

    @Test
    public void openPage() {
        driver.get("https://otus.ru/");
    }

    @AfterAll
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
