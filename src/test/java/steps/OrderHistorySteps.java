package steps;

import POM.OrderHistoryPOM;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.BaseDriver;

import java.util.List;
import java.util.Random;

public class OrderHistorySteps {
    public WebDriver driver = BaseDriver.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, 10);
    OrderHistoryPOM order = new OrderHistoryPOM();
    String modelName;

    @Given("^Navigate to the dresses page$")
    public void navigateToTheDressesPage() {
        order.waitAndClick(By.xpath("(//a[@title='Dresses'])[2]"));
    }

    @And("^Choose a random item and add it to the cart$")
    public void chooseARandomItemAndAddItToTheCart() {
        List<WebElement> itemList = driver.findElements(By.cssSelector(".product_img_link"));
        int num = new Random().nextInt(itemList.size());
        order.waitAndClick(itemList.get(num));
        modelName =driver.findElement(By.cssSelector(".editable")).getText();
        order.waitAndClick(By.cssSelector("button[name='Submit']"));
        order.waitAndClick(By.cssSelector("a[title='Proceed to checkout']"));
    }

    @And("^Verify the total price of the product$")
    public void verifyTheTotalPriceOfTheProduct() {
        String totalProductsPrice = driver.findElement(By.id("total_product")).getText();
        double totalProductsPriceDouble = order.StringToDouble(totalProductsPrice);
        String totalShippingPrice = driver.findElement(By.id("total_shipping")).getText();
        double totalShippingPriceDouble = order.StringToDouble(totalShippingPrice);
        String taxPrice = driver.findElement(By.id("total_tax")).getText();
        double taxPriceDouble = order.StringToDouble(taxPrice);
        String totalPrice = driver.findElement(By.id("total_price")).getText();
        double totalPriceDouble = order.StringToDouble(totalPrice);
        Assert.assertEquals(totalPriceDouble, totalProductsPriceDouble + totalShippingPriceDouble + taxPriceDouble);
    }

    @And("^Proceed to the checkout page$")
    public void proceedToTheCheckoutPage() {
        order.waitAndClick(By.xpath("(//a[@title='Proceed to checkout'])[2]"));
        order.waitAndClick(By.cssSelector("button[name='processAddress']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        order.waitAndClick(By.id("uniform-cgv"));
        order.waitAndClick(By.xpath("(//span[contains(text(),'Proceed to checkout')])[2]"));
    }

    @When("^I do payment and confirm it$")
    public void iDoPaymentAdnConfirmIt() {
        order.waitAndClick(By.cssSelector(".bankwire"));
        order.waitAndClick(By.xpath("//span[contains(text(),'I confirm my order')]"));
    }

    @Then("^Order confirmation message should be appeared$")
    public void orderConfirmationMessageShouldBeAppeared() {
        String expected = "Your order on My Store is complete.";
        String actual = driver.findElement(By.cssSelector(".cheque-indent")).getText();
        Assert.assertEquals(expected, actual);
    }

    @Given("^Navigate to the My Order page$")
    public void navigateToTheMyOrderPage() {
        order.waitAndClick(By.cssSelector("a[title='My orders']"));
    }

    @Then("^The last order name in the table should be the same as the name of the ordered item$")
    public void theLastOrderNameInTheTableShouldBeTheSameAsTheNameOfTheOrderedItem() {
        order.waitAndClick(By.xpath("//tr[@class='first_item ']"));
        order.waitAndClick(By.xpath("(//span[contains(text(),'Details')])[2]"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//table[@class='table table-bordered']/tbody/tr/td)[1]")));
        String modelInOrderHistory=driver.findElement((By.xpath("(//table[@class='table table-bordered']/tbody/tr/td)[1]"))).getText();
        System.out.println(modelInOrderHistory);
        System.out.println(modelName);
        Assert.assertEquals(modelName,modelInOrderHistory);




    }

}
