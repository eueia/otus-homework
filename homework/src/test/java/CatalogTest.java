import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CatalogTest {
    protected static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(CatalogTest.class);

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
    }

    @Test
    public void yandex() {
        String url = "https://market.yandex.ru/";
        String electronicsBtn = "(//span[@class='_3z8GfB4w3a'])[3]";
        String smartphonesBtn = "//a[contains(@class,'_2qvOOvezty _2x2zBaVN-3')]";
        String samsungCheck = "//input[@name='Производитель Samsung']/following-sibling::div[1]";
        String xiaomiCheck = "//input[@name='Производитель Xiaomi']/following-sibling::div[1]";
        String byPriceBtn = "//button[@class='_2zH77vazcW _3tIaiy1WMf']";
        String firstCompareBtn = "//div[@class='_1CXljk9rtd']";
        String nextCompareBtn = "(//div[@class='_1CXljk9rtd'])[3]";
        String phoneAddTitle = "//div[@class='nMEoEKZaF-']//div[1]";
        String toCompareBtn = "//a[@href='/my/compare-lists']//span[1]";
        String comparison = "//div[text()='Сравнение товаров']";
        String listSize = "//img[@class='_1qSSlc4Oxp']";

        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        logger.info(String.format("Открыта страница %s", url));

        WebElement electronics = driver.findElement(By.xpath(electronicsBtn));
        wait.until(ExpectedConditions.elementToBeClickable(electronics));
        electronics.click();
        logger.info("Открыт раздел Электроника");

        WebElement smartphones = driver.findElement(By.xpath(smartphonesBtn));
        wait.until(ExpectedConditions.elementToBeClickable(smartphones));
        smartphones.click();
        logger.info("Открыт подраздел Смартфоны");

        WebElement byPrice = driver.findElement(By.xpath(byPriceBtn));
        wait.until(ExpectedConditions.elementToBeClickable(byPrice));
        byPrice.click();
        logger.info("Товары отсортированы по повышению цены");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)");

        WebElement samsung = driver.findElement(By.xpath(samsungCheck));
        samsung.click();
        logger.info("В фильтре выбран бренд Samsung");

        WebElement xiaomi = driver.findElement(By.xpath(xiaomiCheck));
        xiaomi.click();
        logger.info("В фильтре выбран бренд Xiaomi");

        Actions actions = new Actions(driver);

        try {
        WebElement compare = driver.findElement(By.xpath(firstCompareBtn));
        compare.click();
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
            {
                WebElement compare = driver.findElement(By.xpath(firstCompareBtn));
                compare.click();
                logger.info("Добавляем первый товар к сравнению");
            }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(phoneAddTitle)));
        logger.info("Товар добавлен");

        WebElement compare1 = driver.findElement(By.xpath(nextCompareBtn));
        compare1.click();
        logger.info("Добавляем второй товар к сравнению");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(phoneAddTitle)));
        logger.info("Товар добавлен");

        WebElement toCompare = driver.findElement(By.xpath(toCompareBtn));
        toCompare.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(comparison)));

        Integer goodsListsize = driver.findElements(By.xpath(listSize)).size();
        assertThat(goodsListsize, equalTo(2));
        logger.info("В списке " + goodsListsize + " товара");
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

