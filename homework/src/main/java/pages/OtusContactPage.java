package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class OtusContactPage extends OtusPage {

    String vkButton = "//a[@class='contacts__social hover-ic']";
    String facebookButton = "(//a[@class='contacts__social hover-ic'])[2]";
    String yandexButton = "(//a[@class='contacts__social hover-ic'])[4]";
    String okButton = "(//a[@class='contacts__social hover-ic'])[3]";
    String youtubeButton = "(//a[@class='contacts__social hover-ic'])[5]";

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
        WebElement yt =  driver.findElement(By.xpath(youtubeButton));
        assert yt.getAttribute("href").contentEquals(ytLink);
    }

    public void checkYandexLink(String yandexLink) {
        WebElement yandex =  driver.findElement(By.xpath(yandexButton));
        assert yandex.getAttribute("href").contentEquals(yandexLink);
    }
}
