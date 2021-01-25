package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class OtusContactPage extends OtusPage {

    String vkButton = "//a[@class='SocialBlock__Title-ktydkg-2 dWkFod']";
    String facebookButton = "(//a[@class='SocialBlock__Title-ktydkg-2 dWkFod'])[2]";
    String yandexButton = "Дзен";
    String okButton = "(//a[@class='SocialBlock__Title-ktydkg-2 dWkFod'])[3]";
    String youtubeButton = "YouTube";

    public void checkVkLink(String vkLink) {
        WebElement vk =  driver.findElement(By.xpath(vkButton));
        assert vk.getAttribute("href").contentEquals(vkLink);
    }

    public void checkFbLink(String fbLink) {
        WebElement fb =  driver.findElement(By.xpath(facebookButton));
        assert fb.getAttribute("href").contentEquals(fbLink);
    }

    public void checkOkLink(String okLink) {
        WebElement ok =  driver.findElement(By.xpath(okButton));
        assert ok.getAttribute("href").contentEquals(okLink);
    }

    public void checkYtLink(String ytLink) {
        WebElement yt =  driver.findElement(By.linkText(youtubeButton));
        assert yt.getAttribute("href").contentEquals(ytLink);
    }

    public void checkYandexLink(String yandexLink) {
        WebElement yandex =  driver.findElement(By.linkText(yandexButton));
        assert yandex.getAttribute("href").contentEquals(yandexLink);
    }
}
