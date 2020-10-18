import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;


public class WebDriverFactory {

    private String browser;

    public WebDriverFactory(String browser) {
        this.browser = browser;
    }

    public WebDriver createDriver(DesiredCapabilities capability) {
        WebDriver webDriver = null;

        if (StringUtils.startsWith(browser, "http://") || StringUtils.startsWith(browser, "https://")) {
            try {
                webDriver = new RemoteWebDriver(new URL(browser), capability);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        } else {
            if (BrowserType.CHROME.equalsIgnoreCase(browser)) {
                webDriver = new ChromeDriver(capability);
            } else if (BrowserType.SAFARI.equalsIgnoreCase(browser)) {
                webDriver = new SafariDriver(capability);
            } else if (BrowserType.FIREFOX.equalsIgnoreCase(browser)) {
                webDriver = new FirefoxDriver(capability);
            } else if (BrowserType.IE.equalsIgnoreCase(browser)) {
                webDriver = new InternetExplorerDriver(capability);
            } else {
                throw new RuntimeException("Unsupported browser: "+ browser);
            }
        }
        return webDriver;

    }
}
