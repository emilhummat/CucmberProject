package steps;

import POM.SpecialsPOM;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.BaseDriver;

import java.util.List;

public class SpecialsSteps {
    public WebDriver driver = BaseDriver.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, 10);
    SpecialsPOM specials = new SpecialsPOM();

    @Given("^Navigate to Specials page$")
    public void navigateToSpecialsPage() {
        specials.waitAndClick(By.cssSelector("a[title='Specials']"));
    }

    @Then("^I should see the discount on the price$")
    public void iShouldSeeTheDiscountOnThePrice() {
        List<WebElement> itemList = driver.findElements(By.xpath("//div[@class='right-block']//span[@class='price-percent-reduction']"));
        String [] itemDiscount=new String[itemList.size()];
        for (int i = 0; i < itemList.size(); i++) {
            itemDiscount[i] = itemList.get(i).getText();
            System.out.println(itemDiscount[i]+" ");
            Assert.assertTrue(itemDiscount[i].contains("-")&&itemDiscount[i].contains("%"));

        }

    }
}
