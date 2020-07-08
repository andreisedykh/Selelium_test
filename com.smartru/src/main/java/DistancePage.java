import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DistancePage {
    private WebDriver driver;

    public DistancePage(WebDriver driver) {
        this.driver = driver;
    }

    private By fromField = By.cssSelector("input[name=from]");
    private By toField = By.cssSelector("input[name=to]");
    private By fuelConsumptionField = By.cssSelector(".fuel input[name=fc]");
    private By fuelPriceField = By.cssSelector(".fuel input[name=fp]");
    private By calculateButton = By.cssSelector(".submit_button input");
    private By throughCityField = By.cssSelector(".through input");
    private By extraSettingsButton = By.cssSelector("a#trigger");
    private By changeRouteButton = By.cssSelector("a#triggerFormD");
    private By totalDistance = By.cssSelector("#summaryContainer span#totalDistance");
    private By totalPrice = By.xpath("//form//p[contains(text(),':')]");


    public DistancePage submitCalculateButton() {
        driver.findElement(calculateButton).submit();
        return this;
    }

    public DistancePage clickExtraSettingsButton() throws InterruptedException {
        driver.findElement(extraSettingsButton).click();
        Thread.sleep(5000);
        return this;
    }

    public DistancePage clickChangeRouteButton() throws InterruptedException {
        driver.findElement(changeRouteButton).click();
        Thread.sleep(5000);
        return this;
    }

    public String getTotalPrice() {
        String str =  driver.findElement(totalPrice).getText();
        String subStr = str.substring(str.indexOf("руб./л."));
        String result = subStr.replaceAll("\\D", "");
        return result;
    }
    public String getTotalDistance() {
        return driver.findElement(totalDistance).getText();
    }

    public DistancePage enterFromCity(String fromCity) {
        driver.findElement(fromField).clear();
        driver.findElement(fromField).sendKeys(fromCity);
        return this;
    }

    public DistancePage enterToCity(String toCity) {
        driver.findElement(toField).clear();
        driver.findElement(toField).sendKeys(toCity);
        return this;
    }

    public DistancePage enterFuelConsumption(String fuelConsumption) {
        driver.findElement(fuelConsumptionField).clear();
        driver.findElement(fuelConsumptionField).sendKeys(fuelConsumption);
        return this;
    }

    public DistancePage enterFuelPrice(String fuelPrice) {
        driver.findElement(fuelPriceField).clear();
        driver.findElement(fuelPriceField).sendKeys(fuelPrice);
        return this;
    }

    public DistancePage enterThroughCity(String throughCity) {
        driver.findElement(throughCityField).clear();
        driver.findElement(throughCityField).sendKeys(throughCity);
        return this;
    }

    public DistancePage doCalculate(String fromCity, String toCity, String fuelConsumption, String fuelPrice) throws InterruptedException {
        this.enterFromCity(fromCity);
        this.enterToCity(toCity);
        this.enterFuelConsumption(fuelConsumption);
        this.enterFuelPrice(fuelPrice);
        this.submitCalculateButton();
        Thread.sleep(5000);
        return this;
    }

    public DistancePage doCalculate(String fromCity, String toCity, String fuelConsumption, String fuelPrice, String throughCity) throws InterruptedException {
        this.enterFromCity(fromCity);
        this.enterToCity(toCity);
        this.enterFuelConsumption(fuelConsumption);
        this.enterFuelPrice(fuelPrice);
        this.clickChangeRouteButton();
        this.enterThroughCity(throughCity);
        this.submitCalculateButton();
        Thread.sleep(5000);
        return this;
    }




}
