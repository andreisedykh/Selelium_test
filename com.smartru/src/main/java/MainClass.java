import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MainClass {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D://Selenium/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        DistancePage distancePage = new DistancePage(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get("http://www.yandex.ru/");
        WebElement searchBox = driver.findElement(By.cssSelector(".search2 [type=\"submit\"]"));
        searchBox.sendKeys("расчет расстояний между городами");
        searchBox.submit();

        driver.findElement(By.partialLinkText("avtodispetcher.ru")).click();

        Set<String> windowHandles = driver.getWindowHandles();
        System.out.println("windowHandles.size is " + windowHandles.size());
        if (windowHandles.size()>2)
            throw new IllegalStateException();
        else    {
            for(String winHandle : driver.getWindowHandles()){
                driver.switchTo().window(winHandle);
            }
        }

        String currentUrl = driver.getCurrentUrl();
        System.out.println(currentUrl);
        Assert.assertEquals("https://www.avtodispetcher.ru/distance/", currentUrl);

        distancePage.doCalculate("Тула", "Санкт-Петербург", "9", "46");
        distancePage.clickChangeRouteButton();
        distancePage.enterThroughCity("Великий Новгород");
        System.out.println("Total distance is: " + distancePage.getTotalDistance() + " км");

        String distance = distancePage.getTotalDistance();
        Assert.assertEquals("897", distance);

        String totalPriceResult = distancePage.getTotalPrice();
        Assert.assertEquals("3726", totalPriceResult);


        System.out.println("Total price is: " + distancePage.getTotalPrice() + " руб.");
        Thread.sleep(60000);
        distancePage.submitCalculateButton();

        String distance1 = distancePage.getTotalDistance();
        Assert.assertEquals("966", distance1);
        String totalPriceResult1 = distancePage.getTotalPrice();
        Assert.assertEquals("4002", totalPriceResult1);

        Thread.sleep(5000);
        driver.quit();
    }
}
