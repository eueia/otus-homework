import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WDFTest {


    protected static WebDriver driver;

    @Before
    public void setUpDriver() {
        String browser = System.getProperty("browser").toLowerCase();
        WebDriverFactory wdf = new WebDriverFactory(browser);
        driver = wdf.createDriver(new DesiredCapabilities());
    }

    @Test
    public void openPage() {
        driver.get("https://otus.ru/");
    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
